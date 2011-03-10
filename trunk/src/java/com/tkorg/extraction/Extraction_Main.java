/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.extraction;

import com.tkorg.util.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DANHIT
 */
public class Extraction_Main {

    ArrayList < com.tkorg.extraction.MyFile > fileList = null;

    public Extraction_Main() {
        fileList = new ArrayList < com.tkorg.extraction.MyFile >();
    }

    public void loadFiles() {
        File root_file = new File(Constants.PATH_SVM_CLASSIFY_CHOSENFILES);

        File[] files = root_file.listFiles();
        for (int i = 0; i < files.length; i++) {
            int index01 = files[i].getPath().indexOf(".svn");
            if (index01 == -1) {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(files[i]), "UTF8"));
                    String text = "";
                    String content = "";
                    while ((text = reader.readLine()) != null) {
                        content = content + text + "\n";
                    }
                    ArrayList < String > temp = new ArrayList < String >();
                    for (int j = 0, index = 0; j < content.length() - 1; j++) {
                        for (int k = 0; k < Constants.words.length; k++) {
                            if (content.substring(j, j + 1).equals(Constants.words[k])) {
                                String str = content.substring(index, j);
                                if (!str.equals(""))
                                    temp.add(str);
                                index = j + 1;
                                break;
                            }
                        }
                    }
                    MyFile file = new MyFile();
                    file.setName(files[i].getName());
                    for (int j = 0; j < temp.size(); j++) {
                        file.getSentences().add(temp.get(j));
                    }
                    fileList.add(file);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Extraction_Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Extraction_Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public ArrayList<MyFile> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<MyFile> fileList) {
        this.fileList = fileList;
    }

    public static void main(String[] args) {
        Extraction_Main m = new Extraction_Main();

        m.loadFiles();
        for (int i = 0; i < m.getFileList().size(); i++) {
            for (int j = 0; j < m.getFileList().get(i).getSentences().size(); j++) {
                System.out.println(m.getFileList().get(i).getSentences().get(j));
            }
        }
    }
}
