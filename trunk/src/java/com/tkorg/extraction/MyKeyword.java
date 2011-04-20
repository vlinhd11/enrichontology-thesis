/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.extraction;

import com.tkorg.entities.MyIndividual;
import java.util.ArrayList;

/**
 *
 * @author DANHIT
 */
public class MyKeyword {

    private String name;
    private ArrayList < MyIndividual > individuals = null;

    public MyKeyword() {
        individuals = new ArrayList < MyIndividual >();
    }

    public ArrayList<MyIndividual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(ArrayList<MyIndividual> individuals) {
        this.individuals = individuals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
