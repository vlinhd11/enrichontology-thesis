/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.download;

import com.tkorg.util.Constants;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danhit
 */
public class Download {

    public void downloadFileFromLink(String url, String title) throws IOException {
        String type=".html";
        if(url.substring(url.length()- 4,url.length()- 3).equals(".")
                && (!url.substring(url.length() - 3, url.length()).equals("pdf"))
                && (!url.substring(url.length() - 3, url.length()).equals("doc"))) {
            type=url.substring(url.length()- 4);
        }

        BufferedInputStream in = new BufferedInputStream(new java.net.URL(url).openStream());
        FileOutputStream fos = new FileOutputStream(Constants.PATH_FILES + "\\" + title + type);
        BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
        byte[] data = new byte[1024];
        int x = 0;
        while((x = in.read(data, 0, 1024)) >= 0) {
            bout.write(data, 0, x);
        }
        bout.close();
        in.close();
    }

    public static void main(String[] args) {
        try {
            Download down = new Download();
            down.downloadFileFromLink("http://www.baohylabel.com/", "công ty-1");
            down.downloadFileFromLink("http://www.blogcatalog.com/blogs/make-money-to-free", "công ty-2");
        } catch (IOException ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
