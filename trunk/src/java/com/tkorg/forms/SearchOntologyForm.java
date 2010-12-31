/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author danhit
 */
public class SearchOntologyForm extends org.apache.struts.action.ActionForm {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String li_id;
	private String screenid;
	private String processid;
	private String query_string;
	private String yahoo;
	private String m_yahoo;
	private String google;
	private String m_google;
        private String my_property;

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public String getLi_id() {
        return li_id;
    }

    public void setLi_id(String li_id) {
        this.li_id = li_id;
    }

    public String getM_google() {
        return m_google;
    }

    public void setM_google(String m_google) {
        this.m_google = m_google;
    }

    public String getM_yahoo() {
        return m_yahoo;
    }

    public void setM_yahoo(String m_yahoo) {
        this.m_yahoo = m_yahoo;
    }

    public String getMy_property() {
        return my_property;
    }

    public void setMy_property(String my_property) {
        this.my_property = my_property;
    }

    public String getProcessid() {
        return processid;
    }

    public void setProcessid(String processid) {
        this.processid = processid;
    }

    public String getQuery_string() {
        return query_string;
    }

    public void setQuery_string(String query_string) {
        this.query_string = query_string;
    }

    public String getScreenid() {
        return screenid;
    }

    public void setScreenid(String screenid) {
        this.screenid = screenid;
    }

    public String getYahoo() {
        return yahoo;
    }

    public void setYahoo(String yahoo) {
        this.yahoo = yahoo;
    }

    /**
     *
     */
    public SearchOntologyForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getGoogle() == null || getGoogle().length() < 1) {
            setGoogle("");
        }
        if (getYahoo() == null || getYahoo().length() < 1) {
            setYahoo("");
        }
        return errors;
    }
}
