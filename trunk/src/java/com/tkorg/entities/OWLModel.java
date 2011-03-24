package com.tkorg.entities;

import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import java.net.URI;

/**
 *
 * @author danhit
 */
public class OWLModel {
    public static JenaOWLModel owlModel = null;

    public OWLModel() {}

    public void loadOWLModelFromExistFile(URI uri) {
        try {
            if (owlModel == null)
                owlModel = ProtegeOWL.createJenaOWLModelFromURI(uri.toString());
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public JenaOWLModel getOWLModel() {
    	return owlModel;
    }
}
