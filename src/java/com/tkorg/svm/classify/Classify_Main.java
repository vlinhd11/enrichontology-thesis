/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.svm.classify;

import com.tkorg.features.Features_Main;
import com.tkorg.token.SeperateWords;
import com.tkorg.token.Stopwords;
import com.tkorg.util.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DANHIT
 */
public class Classify_Main {

    private void outFile(File file, String pathChosenFile) {
        BufferedWriter out = null;
        BufferedReader reader = null;
        String content = "";
        File chosenFile = new File(pathChosenFile + "/" + file.getName());

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String text = "";
            while ((text = reader.readLine()) != null) {
                content = content + text + "\n";
            }
            
            //Xuat file.
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(chosenFile), "UTF-8"));
            out.write(content);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Stopwords.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Stopwords.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void choseFile() {
        File download_files = new File(Constants.PATH_SVM_CLASSIFY_DOWNLOADFILES);
        File[] fileList = null;
        File resultFile = new File(Constants.PATH_SVM_CLASSIFY_RESULT);
        BufferedReader reader = null;
        ArrayList < Integer > svmList = new ArrayList < Integer >();

        fileList = download_files.listFiles();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(resultFile), "UTF8"));
            String text = null;

            //Loai bo stopword.
            svmList.add(0); //0 la chi .svn
            while ((text = reader.readLine()) != null) {
                svmList.add(Integer.parseInt(text.replace(".0", "")));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Stopwords.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Stopwords.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < svmList.size(); i++) {
            if (svmList.get(i) == 1) {
                int index = fileList[i].getPath().indexOf(".svn");
                if (index == -1)
                    outFile(fileList[i], Constants.PATH_SVM_CLASSIFY_CHOSENFILES);
            }
        }
        System.out.println("finish choseFile");
    }

    public void calculateTFIDF() {
        Features_Main cntt = new Features_Main();

        cntt.setIsCNTT(true);
        cntt.runFile(Constants.PATH_SVM_CLASSIFY_REMOVESTOPWORDFILES);
        cntt.groupTFIDFList();
        cntt.sortTFIDFList();
        cntt.loadFeatures();
        cntt.setQuantityOfFeatures(Features_Main.features.size());
        cntt.outFile02(Constants.PATH_SVM_CLASSIFY_TFIDFDOWNLOADFILES);
        System.out.println("finish calculateTFIDF");
    }

    public void removeStopwords() {
        Stopwords stopwords = new Stopwords();

        stopwords.removeStopwords(Constants.PATH_SVM_CLASSIFY_DOWNLOADFILES, Constants.PATH_SVM_CLASSIFY_REMOVESTOPWORDFILES, true);
        System.out.println("finish removeStopwords");
    }

    public void seperateWordsForClassify() {
        try {
            SeperateWords seperateWords = new SeperateWords();
            seperateWords.forClassify();
            System.out.println("finish seperateWordsForClassify");
        } catch (Exception ex) {
            Logger.getLogger(Classify_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void useSVMTest() {
        try {
            SVMTest testSVM = new SVMTest();
            String pathTestScaleFile = Constants.PATH_SVM_CLASSIFY_TFIDFDOWNLOADFILES;
            String modelFileName = Constants.PATH_SVM_TRAIN_MODEL;
            String resultFileName = Constants.PATH_SVM_CLASSIFY_RESULT;
            testSVM.run(pathTestScaleFile, modelFileName, resultFileName);
        } catch (IOException ex) {
            Logger.getLogger(com.tkorg.svm.classify.Classify_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("finish useSVMTest");
    }

    public static void main(String args[]) {
        com.tkorg.svm.classify.Classify_Main classify = new com.tkorg.svm.classify.Classify_Main();

//        classify.seperateWordsForClassify();
//        classify.removeStopwords();
        classify.calculateTFIDF();
        classify.useSVMTest();
        classify.choseFile();
    }
}
