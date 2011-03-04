/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.features;

import com.tkorg.util.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DANHIT
 */
public class Main {

    public static ArrayList < String > features = null;
    private int quantityOfFeatures = 0;
    private ArrayList < MyFile > fileList = null;
    private ArrayList < TFIDF > tfidfFMax = null;
    private boolean isCNTT;

    public Main() {
        fileList = new ArrayList < MyFile >();
        tfidfFMax = new ArrayList < TFIDF >();
        features = new ArrayList < String >();
    }

    public int calculateSum(ArrayList < String > words) {
        return words.size();
    }

    public TFIDF calculateTFIDF(double tf, double idf, String word) {

        double result = tf * idf;
        TFIDF tfidf = new TFIDF();
        tfidf.setWord(word);
        tfidf.setValue(result);

        return tfidf;
    }

    public String outFile(String pathFile) {

        String result = "";
        String strFeaturesList = "";
        int temp;

        for (int i = 0; i < quantityOfFeatures; i++) {
            strFeaturesList = strFeaturesList + tfidfFMax.get(i).getWord() + "\n";
        }
        if (isCNTT == true)
            temp = 1;
        else
            temp = -1;
        for (int i = 0; i < fileList.size(); i++) {
            result = result + temp + " ";
            for (int j = 0; j < quantityOfFeatures; j++) {
                result = result + (j + 1) + ":";
                boolean isExist = false;
                for (int k = 0; k < fileList.get(i).getTfidfList().size(); k++) {
                    if (tfidfFMax.get(j).getWord().equals(fileList.get(i).getTfidfList().get(k).getWord())) {
                        isExist = true;
                        result = result + fileList.get(i).getTfidfList().get(k).getValue() + " ";
                    }
                }
                if (!isExist) {
                    result = result + "0 ";
                }
            }
            result = result + "\n";
        }

        try {
            File file = new File(pathFile);
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            output.write(strFeaturesList);
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ie) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ie);
        }

        return result;
    }

    public String outFile02(String pathFile) {

        String result = "";
        for (int i = 0; i < fileList.size(); i++) {
            result = result + "1" + " ";
            for (int j = 0; j < quantityOfFeatures; j++) {
                result = result + (j + 1) + ":";
                boolean isExist = false;
                for (int k = 0; k < fileList.get(i).getTfidfList().size(); k++) {
                    if (features.get(j).equals(fileList.get(i).getTfidfList().get(k).getWord())) {
                        isExist = true;
                        result = result + fileList.get(i).getTfidfList().get(k).getValue() + " ";
                    }
                }
                if (!isExist) {
                    result = result + "0 ";
                }
            }
            result = result + "\n";
        }

        try {
            OutputStream output = new FileOutputStream(pathFile);
            DataOutputStream data = new DataOutputStream(output);
            data.writeBytes(result);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ie) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ie);
        }

        return result;
    }

    public IDF calculateIDF(int N, int dfValue, String word) {

        double temp = Math.log10((double)N/dfValue) + 1;
        IDF idf = new IDF();
        idf.setWord(word);
        idf.setValue(temp);

        return idf;
    }

    public void calculateTF(ArrayList < MyFile > fileList) {

        for (int i = 0; i < fileList.size(); i++) {
            for (int j = 0; j < fileList.get(i).getWords().size(); j++) {
                boolean isExist = false;
                for (int k = 0; k < fileList.get(i).getTfList().size(); k++) {
                    if (fileList.get(i).getWords().get(j).equals(fileList.get(i).getTfList().get(k).getWord())) {
                        isExist = true;
                        fileList.get(i).getTfList().get(k).addCount();
                    }
                }
                if (!isExist) {
                    TF tf = new TF();
                    tf.setWord(fileList.get(i).getWords().get(j));
                    tf.addCount();
                    fileList.get(i).getTfList().add(tf);
                }
            }
        }

        for (int i = 0; i < fileList.size(); i++) {
            for (int j = 0; j < fileList.get(i).getTfList().size(); j++) {
                fileList.get(i).getTfList().get(j).setFrequency(
                        calculateFrequency(fileList.get(i).getTfList().get(j).getCount(), fileList.get(i).getSumWordOfFile()));
            }
        }
    }

    public DF calculateDF(String word, ArrayList < MyFile > files) {

        DF df = new DF();
        int count = 0;

        df.setWord(word);
        for (int i = 0; i < files.size(); i++) {
            for (int j = 0; j < files.get(i).getWords().size(); j++) {
                if (word.equals(files.get(i).getWords().get(j))) {
                    count = count + 1;
                    break;
                }
            }
        }
        df.setValue(count);

        return df;
    }

    public double calculateFrequency(int count, int sum) {
        return (double) count/sum;
    }
    
    public void loadFiles(String pathFiles) {
        File cnttFiles = new File(pathFiles);
        BufferedReader reader = null;

        if (cnttFiles.exists() && cnttFiles.isDirectory()) {
            File[] textFiles = cnttFiles.listFiles();
            for (int i = 0; i < textFiles.length; i++) {
                MyFile file = new MyFile();
                file.setName(textFiles[i].getName());
                
                try {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(textFiles[i]), "UTF8"));
                    String text = "";
                    ArrayList < String > temp = new ArrayList < String >();
                    boolean isFirst = true;
                    while ((text = reader.readLine()) != null) {
                        if (isFirst == false)
                            temp.add(text);
                        isFirst = false;
                    }
                    file.setWords(temp);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                fileList.add(file);
            }
        } else {
            System.out.println("cnttFiles has not exist of files");
            return;
        }
    }

    public void sortTFIDFList() {
        for (int i = 0; i < (tfidfFMax.size() - 1); i++) {
            for (int j = i + 1; j < tfidfFMax.size(); j++) {
                if (tfidfFMax.get(i).getValue() < tfidfFMax.get(j).getValue()) {
                    String word = tfidfFMax.get(i).getWord();
                    tfidfFMax.get(i).setWord(tfidfFMax.get(j).getWord());
                    tfidfFMax.get(j).setWord(word);

                    double temp = tfidfFMax.get(i).getValue();
                    tfidfFMax.get(i).setValue(tfidfFMax.get(j).getValue());
                    tfidfFMax.get(j).setValue(temp);
                }
            }
        }

        for (int i = 0; i < tfidfFMax.size(); i++) {
            if ("”".equals(tfidfFMax.get(i).getWord())) {
                tfidfFMax.remove(i);
            }
        }
    }

    public void groupTFIDFList() {
        for (int i = 0; i < fileList.size(); i++) {
            for (int j = 0; j < fileList.get(i).getTfidfList().size(); j++) {
                tfidfFMax.add(fileList.get(i).getTfidfList().get(j));
            }
        }

        for (int i = 0; i < tfidfFMax.size(); i++) {
            for (int j = i + 1; j < tfidfFMax.size(); j++) {
                if (tfidfFMax.get(i).getWord().equals(tfidfFMax.get(j).getWord())) {
                    tfidfFMax.get(i).setValue(tfidfFMax.get(i).getValue() + tfidfFMax.get(j).getValue());
                    tfidfFMax.remove(j);
                }
            }
        }
    }

    public void runFile(String pathFiles) {

        loadFiles(pathFiles);

        for (int i = 0; i < fileList.size(); i++) {
            int sum = calculateSum(fileList.get(i).getWords());
            fileList.get(i).setSumWordOfFile(sum);
        }

        calculateTF(fileList);

        for (int i = 0; i < fileList.size(); i++) {
            for (int j = 0; j < fileList.get(i).getWords().size(); j++) {
                DF df = new DF();
                df = calculateDF(fileList.get(i).getWords().get(j), fileList);

                IDF idf = calculateIDF(fileList.size(), df.getValue(), fileList.get(i).getWords().get(j));
                fileList.get(i).addIDF(idf);
            }
        }

        for (int i = 0; i < fileList.size(); i++) {
            for (int j = 0; j < fileList.get(i).getTfList().size(); j++) {
                for (int k = 0; k < fileList.get(i).getIdfList().size(); k++) {
                    if (fileList.get(i).getTfList().get(j).getWord().equals(fileList.get(i).getIdfList().get(k).getWord())) {
                        TFIDF tfidf = new TFIDF();
                        tfidf = calculateTFIDF(fileList.get(i).getTfList().get(j).getFrequency(),
                                fileList.get(i).getIdfList().get(k).getValue(),
                                fileList.get(i).getTfList().get(j).getWord());
                        fileList.get(i).addTFIDF(tfidf);
                        break;
                    }
                }
            }
        }
    }

    public void loadFeatures() {
        BufferedReader reader;
        File file = new File(Constants.PATH_SVM_TRAIN_ITFEATURES);

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String text = "";
            while ((text = reader.readLine()) != null) {
                features.add(text);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException io) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, io);
        }
    }

    public ArrayList<MyFile> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<MyFile> fileList) {
        this.fileList = fileList;
    }

    public int getQuantityOfFeatures() {
        return quantityOfFeatures;
    }

    public void setQuantityOfFeatures(int quantityOfFeatures) {
        this.quantityOfFeatures = quantityOfFeatures;
    }

    public boolean isIsCNTT() {
        return isCNTT;
    }

    public void setIsCNTT(boolean isCNTT) {
        this.isCNTT = isCNTT;
    }

    public ArrayList<TFIDF> getTfidfFMax() {
        return tfidfFMax;
    }

    public void setTfidfFMax(ArrayList<TFIDF> tfidfFMax) {
        this.tfidfFMax = tfidfFMax;
    }

    public static void main(String args[]) {

        Main cntt = new Main();
        String result = "";

        cntt.setIsCNTT(true);
        cntt.runFile(Constants.PATH_SVM_TRAIN_REMOVESTOPWORDFILES + "/cntt");
        cntt.groupTFIDFList();
        cntt.sortTFIDFList();
        cntt.setQuantityOfFeatures(20);
        result = cntt.outFile(Constants.PATH_SVM_TRAIN_ITFEATURES);

        Main khac = new Main();
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
}
