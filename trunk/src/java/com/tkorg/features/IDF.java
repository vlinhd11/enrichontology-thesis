/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.features;

/**
 *
 * @author DANHIT
 */
public class IDF {

    private String word;
    private double value;

    public IDF() {
        word = "";
        value = 0;
    }

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
}
