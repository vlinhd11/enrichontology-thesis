/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.extraction;

import com.tkorg.util.Constants;
import com.tkorg.util.Global;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DANHIT
 */
public class Extraction_Main {

    public ArrayList < com.tkorg.extraction.MyFile > fileList = null;

    public Extraction_Main() {
        fileList = new ArrayList < com.tkorg.extraction.MyFile >();
    }

    public void loadFiles() {
        for (int i = 0; i < Global.entityList.size(); i++) {
            ArrayList < String > temp = new ArrayList < String >();
            for (int j = 0, index = 0; j < Global.entityList.get(i).getContent().length() - 1; j++) {
                for (int k = 0; k < Constants.words.length; k++) {
                    if (Global.entityList.get(i).getContent().substring(j, j + 1).equals(Constants.words[k])) {
                        String str = Global.entityList.get(i).getContent().substring(index, j);
                        if (!str.equals(""))
                            temp.add(str);
                        index = j + 1;
                        break;
                    }
                }
            }
            MyFile file = new MyFile();
            file.setName(Global.entityList.get(i).getTitle());
            file.setLink(Global.entityList.get(i).getLink());
            for (int j = 0; j < temp.size(); j++) {
                file.getSentences().add(temp.get(j));
            }
            fileList.add(file);
        }
    }

    public void extractWithKeywords(ArrayList < String > keywordnames) {
        for (int index = 0; index < keywordnames.size(); index++) {
            MyKeyword keyword = new MyKeyword();
            keyword.setName(keywordnames.get(index));
            for (int i = 0; i < fileList.size(); i++) {
                for (int j = 0; j < fileList.get(i).getSentences().size(); j++) {
                    try {
                        Thread extraction = new Extraction_Thread();
                        ((Extraction_Thread) extraction).setSentence(fileList.get(i).getSentences().get(j));
                        ((Extraction_Thread) extraction).setKeyword(keyword.getName().replace(" ", "_"));
                        ((Extraction_Thread) extraction).start();
                        ((Extraction_Thread) extraction).join();
                        String temp = ((Extraction_Thread) extraction).getIndividual();
                        ((Extraction_Thread) extraction).stop();
                        if (!temp.equals("")) {
                            keyword.getLinks().add(fileList.get(i).getLink());
                            keyword.getIndividuals().add(temp);
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Extraction_Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            Global.keywordList.add(keyword);
        }
    }

    public ArrayList<MyFile> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<MyFile> fileList) {
        this.fileList = fileList;
    }

    public static void main(String[] args) {
        
    }
}
