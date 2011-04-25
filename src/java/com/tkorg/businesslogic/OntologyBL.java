/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.businesslogic;

import com.tkorg.entities.OWLModel;
import com.tkorg.search.ClassActions;
import com.tkorg.util.Constants;
import com.tkorg.util.Global;
import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author DANHIT
 */
public class OntologyBL {

    private String path;
    private String result;
    public static boolean isFirst = true;

    public void loadOntology(String namePath) {

//        URI uri = new File(namePath).toURI();
//
//        OWLModel owlModel = new OWLModel();
//        owlModel.loadOWLModelFromExistFile(uri);
//        Global.init();
//
//        OWLNamedClass cao_dang = OWLModel.owlModel.getOWLNamedClass("Cao_đẳng");
//        Collection < OWLIndividual > list = cao_dang.getInstances(true);
//        for (Iterator it = list.iterator(); it.hasNext();) {
//            OWLIndividual temp = (OWLIndividual) it.next();
//            JOptionPane.showMessageDialog(null, temp.getBrowserText());
//        }

        URI uri = new File(namePath).toURI();

        OWLModel owlModel = new OWLModel();
        owlModel.loadOWLModelFromExistFile(uri);
        Global.init();
        ClassActions.viewClasses();
        this.result = Global.strResult;
    }

public static ArrayList < String > listProperty = new ArrayList<String>();
public static ArrayList < String > listValue = new ArrayList<String>();

    public void showValues(String nameIndividual) {
        URI uri = new File(Constants.PATH_ONTOLOGY).toURI();
        listProperty.removeAll(listProperty);
        listValue.removeAll(listValue);

        OWLModel owlModel = new OWLModel();
        owlModel.loadOWLModelFromExistFile(uri);
        Global.init();

        String strVN = SearchOntologyBL.convertVN(nameIndividual + ".");
        strVN = strVN.substring(0, strVN.length() - 1).replace(" ", "_");
        OWLIndividual ind = OWLModel.owlModel.getOWLIndividual(strVN);
        Collection < OWLDatatypeProperty > dataPropertyList = OWLModel.owlModel.getUserDefinedOWLDatatypeProperties();
        for (Iterator it = dataPropertyList.iterator(); it.hasNext();) {
            OWLDatatypeProperty dataProperty = (OWLDatatypeProperty) it.next();
            Object data = ind.getPropertyValue(dataProperty);
            if (data != null) {
                String propertyName = dataProperty.getBrowserText().substring(dataProperty.getBrowserText().indexOf(":") + 1);
                listProperty.add(propertyName);
                listValue.add(data.toString());
            }
        }
    }

    public String getIndividuals(String content) {
        
        String strIndividuals = "";

        String strVN = SearchOntologyBL.convertVN(content + ".");
        strVN = strVN.substring(0, strVN.length() - 1).replace(" ", "_");
        OWLNamedClass namedClass = OWLModel.owlModel.getOWLNamedClass(strVN);

        Collection < OWLIndividual > list = namedClass.getInstances(true);
        for (Iterator it = list.iterator(); it.hasNext();) {
            OWLIndividual temp = (OWLIndividual) it.next();
            strIndividuals += temp.getBrowserText() + ";";
        }
        return strIndividuals;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        loadOntology(path);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
