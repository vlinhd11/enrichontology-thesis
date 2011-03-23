/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.token;

import com.tkorg.util.Constants;
import com.tkorg.util.Global;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.htmlparser.beans.StringBean;
import vn.hus.nlp.tokenizer.Tokenizer;
import vn.hus.nlp.tokenizer.TokenizerOptions;
import vn.hus.nlp.tokenizer.TokenizerProvider;
import vn.hus.nlp.tokenizer.VietTokenizer;
import vn.hus.nlp.tokenizer.tokens.TaggedWord;

/**
 * them removestopword
 * @author danhit
 */
public class SeperateWords {

    private VietTokenizer vietToken = null;
    public Tokenizer tokenizer = null;
    private File trainFile = null;
    private List < TaggedWord > list = null;

    public SeperateWords() {
        trainFile = new File(Constants.PATH_SVM_TRAIN_TRAINDATA);
    }

    public void forTrain() {
        vietToken = new VietTokenizer();
        File lastTrainFile = new File(Constants.PATH_SVM_TRAIN_REMOVESTOPWORDFILES);

        if (!lastTrainFile.exists()) {
            lastTrainFile.mkdir();
        }

        if (trainFile.exists() && trainFile.isDirectory()) {
            File[] trainFiles = trainFile.listFiles();
            for (int i = 0; i < trainFiles.length; i++) {
                File file = new File(Constants.PATH_SVM_TRAIN_REMOVESTOPWORDFILES + "/" + trainFiles[i].getName());
                if (!file.exists())
                    file.mkdir();

                if (trainFiles[i].isDirectory()) {
                    File[] textFiles = trainFiles[i].listFiles();
                    for (int j = 0; j < textFiles.length; j++) {
                        String temp = Constants.PATH_SVM_TRAIN_REMOVESTOPWORDFILES + "/" + trainFiles[i].getName() + "/" + textFiles[j].getName();
                         int index = temp.indexOf(".svn");
                         if (index == -1)
                            vietToken.tokenize(textFiles[j].toString(), temp);
                    }
                }
            }
        }
    }

    public void forClassify() throws Exception {
        tokenizer = TokenizerProvider.getInstance().getTokenizer();

        File file = new File(Constants.PATH_SVM_CLASSIFY_DOWNLOADFILES);
        if (!file.exists())
            file.mkdir();

        for (int i = 0; i < Global.entityList.size(); i++) {
            int temp = Global.entityList.get(i).getLink().indexOf(".pdf");
            if (temp == -1)
                downloadFileFromLink(Global.entityList.get(i).getLink(), i);
            else
                downloadPDFFromLink(Global.entityList.get(i).getLink(), Global.entityList.get(i).getTitle(), i);
        }
    }
    
    public String getUrlContentsAsText(String url) {
        String content = "";
        StringBean stringBean = new StringBean();
        stringBean.setURL(url);
        content = stringBean.getStrings();
        return content;
    }

    private String segment(String sentence) throws Exception {
	StringBuffer result = new StringBuffer(1000);
	StringReader reader = new StringReader(sentence);

        // tokenize the sentence
	tokenizer.tokenize(reader);
        list = tokenizer.getResult();
        for (TaggedWord taggedWord : list) {
            String word = taggedWord.toString();
            if (TokenizerOptions.USE_UNDERSCORE) {
                word = word.replaceAll("\\s+", "_");
            } else {
                word = "[" + word + "]";
            }
            result.append(word);
            result.append(' ');
        }
	
	return result.toString().trim();
    }

    private void downloadFileFromLink(String url, int index) {
        try {
            Global.entityList.get(index).setContent(segment(getUrlContentsAsText(url)));
        } catch (Exception ex) {
            Logger.getLogger(SeperateWords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void downloadPDFFromLink(String url, String title, int index) {
        try {
            downloadPDF(url, title);
            changePDFtoText(title, index);
        } catch (Exception ex) {
            Logger.getLogger(SeperateWords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void downloadPDF(String url, String title) throws FileNotFoundException, IOException {

        BufferedInputStream in = new BufferedInputStream(new java.net.URL(url).openStream());
        FileOutputStream fos = new FileOutputStream(Constants.PATH_SVM_CLASSIFY_PDFFILES + "/" + title + ".pdf");
        BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
        byte[] data = new byte[1024];
        int x = 0;
        while((x = in.read(data, 0, 1024)) >= 0) {
            bout.write(data, 0, x);
        }
        bout.close();
        in.close();
    }

    public void changePDFtoText(String title, int index) throws Exception {

        PDDocument pddDocument = PDDocument.load(new File(Constants.PATH_SVM_CLASSIFY_PDFFILES + "/" + title + ".pdf"));
        PDFTextStripper textStripper = new PDFTextStripper();
        Global.entityList.get(index).setContent(textStripper.getText(pddDocument));
        pddDocument.close();
    }

    public static void main(String[] args) {
    }
}