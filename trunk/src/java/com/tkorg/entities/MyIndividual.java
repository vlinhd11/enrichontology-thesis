/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.entities;

/**
 *
 * @author DANHIT
 */
public class MyIndividual {

    private String content;
    private String link;
    private boolean  isChosen;

    public MyIndividual() {
        content = "";
        link = "";
        isChosen = true;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isIsChosen() {
        return isChosen;
    }

    public void setIsChosen(boolean isChosen) {
        this.isChosen = isChosen;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
