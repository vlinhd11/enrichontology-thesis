/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.businesslogic;

import com.tkorg.entities.OWLModel;
import com.tkorg.extraction.MyKeyword;
import com.tkorg.util.Constants;
import com.tkorg.util.Global;
import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DANHIT
 */
public class UpdateOntologyBL {

    public ArrayList < MyKeyword > selectedItemsList = null;

    public UpdateOntologyBL() {
        selectedItemsList = new ArrayList < MyKeyword >();
    }

    public void choseItems(String[] item) {
        if (item[0] == null) {

        } else {
            for (int i = 0; i < item.length; i++) {
                String[] temp = item[i].split("-");
                int temp01 = Integer.parseInt(temp[0]);
                int temp02 = Integer.parseInt(temp[1]);
                Global.keywordList.get(temp01).getIndividuals().remove(temp02);
            }
        }

        for (int i = 0; i < Global.keywordList.size(); i++) {
            selectedItemsList.add(Global.keywordList.get(i));
        }
    }

    public void inputOntology() {
        try {
            for (int i = 0; i < selectedItemsList.size(); i++) {
                OWLNamedClass keyword = OWLModel.owlModel.getOWLNamedClass(selectedItemsList.get(i).getName().replace(" ", "_"));
                OWLDatatypeProperty property01 = OWLModel.owlModel.getOWLDatatypeProperty("Định_nghĩa");
                OWLDatatypeProperty property02 = OWLModel.owlModel.getOWLDatatypeProperty("Nguồn");
                for (int j = 0; j < selectedItemsList.get(i).getIndividuals().size(); j++) {
                    int index = checkExist(keyword.getBrowserText());
                    OWLIndividual individual = keyword.createOWLIndividual(keyword.getBrowserText() + "_" + index);
                    property01.setDomain(keyword);
                    individual.setPropertyValue(property01, selectedItemsList.get(i).getIndividuals().get(j));

                    property02.setDomain(keyword);
                    individual.setPropertyValue(property02, selectedItemsList.get(i).getLinks().get(j));
                }
            }
            if (OWLModel.owlModel.hasChanged()) {
                URI uri = new File(Constants.PATH_ONTOLOGY).toURI();
                OWLModel.owlModel.save(uri);
            } else {
            }
        } catch (Exception ex) {
            Logger.getLogger(UpdateOntologyBL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int checkExist(String keyword) {
        int index = 0;
        do {
            OWLIndividual individual = OWLModel.owlModel.getOWLIndividual(keyword + "_" + index);
            if (individual == null) {
                return index;
            }
            index++;
        } while (true);
    }
}
