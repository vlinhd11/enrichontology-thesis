package com.tkorg.businesslogic;

import com.tkorg.entities.MyEntity;
import com.tkorg.entities.MyKeyword;
import com.tkorg.entities.OWLModel;
import com.tkorg.search.ClassActions;

import com.tkorg.search.SearchEnginesAction;
import com.tkorg.util.Constants;

import com.tkorg.util.Global;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author DANHIT
 */
public class SearchOntologyBL {

    private String result;

    public SearchOntologyBL() {
        
    }

    public static String convertVN(String name) {
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
        Global.init();
        ClassActions.viewClasses();
        this.result = Global.strResult;
    }

    public void loadClasses() {
        Collection classList = OWLModel.owlModel.getUserDefinedOWLNamedClasses();
        ArrayList < String > list = new ArrayList < String >();
        for (Iterator it = classList.iterator(); it.hasNext();) {
            OWLNamedClass c = (OWLNamedClass) it.next();
            list.add(c.getBrowserText().replaceAll("_", " "));
        }

        this.result = "";
        this.result += "<select id=\"classListID\" onchange=\"addConcept(this.value, 'listID03')\" size=\"25\" style=\"width: 100%;\">";
        for (int i = 0; i < list.size(); i++) {
            this.result += "<option value=\""+ list.get(i) +"\">"+ list.get(i) +"</option>";
        }
        this.result += "</select>";
    }

    public String getResult() {
        loadClasses();
        return result;
    }
}
