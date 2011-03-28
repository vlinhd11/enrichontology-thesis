/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.businesslogic;

import com.tkorg.entities.MyKeyword;
import com.tkorg.svm.classify.Classify_Main;
import java.io.File;
import com.tkorg.util.Constants;
import com.tkorg.util.Global;

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
        classify.removeStopwordsForClassify();
        classify.calculateTFIDF();
        classify.useSVMTest();
        classify.choseFile();
    }
}
