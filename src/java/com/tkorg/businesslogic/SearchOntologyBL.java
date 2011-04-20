package com.tkorg.businesslogic;

import com.tkorg.entities.MyEntity;
import com.tkorg.entities.MyKeyword;
import com.tkorg.entities.OWLModel;
import com.tkorg.search.ClassActions;

import com.tkorg.search.SearchEnginesAction;
import com.tkorg.util.Constants;

import com.tkorg.util.Global;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;

/**
 *
 * @author DANHIT
 */
public class SearchOntologyBL {

    private String path;
    private String result;

    public SearchOntologyBL() {
        
    }

    private String convertVN(String name) {
        for (int i = 0; i < Constants.unicodeWords.length; i++) {
            if (!Constants.unicodeWords[i].equals("")) {
                String strTemp = "";
                strTemp = name;
                String[] temp = strTemp.split(Constants.unicodeWords[i]);
                if (temp[0].length() < name.length()) {
                    strTemp = "";
                    for (int j = 0; j < temp.length; j++) {
                        if (j < temp.length - 1) {
                            strTemp = strTemp + temp[j] + Constants.utf8Words[i];
                        } else {
                            strTemp = strTemp + temp[j];
                        }
                    }
                    name = strTemp;
                }
            }
        }

        return name;
    }

    public void searchOntologyWithKeywords(String query_string, String google, int m_google, String yahoo, int m_yahoo) {

        Global.keywordNameList = new ArrayList < String >();
        String[] temp01 = query_string.split("-");
        for (int i = 0; i < temp01.length; i++) {
            Global.keywordNameList.add(temp01[i].toString() + ".");
        }

        SearchEnginesAction queryAction = new SearchEnginesAction();
        for (int i = 0; i < Global.keywordNameList.size(); i++) {
            String strVN = convertVN(Global.keywordNameList.get(i));
            Global.keywordNameList.set(i, strVN.substring(0, strVN.length() - 1));

            if (google.equals(Constants.GOOGLE)) {
                try {
                    ArrayList < String > linkList = queryAction.submitQueryToGoogle(Global.keywordNameList.get(i) + " là", true, m_google);
                    for (int j = 0; j < linkList.size(); j++) {
                        MyEntity entity = new MyEntity();
                        entity.setKeyword(Global.keywordNameList.get(i));
                        String[] temp02 =linkList.get(j).split("<br>");
                        String link = temp02[0];
                        String title = temp02[1];
                        entity.setLink(link);
                        entity.setTitle(title);
                        entity.setType("google");
                        Global.entityList.add(entity);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
		} catch (InterruptedException ite) {
                    ite.printStackTrace();
                }
            }
            if (yahoo.equals(Constants.YAHOO)) {
                ArrayList<String> linkList = queryAction.submitQueryToYahoo(Global.keywordNameList.get(i) + " là", true, m_yahoo);
                for (int j = 0; j < linkList.size(); j++) {
                    MyEntity entity = new MyEntity();
                    entity.setKeyword(Global.keywordNameList.get(i));
                    String[] temp02 =linkList.get(j).split("<br>");
                    String link = temp02[0];
                    String title = temp02[1];
                    entity.setLink(link);
                    entity.setTitle(title);
                    entity.setType("yahoo");
                    Global.entityList.add(entity);
                }
            }
        }
    }

    public void searchOntologyWithRelation(String query_string, String google, int m_google, String yahoo, int m_yahoo) {

        query_string = convertVN(query_string);
        query_string = query_string.replace("-", "+");

        SearchEnginesAction queryAction = new SearchEnginesAction();

        if (google.equals(Constants.GOOGLE)) {
            try {
                MyKeyword keyword = new MyKeyword();
                keyword.setName(query_string);
                keyword.setLinkandTitle(queryAction.submitQueryToGoogle(query_string, true, m_google));
                Global.googleList.add(keyword);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (yahoo.equals(Constants.YAHOO)) {
            MyKeyword keyword = new MyKeyword();
            keyword.setName(query_string);
            keyword.setLinkandTitle(queryAction.submitQueryToYahoo(query_string, true, m_yahoo));
            Global.yahooList.add(keyword);
        }
    }

    public void loadOntology(String namePath) {
        URI uri = new File(namePath).toURI();

        OWLModel owlModel = new OWLModel();
        owlModel.loadOWLModelFromExistFile(uri);
        ClassActions.viewClasses();
        this.result = Global.strResult;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        loadOntology(path);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static void main(String args[]) {
        SearchOntologyBL search = new SearchOntologyBL();

        String name = "Khái niá»m thuá»c ngÃ nh cÃ´ng nghá» thÃ´ng tin";
        name = search.convertVN(name);
    }
}
