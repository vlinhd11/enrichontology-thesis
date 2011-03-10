/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.token;

import com.tkorg.businesslogic.SearchOntologyBL;
import com.tkorg.util.Constants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.util.Iterator;
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
import vn.hus.nlp.utils.UTF8FileUtility;

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

        //Download Google.
        File file = new File(Constants.PATH_SVM_CLASSIFY_DOWNLOADFILES);
        if (!file.exists())
            file.mkdir();

        for (int i = 0; i < SearchOntologyBL.googleList.size(); i++) {
            for (int j = 0; j < SearchOntologyBL.googleList.get(i).getLinkList().size(); j++) {
                int index = SearchOntologyBL.googleList.get(i).getLinkList().get(j).indexOf(".pdf");
                if (index == -1) {
                    downloadFileFromLink(SearchOntologyBL.googleList.get(i).getLinkList().get(j),
                        "google-" + SearchOntologyBL.googleList.get(i).getName() + "-" + (j + 1));
                } else {
                    downloadPDFFromLink(SearchOntologyBL.googleList.get(i).getLinkList().get(j),
                            "google-" + SearchOntologyBL.googleList.get(i).getName() + "-" + (j + 1),
                            ".pdf");
                }
            }
        }

        //Download Yahoo.
        for (int i = 0; i < SearchOntologyBL.yahooList.size(); i++) {
            for (int j = 0; j < SearchOntologyBL.yahooList.get(i).getLinkList().size(); j++) {
                int index = SearchOntologyBL.yahooList.get(i).getLinkList().get(j).indexOf(".pdf");
                if (index == -1) {
                    downloadFileFromLink(SearchOntologyBL.yahooList.get(i).getLinkList().get(j),
                            "yahoo-" + SearchOntologyBL.yahooList.get(i).getName() + "-" + (j + 1));
                } else {
                    downloadPDFFromLink(SearchOntologyBL.yahooList.get(i).getLinkList().get(j),
                            "google-" + SearchOntologyBL.yahooList.get(i).getName() + "-" + (j + 1),
                            ".pdf");
                }
            }
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

    private void downloadFileFromLink(String url, String title) {
        try {
            segment(getUrlContentsAsText(url));
            outFile(Constants.PATH_SVM_CLASSIFY_DOWNLOADFILES + "/" + title + ".txt");
        } catch (Exception ex) {
            Logger.getLogger(SeperateWords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void downloadPDFFromLink(String url, String title, String type) {
        try {
            downloadPDF(url, title, type);
            changePDFtoText(title, type);
        } catch (Exception ex) {
            Logger.getLogger(SeperateWords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void downloadPDF(String url, String title, String type) throws FileNotFoundException, IOException {
        BufferedInputStream in = new BufferedInputStream(new java.net.URL(url).openStream());
        FileOutputStream fos = new FileOutputStream(Constants.PATH_SVM_CLASSIFY_PDFFILES + "/" + title + type);
        BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
        byte[] data = new byte[1024];
        int x = 0;
        while((x = in.read(data, 0, 1024)) >= 0) {
            bout.write(data, 0, x);
        }
        bout.close();
        in.close();
    }

    public void changePDFtoText(String title, String type) throws Exception {

        PDDocument pddDocument = PDDocument.load(new File(Constants.PATH_SVM_CLASSIFY_PDFFILES + "/" + title + type));
            
        PDFTextStripper textStripper = new PDFTextStripper();

        String test=textStripper.getText(pddDocument);
        Writer output = null;
        File file = new File(Constants.PATH_SVM_CLASSIFY_DOWNLOADFILES + "/" + title + ".txt");
        output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        output.write(test);
        output.close();
        pddDocument.close();
    }

    private void outFile(String filename) throws Exception {
        UTF8FileUtility.createWriter(filename);
        for (Iterator<TaggedWord> iter = list.iterator(); iter.hasNext();) {
            TaggedWord token = iter.next();
            UTF8FileUtility.write(token.toString().replaceAll(" ", "_") + " ");
        }

	UTF8FileUtility.closeWriter();
    }

    public static void main(String[] args) {
    }
}