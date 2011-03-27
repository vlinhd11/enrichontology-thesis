/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.util;

import com.tkorg.entities.MyEntity;
import com.tkorg.entities.MyKeyword;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import java.util.ArrayList;

/**
 *
 * @author DANHIT
 */
public class Global {

    public static ArrayList < MyEntity > entityList = null;
    public static ArrayList < String > keywordNameList = null;
    public static ArrayList < String > features = null;
    public static ArrayList < com.tkorg.extraction.MyKeyword > keywordList = null;
    public static ArrayList < OWLNamedClass > classesArrayList = null;
    public static ArrayList < String > liClassNameList = null;
    public static ArrayList < MyKeyword > chosenLinks = null;
    public static ArrayList < MyKeyword > googleList = null;
    public static ArrayList < MyKeyword > yahooList = null;
    public static String selectedClassName = "";
    public static String strResult = "";

    public Global() {
        if (entityList != null)
            entityList.removeAll(entityList);
        if (keywordNameList != null)
            keywordNameList.removeAll(keywordNameList);
        if (features != null)
            features.removeAll(features);
        if (keywordList != null)
            keywordList.removeAll(keywordList);
        if (classesArrayList != null) {
            classesArrayList.removeAll(classesArrayList);
        }
        if (liClassNameList != null) {
            liClassNameList.removeAll(liClassNameList);
        }
        if (chosenLinks != null) {
            chosenLinks.removeAll(chosenLinks);
        }
        if (googleList != null) {
            googleList.removeAll(googleList);
        }
        if (yahooList != null) {
            yahooList.removeAll(yahooList);
        }
        selectedClassName = "";
        strResult = "";

        entityList = new ArrayList < MyEntity >();
        keywordNameList = new ArrayList < String >();
        features = new ArrayList < String >();
        keywordList = new ArrayList < com.tkorg.extraction.MyKeyword >();
        classesArrayList = new ArrayList<OWLNamedClass>();
        liClassNameList = new ArrayList<String>();
        chosenLinks = new ArrayList < MyKeyword >();
        googleList = new ArrayList < MyKeyword >();
        yahooList = new ArrayList < MyKeyword >();
    }
}
