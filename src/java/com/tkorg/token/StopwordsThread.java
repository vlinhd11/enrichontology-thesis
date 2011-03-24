/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.token;

import java.util.ArrayList;

/**
 *
 * @author DANHIT
 */
public class StopwordsThread extends Thread {

    private String input;
    private String output;
    private ArrayList < String > stopwordList = null;

    @Override
    public void run() {
        this.output = remove(this.input);
    }

    public String remove(String content) {

        String[] temp = content.split(" ");
        content = "";
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < stopwordList.size(); j++) {
                if (temp[i].equals(stopwordList.get(j))) {
                    temp[i] = "";
                }
            }
        }
        for (int i = 0; i < temp.length; i++) {
            if (!temp[i].equals("")) {
                content = content + temp[i] + "\n";
            }
        }

        return content;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setStopwordList(ArrayList<String> stopwordList) {
        this.stopwordList = stopwordList;
    }
}
