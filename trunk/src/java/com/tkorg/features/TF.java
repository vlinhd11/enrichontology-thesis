/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.features;

/**
 *
 * @author DANHIT
 */
public class TF {

    private String word = "";
    private double frequency = 0;
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void addCount() {
        this.count = this.count + 1;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
