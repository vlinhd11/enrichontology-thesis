/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.svm.train;

import com.tkorg.token.SeperateWords;
import com.tkorg.token.Stopwords;
import com.tkorg.util.Constants;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DANHIT
 */
public class Main {

    public void useSVMTrain() {
        SVMTrain t = new SVMTrain();
	String inputFileName = Constants.PATH_SVM_TRAIN_TFIDFFEATURES;
	String modelFileName = Constants.PATH_SVM_TRAIN_MODEL;
        try {
            t.run(inputFileName, modelFileName);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lookForFeatures() {
        com.tkorg.features.Main cntt = new com.tkorg.features.Main();
        String result = "";

        cntt.setIsCNTT(true);
        cntt.runFile(Constants.PATH_SVM_TRAIN_REMOVESTOPWORDFILES + "/cntt");
        cntt.groupTFIDFList();
        cntt.sortTFIDFList();
        cntt.setQuantityOfFeatures(20);
        result = cntt.outFile(Constants.PATH_SVM_TRAIN_ITFEATURES);

        com.tkorg.features.Main khac = new com.tkorg.features.Main();
        khac.setIsCNTT(false);
        khac.runFile(Constants.PATH_SVM_TRAIN_REMOVESTOPWORDFILES + "/khac");
        khac.groupTFIDFList();
        khac.sortTFIDFList();
        khac.setQuantityOfFeatures(20);
        result = result + khac.outFile(Constants.PATH_SVM_TRAIN_OTHERFEATURES);

        try {
            OutputStream output = new FileOutputStream(Constants.PATH_SVM_TRAIN_TFIDFFEATURES);
            DataOutputStream data = new DataOutputStream(output);
            data.writeBytes(result);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ie) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ie);
        }
    }

    public void removeStopwords() {
        Stopwords stopwords = new Stopwords();

        stopwords.removeStopwords(Constants.PATH_SVM_TRAIN_REMOVESTOPWORDFILES, Constants.PATH_SVM_TRAIN_REMOVESTOPWORDFILES, false);
    }

    public void seperateWordsForTrain() {
        SeperateWords seperateWords = new SeperateWords();

        seperateWords.forTrain();
    }

    public static void main(String[] args) {
        com.tkorg.svm.train.Main train = new com.tkorg.svm.train.Main();

        train.seperateWordsForTrain();
        train.removeStopwords();
        train.lookForFeatures();
        train.useSVMTrain();
    }
}
