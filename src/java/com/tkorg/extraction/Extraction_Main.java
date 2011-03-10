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

    public ArrayList < com.tkorg.extraction.MyFile > fileList = null;
    public ArrayList < MyKeyword > keywordList = null;

    public Extraction_Main() {
        fileList = new ArrayList < com.tkorg.extraction.MyFile >();
        keywordList = new ArrayList < MyKeyword >();
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
                        if (!text.equals(""))
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

    public String extractSentence(String sentence, String keywordname) {
        String individual = "";
        for (int j = 0; j < Constants.removewords.length; j++) {
            if (sentence.toLowerCase().startsWith(Constants.removewords[j].toLowerCase())) {
                sentence = sentence.substring(Constants.removewords[j].length()).toString();
                if (sentence.startsWith(",")) {
                    sentence = sentence.substring(2);
                }
            }
        }

        if (sentence.toLowerCase().startsWith(keywordname) || sentence.toLowerCase().startsWith(" " + keywordname)) {
            for (int j = 0; j < Constants.followwords.length; j++) {
                if (sentence.matches(keywordname + Constants.followwords[j] + ".*") || sentence.matches(keywordname + " \\(.*\\)" + Constants.followwords[j] + ".*")) {
                    individual = sentence.substring(keywordname.length() + Constants.followwords[j].length() + 1);
                    break;
                }
            }
        } else if (sentence.endsWith(keywordname)) {
            for (int j = 0; j < Constants.forwardwords.length; j++) {
                if (sentence.endsWith(Constants.forwardwords[j] + keywordname)) {
                    individual = sentence.substring(0, sentence.length() - (Constants.forwardwords[j].length() + keywordname.length()));
                    break;
                }
            }
        }

        return individual;
    }

    public void extractWithKeywords(ArrayList < String > keywordnames) {
        for (int index = 0; index < keywordnames.size(); index++) {
            MyKeyword keyword = new MyKeyword();
            keyword.setName(keywordnames.get(index));
            for (int i = 0; i < fileList.size(); i++) {
                for (int j = 0; j < fileList.get(i).getSentences().size(); j++) {
                    String temp = extractSentence(fileList.get(i).getSentences().get(j), keyword.getName());
                    if (!temp.equals("")) {
                        keyword.getIndividuals().add(temp);
                    }
                }
            }
            keywordList.add(keyword);
        }
    }

    public ArrayList<MyFile> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<MyFile> fileList) {
        this.fileList = fileList;
    }

    public ArrayList<MyKeyword> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(ArrayList<MyKeyword> keywordList) {
        this.keywordList = keywordList;
    }

    public static void main(String[] args) {
        Extraction_Main m = new Extraction_Main();

        m.loadFiles();
        ArrayList < String > keywords = new ArrayList < String >();
        keywords.add("tin học");
        keywords.add("hệ điều hành mạng");
        m.extractWithKeywords(keywords);
        for (int i = 0; i < m.getKeywordList().size(); i++) {
            System.out.println(m.getKeywordList().get(i).getName());
            for (int j = 0; j < m.getKeywordList().get(i).getIndividuals().size(); j++) {
                System.out.println("    " + m.getKeywordList().get(i).getIndividuals().get(j));
            }
        }

//        for (int i = 0; i < m.getFileList().size(); i++) {
//            for (int j = 0; j < m.getFileList().get(i).getSentences().size(); j++) {
//                System.out.println(m.getFileList().get(i).getSentences().get(j));
//            }
//        }
    }
}
