/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.features;

import java.util.ArrayList;

/**
 *
 * @author DANHIT
 */
public class MyFile {

    private String name;
    private int sumWordOfFile;
    private ArrayList < TF > tfList = null;
    private ArrayList < TFIDF > tfidfList = null;
    private ArrayList < String > words = null;
    private ArrayList < IDF > idfList = null;
    private ArrayList < TFIDF > maxTFIDF = null;

    public MyFile() {
        name = "";
        sumWordOfFile = 0;
        tfList = new ArrayList < TF >();
        tfidfList = new ArrayList < TFIDF >();
        words = new ArrayList < String >();
        idfList = new ArrayList < IDF >();
        maxTFIDF = new ArrayList < TFIDF >();
    }

    public ArrayList<TFIDF> getMaxTFIDF() {
        return maxTFIDF;
    }

    public void setMaxTFIDF(ArrayList<TFIDF> maxTFIDF) {
        this.maxTFIDF = maxTFIDF;
    }

    public ArrayList<IDF> getIdfList() {
        return idfList;
    }

    public void setIdfList(ArrayList<IDF> idfList) {
        this.idfList = idfList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSumWordOfFile() {
        return sumWordOfFile;
    }

    public void setSumWordOfFile(int sumWordOfFile) {
        this.sumWordOfFile = sumWordOfFile;
    }

    public ArrayList<TF> getTfList() {
        return tfList;
    }

    public void setTfList(ArrayList<TF> tfList) {
        this.tfList = tfList;
    }

    public ArrayList<TFIDF> getTfidfList() {
        return tfidfList;
    }

    public void setTfidfList(ArrayList<TFIDF> tfidfList) {
        this.tfidfList = tfidfList;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    public void addTF(TF tf) {
        this.tfList.add(tf);
    }

    public void addIDF(IDF idf) {
        this.idfList.add(idf);
    }

    public void addTFIDF(TFIDF tfidf) {
        this.tfidfList.add(tfidf);
    }

    public void addMaxTFIDF(TFIDF tfidf) {
        this.maxTFIDF.add(tfidf);
    }
}
