/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.util;

import com.tkorg.entities.MyEntity;
import com.tkorg.entities.MyKeyword;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import java.util.ArrayList;
import vn.hus.nlp.tokenizer.VietTokenizer;

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
    public static int max_size = 70;
    public static VietTokenizer vietToken = null;

    public static boolean isFirst = true;

    public Global() {
        init();
    }

    public static void init() {
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

    public static String checkSize(String strIn) {
        String strOut = "";
        if (strIn.length() > max_size) {
            int size = strIn.length();
            String content = strIn;
            while (size > max_size) {
                strOut += content.substring(0, max_size) + "<br>";
                content = content.substring(max_size, content.length());
                size = content.length();
            }
            strOut += content;
        } else {
            strOut = strIn;
        }
        return strOut;
    }
}
