/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.entities;

import java.util.ArrayList;

/**
 *
 * @author danhit
 */
public class MyKeyword {

    private String name;
    private ArrayList < String > linkList = null;
    private ArrayList < String > titleList = null;

    public MyKeyword() {
        name = "";
        linkList = new ArrayList < String >();
        titleList = new ArrayList < String >();
    }

    public void setLinkandTitle(ArrayList<String> linkList) {
        for (int i = 0; i < linkList.size(); i++) {
            String[] temp;
            temp = linkList.get(i).split("<br>");
            String temp1 = temp[0];
            String temp2 = temp[1];
            this.linkList.add(temp1);
            this.titleList.add(temp2);
        }
    }

    public ArrayList<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(ArrayList<String> titleList) {
        this.titleList = titleList;
    }

    public ArrayList<String> getLinkList() {
        return linkList;
    }

    public void setLinkList(ArrayList<String> linkList) {
        this.linkList = linkList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
