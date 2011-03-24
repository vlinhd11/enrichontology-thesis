/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.entities;

/**
 *
 * @author DANHIT
 */
public class MyEntity {

    private String keyword;
    private String type;
    private String link;
    private String title;
    private String content;
    private boolean isChosen;
    private String contentAfterRemoveStopwords;

    public MyEntity() {
        keyword = "";
        type = "";
        link = "";
        title = "";
        content = "";
        isChosen = false;
        contentAfterRemoveStopwords = "";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentAfterRemoveStopwords() {
        return contentAfterRemoveStopwords;
    }

    public void setContentAfterRemoveStopwords(String contentAfterRemoveStopwords) {
        this.contentAfterRemoveStopwords = contentAfterRemoveStopwords;
    }

    public boolean isIsChosen() {
        return isChosen;
    }

    public void setIsChosen(boolean isChosen) {
        this.isChosen = isChosen;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
