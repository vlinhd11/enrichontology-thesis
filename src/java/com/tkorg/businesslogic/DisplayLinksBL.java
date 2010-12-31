/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.businesslogic;

import com.hp.hpl.jena.graph.query.SimpleQueryEngine.Cons;
import com.tkorg.download.Download;
import com.tkorg.util.Constants;
import com.yahoo.shopping.Cost;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author danhit
 */
public class DisplayLinksBL {

    public void downloadLinks() {
        Download download = new Download();
        File file = new File(Constants.PATH_FILES);

        if (!file.exists())
            file.mkdir();
        //Download Google.
        try {
            for (int i = 0; i < SearchOntologyBL.googleList.size(); i++) {
                for (int j = 0; j < SearchOntologyBL.googleList.get(i).getLinkList().size(); j++) {
                    download.downloadFileFromLink(SearchOntologyBL.googleList.get(i).getLinkList().get(j),
                            "google-" + SearchOntologyBL.googleList.get(i).getName() + "-" + (j + 1));
                    }
                }
        } catch (Exception e) {

        }

        //Download Yahoo.
        try {
            for (int i = 0; i < SearchOntologyBL.yahooList.size(); i++) {
                for (int j = 0; j < SearchOntologyBL.yahooList.get(i).getLinkList().size(); j++) {
                    download.downloadFileFromLink(SearchOntologyBL.yahooList.get(i).getLinkList().get(j),
                            "yahoo-" + SearchOntologyBL.yahooList.get(i).getName() + "-" + (j + 1));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
}
