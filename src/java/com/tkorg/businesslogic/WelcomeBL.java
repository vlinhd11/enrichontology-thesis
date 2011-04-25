/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.businesslogic;

import com.tkorg.entities.OWLModel;
import com.tkorg.util.Constants;
import java.io.File;
import java.net.URI;



/**
 *
 * @author DANHIT
 */
public class WelcomeBL {

    public static boolean isFirst = true;

    public void initOntology() {
        URI uri = new File(Constants.PATH_ONTOLOGY).toURI();
        OWLModel owlModel = new OWLModel();
        owlModel.loadOWLModelFromExistFile(uri);
    }
}
