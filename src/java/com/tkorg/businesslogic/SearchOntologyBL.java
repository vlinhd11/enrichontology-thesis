package com.tkorg.businesslogic;

import com.tkorg.entities.MyKeyword;
import com.tkorg.entities.OWLModel;
import com.tkorg.search.ClassActions;

import com.tkorg.search.SearchEnginesAction;
import com.tkorg.util.Constants;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author DANHIT
 */
public class SearchOntologyBL {

    public static ArrayList < MyKeyword > googleList = null;
    public static ArrayList < MyKeyword > yahooList = null;

    private String path;
    private String result;

    public SearchOntologyBL() {
        googleList = new ArrayList < MyKeyword >();
        yahooList = new ArrayList < MyKeyword >();
    }

    private String convertVN(String name) {

        for (int i = 0; i < Constants.unicodeWords.length; i++) {
            if (!Constants.unicodeWords[i].equals("")) {
                int index = name.indexOf(Constants.unicodeWords[i]);
                if (index != -1) {
                    name = name.substring(0, index) + Constants.utf8Words[i] + name.split(Constants.unicodeWords[i])[1];
                }
            }
        }

        return name;
    }

    public void searchOntologyWithIndividual(String query_string, String google, int m_google, String yahoo, int m_yahoo) {

        String[] keywordNameList = null;

        keywordNameList = query_string.split("-");
        
        for (int i = 0; i < keywordNameList.length; i++) {
            SearchEnginesAction queryAction = new SearchEnginesAction();
            String cntt = "cntt";

            keywordNameList[i] = convertVN(keywordNameList[i]);
            if (google.equals(Constants.GOOGLE)) {
                try {
                    MyKeyword keyword = new MyKeyword();
                    keyword.setName(keywordNameList[i]);
                    keyword.setLinkandTitle(queryAction.submitQueryToGoogle(keywordNameList[i] + " " + cntt, true, m_google));
                    googleList.add(keyword);
		} catch (NumberFormatException e) {
                    e.printStackTrace();
		} catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (yahoo.equals(Constants.YAHOO)) {
                MyKeyword keyword = new MyKeyword();
                keyword.setName(keywordNameList[i]);
                keyword.setLinkandTitle(queryAction.submitQueryToYahoo(keywordNameList[i]  + " " + cntt, true, m_yahoo));
                yahooList.add(keyword);
            }
        }
    }

    public void searchOntologyWithRelation(String query_string, String google, int m_google, String yahoo, int m_yahoo) {

        query_string = convertVN(query_string);
        query_string = query_string.replace("-", "+");

        SearchEnginesAction queryAction = new SearchEnginesAction();
        String cntt = "cntt";

        if (google.equals(Constants.GOOGLE)) {
            try {
                MyKeyword keyword = new MyKeyword();
                keyword.setName(query_string);
                keyword.setLinkandTitle(queryAction.submitQueryToGoogle(query_string + " " + cntt, true, m_google));
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
            keyword.setLinkandTitle(queryAction.submitQueryToYahoo(query_string  + " " + cntt, true, m_yahoo));
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

        String name = "CÃ´ng Ã¡i";
        JOptionPane.showMessageDialog(null, name);
        name = search.convertVN(name);

        JOptionPane.showMessageDialog(null, name);
    }
}
