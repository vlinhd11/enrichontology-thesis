/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.token;

import com.tkorg.util.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DANHIT
 */
public class Stopwords {

    public ArrayList < String > stopwordList = null;

    public Stopwords() {
        stopwordList = new ArrayList < String >();

        File file = new File(Constants.PATH_STOPWORD);
        BufferedReader reader = null;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            String temp = "";

            // repeat until all lines is read
            while ((text = reader.readLine()) != null) {
                temp = new String(text.getBytes(), "UTF-8");
                stopwordList.add(temp);
            }
            for (int i = 0; i < Constants.extraStopwords.length; i++) {
                stopwordList.add(Constants.extraStopwords[i]);
            }
            for (int i = 0; i < Constants.extraCapitalStopwords.length; i++) {
                stopwordList.add(Constants.extraCapitalStopwords[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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

    public void removeStopwords(String pathFile, String outFile, boolean isClassify) {
        if (isClassify == true) {
            File download_files = new File(pathFile);
            File[] files = download_files.listFiles();
            for (int i = 0; i < files.length; i++) {
                remove(pathFile + "/" + files[i].getName(), outFile + "/" + files[i].getName(), isClassify);
            }
        } else {
            File download_files = new File(pathFile);
            File[] files01 = download_files.listFiles();
            for (int i = 0; i < files01.length; i++) {
                File[] files02 = files01[i].listFiles();
                for (int j = 0; j < files02.length; j++) {
                    remove(pathFile + "/" + files01[i].getName() + "/" + files02[j].getName(),
                            outFile + "/" + files01[i].getName() + "/" + files02[j].getName(), isClassify);
                }
            }
        }
    }

    public void remove(String pathFile, String outFile, boolean isClassify) {
        File file = new File(pathFile);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String text = null;
            String content = "";

            //Loai bo stopword.
            while ((text = reader.readLine()) != null) {
                String[] temp = text.split(" ");

                for (int i = 0; i < temp.length; i++) {
                    for (int j = 0; j < stopwordList.size(); j++) {
                        if (temp[i].equals(stopwordList.get(j))) {
                            temp[i] = "";
                        }
                    }
                }
                for (int i = 0; i < temp.length; i++) {
                    if (!temp[i].equals("")) {
                        content = content + temp[i] + "\n";
                    }
                }
            }

            //Xuat ra file.
            BufferedWriter out = null;
            if (isClassify == false) {
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            } else {
                File file02 = new File(outFile);
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file02), "UTF-8"));
            }
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

    public ArrayList<String> getStopwordList() {
        return stopwordList;
    }

    public void setStopwordList(ArrayList<String> stopwordList) {
        this.stopwordList = stopwordList;
    }

    public static void main(String[] args) {
        Stopwords temp = new Stopwords();
        String path = Constants.PATH_SVM_TRAIN_REMOVESTOPWORDFILES + "/cntt" + "/3.txt";

        temp.remove(path, path, false);
    }
}
