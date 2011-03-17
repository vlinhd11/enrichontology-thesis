package com.tkorg.businesslogic;

import com.tkorg.entities.MyKeyword;
import com.tkorg.entities.OWLModel;
import com.tkorg.search.ClassActions;

import com.tkorg.search.SearchEnginesAction;
import com.tkorg.util.Constants;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

/**
 *
 * @author DANHIT
 */
public class SearchOntologyBL {

    public static ArrayList < MyKeyword > googleList = null;
    public static ArrayList < MyKeyword > yahooList = null;
    public static ArrayList < String > keywordNameList = null;

    private String path;
    private String result;

    public SearchOntologyBL() {
        googleList = new ArrayList < MyKeyword >();
        yahooList = new ArrayList < MyKeyword >();
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

        keywordNameList = new ArrayList<String>();
        String[] temp = query_string.split("-");
        for (int i = 0; i < temp.length; i++) {
            keywordNameList.add(temp[i].toString());
        }
        
        for (int i = 0; i < keywordNameList.size(); i++) {
            SearchEnginesAction queryAction = new SearchEnginesAction();

            keywordNameList.set(i, convertVN(keywordNameList.get(i)));
            if (google.equals(Constants.GOOGLE)) {
                try {
                    MyKeyword keyword = new MyKeyword();
                    keyword.setName(keywordNameList.get(i));
                    keyword.setLinkandTitle(queryAction.submitQueryToGoogle(keywordNameList.get(i) + " là gì", true, m_google));
                    googleList.add(keyword);
		} catch (NumberFormatException e) {
                    e.printStackTrace();
		} catch (InterruptedException ite) {
                    ite.printStackTrace();
                }
            }
            if (yahoo.equals(Constants.YAHOO)) {
                MyKeyword keyword = new MyKeyword();
                keyword.setName(keywordNameList.get(i));
                keyword.setLinkandTitle(queryAction.submitQueryToYahoo(keywordNameList.get(i) + " là gì", true, m_yahoo));
                yahooList.add(keyword);
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
                googleList.add(keyword);
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
            yahooList.add(keyword);
        }
    }

    public void loadOntology(String namePath) {
        if (ClassActions.classesArrayList == null) {
            URI uri = new File(namePath).toURI();

            OWLModel owlModel = new OWLModel();
            owlModel.loadOWLModelFromExistFile(uri);
            ClassActions.viewClasses();
	} else {
            ClassActions.convertClassesIntoTree();
	}
            this.result = ClassActions.strResult;
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
