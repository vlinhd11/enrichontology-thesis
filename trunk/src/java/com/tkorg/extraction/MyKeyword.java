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
public class MyKeyword {

    String name;
    ArrayList < String > individuals = null;

    public MyKeyword() {
        individuals = new ArrayList < String >();
    }

    public ArrayList<String> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(ArrayList<String> individuals) {
        this.individuals = individuals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
