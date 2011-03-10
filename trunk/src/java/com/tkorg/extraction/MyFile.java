/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.extraction;

import java.util.ArrayList;

/**
 *
 * @author DANHIT
 */
public class MyFile {

    String name;
    ArrayList < String > sentences = null;

    MyFile() {
        sentences = new ArrayList < String >();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getSentences() {
        return sentences;
    }

    public void setSentences(ArrayList<String> sentences) {
        this.sentences = sentences;
    }
}
