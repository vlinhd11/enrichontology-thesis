/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.businesslogic;

import com.tkorg.download.Download;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author danhit
 */
public class DisplayLinksBL {

    Download down = null;

    public DisplayLinksBL() {
        down = new Download();
    }
    
    public void downloadLinks() {
        String url = "http://computerjobs.vn/huong-nghiep/kinh-doanh-ict/cong-ty-cntt";
        try {
            down.downloadFileFromLink(url, "danhit");
        } catch (IOException ex) {
            Logger.getLogger(DisplayLinksBL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Download Google.
//        try {
//            File file = new File(Constants.PATH_FILES);
//
//            if (!file.exists())
//                file.mkdir();
//            for (int i = 0; i < SearchOntologyBL.googleList.size(); i++) {
//                for (int j = 0; j < SearchOntologyBL.googleList.get(i).getLinkList().size(); j++) {
//                    download.downloadFileFromLink(SearchOntologyBL.googleList.get(i).getLinkList().get(j),
//                            "google-" + SearchOntologyBL.googleList.get(i).getName() + "-" + (j + 1));
//                    }
//                }
//        } catch (Exception e) {
//
//        }
//
//        //Download Yahoo.
//        try {
//            for (int i = 0; i < SearchOntologyBL.yahooList.size(); i++) {
//                for (int j = 0; j < SearchOntologyBL.yahooList.get(i).getLinkList().size(); j++) {
//                    download.downloadFileFromLink(SearchOntologyBL.yahooList.get(i).getLinkList().get(j),
//                            "yahoo-" + SearchOntologyBL.yahooList.get(i).getName() + "-" + (j + 1));
//                }
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.toString());
//        }
    }

    public static void main(String[] args) {
        DisplayLinksBL bl = new DisplayLinksBL();
        bl.downloadLinks();
    }
}
