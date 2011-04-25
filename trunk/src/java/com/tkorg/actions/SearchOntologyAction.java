/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.actions;

import com.tkorg.businesslogic.SearchOntologyBL;
import com.tkorg.entities.MyKeyword;
import com.tkorg.forms.SearchOntologyForm;
import com.tkorg.util.Constants;
import com.tkorg.util.Global;
import edu.stanford.smi.protege.server.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author danhit
 */
public class SearchOntologyAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SearchOntologyForm searchOntologyForm = (SearchOntologyForm) form;

	String screenid = searchOntologyForm.getScreenid();
	String processid = searchOntologyForm.getProcessid();

        Global g = new Global();

	if (screenid.equals("SEARCH_ONTOLOGY")) {
            if (processid.equals("SEARCH_ONTOLOGY_01")) {

                String query_string = searchOntologyForm.getQuery_string();
                String google = searchOntologyForm.getGoogle();
                String yahoo = searchOntologyForm.getYahoo();
                int m_google = Integer.parseInt(searchOntologyForm.getM_google());
                int m_yahoo = Integer.parseInt(searchOntologyForm.getM_yahoo());
                SearchOntologyBL searchOntologyBL = new SearchOntologyBL();
                HttpSession httpSession = request.getSession();

                searchOntologyBL.searchOntologyWithKeywords(query_string, google, m_google, yahoo, m_yahoo);

                for (int i = 0; i < Global.entityList.size(); i++) {
                    if (Global.entityList.get(i).getType().equals("google")) {
                        boolean isExist = false;
                        for (int j = 0; j < Global.googleList.size(); j++) {
                            if (Global.googleList.get(j).getName().equals(Global.entityList.get(i).getKeyword())) {
                                isExist = true;
                                Global.googleList.get(j).getLinkList().add(Global.entityList.get(i).getLink());
                                Global.googleList.get(j).getTitleList().add(Global.entityList.get(i).getTitle());
                                break;
                            }
                        }
                        if (isExist == false) {
                            MyKeyword keyword = new MyKeyword();
                            keyword.setName(Global.entityList.get(i).getKeyword());
                            keyword.getLinkList().add(Global.entityList.get(i).getLink());
                            keyword.getTitleList().add(Global.entityList.get(i).getTitle());
                            Global.googleList.add(keyword);
                        }
                    }
                    if (Global.entityList.get(i).getType().equals("yahoo")) {
                        boolean isExist = false;
                        for (int j = 0; j < Global.yahooList.size(); j++) {
                            if (Global.yahooList.get(j).getName().equals(Global.entityList.get(i).getKeyword())) {
                                isExist = true;
                                Global.yahooList.get(j).getLinkList().add(Global.entityList.get(i).getLink());
                                Global.yahooList.get(j).getTitleList().add(Global.entityList.get(i).getTitle());
                                break;
                            }
                        }
                        if (isExist == false) {
                            MyKeyword keyword = new MyKeyword();
                            keyword.setName(Global.entityList.get(i).getKeyword());
                            keyword.getLinkList().add(Global.entityList.get(i).getLink());
                            keyword.getTitleList().add(Global.entityList.get(i).getTitle());
                            Global.yahooList.add(keyword);
                        }
                    }
                }

                httpSession.setAttribute(Constants.GOOGLE_LIST, Global.googleList);
                httpSession.setAttribute(Constants.YAHOO_LIST, Global.yahooList);
                if (Constants.GOOGLE.equals(google)) {
                    httpSession.setAttribute(Constants.IS_GOOGLE, (Boolean)true);
                } else {
                    httpSession.setAttribute(Constants.IS_GOOGLE, (Boolean)false);
                }
                if (Constants.YAHOO.equals(yahoo)) {
                    httpSession.setAttribute(Constants.IS_YAHOO, (Boolean)true);
                } else {
                    httpSession.setAttribute(Constants.IS_YAHOO, (Boolean)false);
                }
                return mapping.findForward(SUCCESS);
            } else {
                return mapping.findForward(FAIL);
            }
        }
        return null;
    }
}
