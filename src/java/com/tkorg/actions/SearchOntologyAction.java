/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.actions;

import com.tkorg.businesslogic.SearchOntologyBL;
import com.tkorg.forms.SearchOntologyForm;
import com.tkorg.util.Constants;
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
    private static final String SEARCH_ONTOLOGY_01 = "searchOntology01";
    private static final String SEARCH_ONTOLOGY_02 = "searchOntology02";
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

	if (screenid.equals("SEARCH_ONTOLOGY")) {
            if (processid.equals("SEARCH_ONTOLOGY_01")) {

                String query_string = searchOntologyForm.getQuery_string();
                String google = searchOntologyForm.getGoogle();
                String yahoo = searchOntologyForm.getYahoo();
                int m_google = Integer.parseInt(searchOntologyForm.getM_google());
                int m_yahoo = Integer.parseInt(searchOntologyForm.getM_yahoo());
                String my_property = searchOntologyForm.getMy_property();
                SearchOntologyBL searchOntologyBL = new SearchOntologyBL();
                HttpSession httpSession = request.getSession();
                
                if ("individual".equals(my_property)) {
                    searchOntologyBL.searchOntologyWithKeywords(query_string, google, m_google, yahoo, m_yahoo);
                    httpSession.setAttribute(Constants.GOOGLE_LIST, SearchOntologyBL.googleList);
                    httpSession.setAttribute(Constants.YAHOO_LIST, SearchOntologyBL.yahooList);
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
                    return mapping.findForward(SEARCH_ONTOLOGY_01);
                }
                else if ("relation".equals(my_property)) {
                    searchOntologyBL.searchOntologyWithRelation(query_string, google, m_google, yahoo, m_yahoo);
                    httpSession.setAttribute(Constants.GOOGLE_LIST, SearchOntologyBL.googleList);
                    httpSession.setAttribute(Constants.YAHOO_LIST, SearchOntologyBL.yahooList);
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
                    return mapping.findForward(SEARCH_ONTOLOGY_02);
                }
            } else {
                return mapping.findForward(FAIL);
            }
        }
        return null;
    }
}
