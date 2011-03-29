/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.token;

import com.tkorg.util.Constants;
import com.tkorg.util.Global;
import java.io.File;
import org.htmlparser.beans.StringBean;
import vn.hus.nlp.tokenizer.VietTokenizer;

/**
 * them removestopword
 * @author danhit
 */
public class SeperateWords {

    private VietTokenizer vietToken01 = null;
    private File trainFile = null;

    public SeperateWords() {
        trainFile = new File(Constants.PATH_SVM_TRAIN_TRAINDATA);
    }

    public void forTrain() {
        vietToken01 = new VietTokenizer();
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
                            vietToken01.tokenize(textFiles[j].toString(), temp);
                    }
                }
            }
        }
    }

    public void forClassify() throws Exception {
        for (int i = 0; i < Global.entityList.size(); i++) {
            int temp = Global.entityList.get(i).getLink().indexOf(".pdf");
            boolean isPDF = false;
            if (temp == -1)
                isPDF = false;
            else
                isPDF = true;

            Thread seperateWordsThread = new SeperateWordsThread();
            ((SeperateWordsThread) seperateWordsThread).setLink(Global.entityList.get(i).getLink());
            ((SeperateWordsThread) seperateWordsThread).setIndex(i);
            ((SeperateWordsThread) seperateWordsThread).setIsPDF(isPDF);
            ((SeperateWordsThread) seperateWordsThread).setTitle("" + i);
            ((SeperateWordsThread) seperateWordsThread).start();
            ((SeperateWordsThread) seperateWordsThread).join();
            ((SeperateWordsThread) seperateWordsThread).stop();
        }
    }
    
    public String getUrlContentsAsText(String url) {
        String content = "";
        StringBean stringBean = new StringBean();
        stringBean.setURL(url);
        content = stringBean.getStrings();
        return content;
    }


    public static void main(String[] args) {
    }
}