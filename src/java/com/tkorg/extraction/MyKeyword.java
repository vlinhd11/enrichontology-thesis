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

    private String name;
    private ArrayList < String > links = null;
    private ArrayList < String > individuals = null;

    public MyKeyword() {
        links = new ArrayList < String >();
        individuals = new ArrayList < String >();
    }

    public ArrayList<String> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(ArrayList<String> individuals) {
        this.individuals = individuals;
    }

    public ArrayList<String> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<String> links) {
        this.links = links;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
