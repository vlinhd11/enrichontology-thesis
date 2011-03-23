/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.extraction;

import com.tkorg.util.Constants;
import com.tkorg.util.Global;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
            for (int j = 0; j < temp.size(); j++) {
                file.getSentences().add(temp.get(j));
            }
            fileList.add(file);
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
        } else if (sentence.indexOf(keywordname) > -1) {
            String cha = sentence.substring(sentence.indexOf(keywordname), sentence.indexOf(keywordname) + 1);
            if (cha.equals(keywordname.substring(0, 1).toUpperCase())) {
                if ((sentence.indexOf(keywordname + "(") > -1) || (sentence.indexOf(keywordname + " (") > -1)) {
                    for (int j = 0; j < Constants.followwords.length; j++) {
                        int index02 = sentence.indexOf(")" + Constants.followwords[j]);
                        if (index02 > -1) {
                            individual = sentence.substring(index02 + Constants.followwords[j].length() + 1);
                            break;
                        }
                    }
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
                    String temp = extractSentence(fileList.get(i).getSentences().get(j), keyword.getName().replace(" ", "_"));
                    if (!temp.equals("")) {
                        keyword.getIndividuals().add(temp);
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
        Extraction_Main e = new Extraction_Main();
        String t = e.extractSentence("org Phần_mềm ( tiếng Anh : software ) là một tập_hợp những câu_lệnh được viết bằng một hoặc nhiều ngôn_ngữ_lập_trình theo một trật_tự xác_định nhằm tự_động thực_hiện một_số nhiệm_vụ hoặc chức_năng hoặc giải_quyết một bài_toán nào_đó",
                            "Phần mềm");
        JOptionPane.showMessageDialog(null, t);
    }
}
