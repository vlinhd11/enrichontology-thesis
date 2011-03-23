/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.businesslogic;

import com.tkorg.extraction.Extraction_Main;
import com.tkorg.util.Global;

/**
 *
 * @author DANHIT
 */
public class ITDocumentBL {

    public void extractWithKeywords() {
        Extraction_Main extractMain = new Extraction_Main();
        extractMain.loadFiles();
        extractMain.extractWithKeywords(Global.keywordNameList);
    }
}
