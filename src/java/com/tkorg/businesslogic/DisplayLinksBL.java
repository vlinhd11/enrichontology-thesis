/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.businesslogic;

import com.tkorg.svm.classify.Classify_Main;

/**
 *
 * @author danhit
 */
public class DisplayLinksBL {

    public DisplayLinksBL() {
    }

    public void downloadAndClassify() {
        Classify_Main classify = new Classify_Main();

        classify.seperateWordsForClassify();
        classify.removeStopwords();
        classify.calculateTFIDF();
        classify.useSVMTest();
        classify.choseFile();
    }
}
