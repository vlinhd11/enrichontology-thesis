/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.features;

import com.tkorg.util.Constants;
import java.io.File;

/**
 *
 * @author DANHIT
 */
public class TFIDF {

    private String word = "";
    private double value = 0;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

//    public void calculateTFIDF() {
//        File cnttFiles = new File(Constants.PATH_TRAIN + "/cntt");
//        File[] textFiles = cnttFiles.listFiles();
//                    for (int i = 0; i < textFiles.length; i++) {
//
//                    }
//    }

//    public static void main(String args[]) {
//
//    }
}
