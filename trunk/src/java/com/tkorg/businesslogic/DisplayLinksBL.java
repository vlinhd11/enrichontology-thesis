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
                for (int j = 0; j < Global.chosenLinks.size(); j++) {
                    if (temp[1].equals(Global.chosenLinks.get(j).getName())) {
                        isExist = true;
                    }
                }

                if (!isExist) {
                    MyKeyword keyword = new MyKeyword();
                    if (temp[0].equals("google")) {
                        for (int k = 0; k < Global.googleList.size(); k++) {
                            if (temp[1].equals(Global.googleList.get(k).getName())) {
                                keyword.setName(temp[1]);
                                keyword.getLinkList().add(Global.googleList.get(k).getLinkList().get(index));
                                keyword.getTitleList().add(Global.googleList.get(k).getTitleList().get(index));
                                break;
                            }
                        }
                    } else if (temp[0].equals("yahoo")) {
                        for (int k = 0; k < Global.yahooList.size(); k++) {
                            if (temp[1].equals(Global.yahooList.get(k).getName())) {
                                keyword.setName(temp[1]);
                                keyword.getLinkList().add(Global.yahooList.get(k).getLinkList().get(index));
                                keyword.getTitleList().add(Global.yahooList.get(k).getTitleList().get(index));
                                break;
                            }
                        }
                    }
                    Global.chosenLinks.add(keyword);
                } else {
                    for (int j = 0; j < Global.chosenLinks.size(); j++) {
                        if (temp[1].equals(Global.chosenLinks.get(j).getName())) {
                            if (temp[0].equals("google")) {
                                for (int k = 0; k < Global.googleList.size(); k++) {
                                    if (temp[1].equals(Global.googleList.get(k).getName())) {
                                        Global.chosenLinks.get(j).getLinkList().add(Global.googleList.get(k).getLinkList().get(index));
                                        Global.chosenLinks.get(j).getTitleList().add(Global.googleList.get(k).getTitleList().get(index));
                                        break;
                                    }
                                }
                            } else if (temp[0].equals("yahoo")) {
                                for (int k = 0; k < Global.yahooList.size(); k++) {
                                    if (temp[1].equals(Global.yahooList.get(k).getName())) {
                                        Global.chosenLinks.get(j).getLinkList().add(Global.yahooList.get(k).getLinkList().get(index));
                                        Global.chosenLinks.get(j).getTitleList().add(Global.yahooList.get(k).getTitleList().get(index));
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
