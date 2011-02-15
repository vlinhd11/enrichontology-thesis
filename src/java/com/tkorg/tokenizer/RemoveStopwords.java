/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.tokenizer;

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
public class RemoveStopwords {

    public ArrayList < String > stopwordList = null;

    public RemoveStopwords() {
        stopwordList = new ArrayList < String >();

        File file = new File(Constants.PATH_STOPWORD);
//        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            String temp = "";

            // repeat until all lines is read
            while ((text = reader.readLine()) != null) {
//                contents.append(text).append(System.getProperty("line.separator"));
                temp = new String(text.getBytes(), "UTF-8");
                stopwordList.add(temp);
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

        // show file contents here
//        System.out.println(contents.toString());
    }

    public void remove(String pathFile) {
        File file = new File(pathFile);
        BufferedReader reader = null;

        try {
//            reader = new BufferedReader(new FileReader(file));
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String text = null;
//            String temp = "";
            String content = "";

            //Loai bo stopword.
            while ((text = reader.readLine()) != null) {
//                temp = new String(text.getBytes(), "UTF-8");
//                temp = text;
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
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            out.write(content);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RemoveStopwords.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RemoveStopwords.class.getName()).log(Level.SEVERE, null, ex);
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
        RemoveStopwords temp = new RemoveStopwords();
        String path = Constants.PATH_FILES + "/danhit.txt";

        temp.remove(path);
    }
}
