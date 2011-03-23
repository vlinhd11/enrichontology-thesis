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
import vn.hus.nlp.tokenizer.tokens.TaggedWord;

/**
 *
 * @author DANHIT
 */
public class SeperateWordsThread extends Thread {

    private Tokenizer tokenizer = null;
    private List < TaggedWord > list = null;
    private boolean isPDF;
    private String link;
    private String title;
    private int index;

    public SeperateWordsThread() {
        link = "";
        isPDF = false;
    }

    @Override
    public void run() {
        if (isPDF == false)
            downloadFileFromLink(link, index);
        else
            downloadPDFFromLink(link, title, index);
    }

    private String getUrlContentsAsText(String url) {
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

    private void downloadPDF(String url, String title) throws FileNotFoundException, IOException {

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

    private void changePDFtoText(String title, int index) throws Exception {

        PDDocument pddDocument = PDDocument.load(new File(Constants.PATH_SVM_CLASSIFY_PDFFILES + "/" + title + ".pdf"));
        PDFTextStripper textStripper = new PDFTextStripper();
        Global.entityList.get(index).setContent(textStripper.getText(pddDocument));
        pddDocument.close();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isIsPDF() {
        return isPDF;
    }

    public void setIsPDF(boolean isPDF) {
        this.isPDF = isPDF;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Tokenizer getTokenizer() {
        return tokenizer;
    }

    public void setTokenizer(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }
}
