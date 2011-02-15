/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.tokenizer;

import com.tkorg.util.Constants;
import java.io.File;
import vn.hus.nlp.tokenizer.VietTokenizer;

/**
 * them removestopword
 * @author danhit
 */
public class VietToken {

    private VietTokenizer vietToken = null;
    private File trainFile = null;
    private RemoveStopwords removeStopwords = null;

    public VietToken() {
        vietToken = new VietTokenizer();
        trainFile = new File(Constants.PATH_TRAIN);
    }

    public void seperateInTrain() {
//        TokenizerOptions.XML_OUTPUT = true;
        File lastTrainFile = new File(Constants.PATH_LAST_TRAIN);
        removeStopwords = new RemoveStopwords();

        if (!lastTrainFile.exists()) {
            lastTrainFile.mkdir();
        }

        if (trainFile.exists() && trainFile.isDirectory()) {
            File[] trainFiles = trainFile.listFiles();
            for (int i = 0; i < trainFiles.length; i++) {
                File file = new File(Constants.PATH_LAST_TRAIN + "/" + trainFiles[i].getName());
                if (!file.exists())
                    file.mkdir();

                if (trainFiles[i].isDirectory()) {
                    File[] textFiles = trainFiles[i].listFiles();
                    for (int j = 0; j < textFiles.length; j++) {
                        String temp = Constants.PATH_LAST_TRAIN + "/" + trainFiles[i].getName() + "/" + textFiles[j].getName();
                        vietToken.tokenize(textFiles[j].toString(), temp);
                        removeStopwords.remove(temp);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        VietToken vietToken = new VietToken();

        vietToken.seperateInTrain();
    }
}