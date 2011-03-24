/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.extraction;

import com.tkorg.util.Constants;

/**
 *
 * @author DANHIT
 */
public class Extraction_Thread extends Thread {

    private String sentence;
    private String keyword;
    private String individual;

    @Override
    public void run() {
        this.individual = extractSentence(this.sentence, this.keyword);
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

    public String getIndividual() {
        return individual;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
