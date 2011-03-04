/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.businesslogic;

/**
 *
 * @author danhit
 */
public class DisplayLinksBL {

    public DisplayLinksBL() {
    }

    public void downloadAndClassify() {
        com.tkorg.svm.classify.Main classify = new com.tkorg.svm.classify.Main();

        classify.seperateWordsForClassify();
        classify.removeStopwords();
        classify.calculateTFIDF();
        classify.useSVMTest();
        classify.choseFile();
    }
}
