/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.businesslogic;

import com.tkorg.entities.MyKeyword;
import com.tkorg.svm.classify.Classify_Main;
import java.io.File;
import java.util.ArrayList;
import com.tkorg.util.Constants;

/**
 *
 * @author danhit
 */
public class DisplayLinksBL {

    public static ArrayList < MyKeyword > chosenLinks = null;

    public DisplayLinksBL() {
        chosenLinks = new ArrayList < MyKeyword >();
    }

    public void downloadAndClassify() {
        Classify_Main classify = new Classify_Main();

        classify.seperateWordsForClassify();
        classify.removeStopwordsForClassify();
        classify.calculateTFIDF();
        classify.useSVMTest();
        classify.choseFile();
    }

    public void choseLinks() {
        File rootFile = new File(Constants.PATH_SVM_CLASSIFY_CHOSENFILES);
        File[] files = rootFile.listFiles();

        for (int i = 0; i < files.length; i++) {
            int index01 = files[i].getPath().indexOf(".svn");
            int index = 0;
            boolean isExist = false;
            String[] temp = null;
            if (index01 == -1) {
                String t = files[i].getName();
                temp = t.split("-");
                index = Integer.parseInt(temp[2].toString().substring(0, temp[2].length() - 4)) - 1;
                for (int j = 0; j < chosenLinks.size(); j++) {
                    if (temp[1].equals(chosenLinks.get(j).getName())) {
                        isExist = true;
                    }
                }

                if (!isExist) {
                    MyKeyword keyword = new MyKeyword();
                    if (temp[0].equals("google")) {
                        for (int k = 0; k < SearchOntologyBL.googleList.size(); k++) {
                            if (temp[1].equals(SearchOntologyBL.googleList.get(k).getName())) {
                                keyword.setName(temp[1]);
                                keyword.getLinkList().add(SearchOntologyBL.googleList.get(k).getLinkList().get(index));
                                keyword.getTitleList().add(SearchOntologyBL.googleList.get(k).getTitleList().get(index));
                                break;
                            }
                        }
                    } else if (temp[0].equals("yahoo")) {
                        for (int k = 0; k < SearchOntologyBL.yahooList.size(); k++) {
                            if (temp[1].equals(SearchOntologyBL.yahooList.get(k).getName())) {
                                keyword.setName(temp[1]);
                                keyword.getLinkList().add(SearchOntologyBL.yahooList.get(k).getLinkList().get(index));
                                keyword.getTitleList().add(SearchOntologyBL.yahooList.get(k).getTitleList().get(index));
                                break;
                            }
                        }
                    }
                    chosenLinks.add(keyword);
                } else {
                    for (int j = 0; j < chosenLinks.size(); j++) {
                        if (temp[1].equals(chosenLinks.get(j).getName())) {
                            if (temp[0].equals("google")) {
                                for (int k = 0; k < SearchOntologyBL.googleList.size(); k++) {
                                    if (temp[1].equals(SearchOntologyBL.googleList.get(k).getName())) {
                                        chosenLinks.get(j).getLinkList().add(SearchOntologyBL.googleList.get(k).getLinkList().get(index));
                                        chosenLinks.get(j).getTitleList().add(SearchOntologyBL.googleList.get(k).getTitleList().get(index));
                                        break;
                                    }
                                }
                            } else if (temp[0].equals("yahoo")) {
                                for (int k = 0; k < SearchOntologyBL.yahooList.size(); k++) {
                                    if (temp[1].equals(SearchOntologyBL.yahooList.get(k).getName())) {
                                        chosenLinks.get(j).getLinkList().add(SearchOntologyBL.yahooList.get(k).getLinkList().get(index));
                                        chosenLinks.get(j).getTitleList().add(SearchOntologyBL.yahooList.get(k).getTitleList().get(index));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
