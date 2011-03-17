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
 * @author DANHIT
 */
public class UpdateOntologyForm extends org.apache.struts.action.ActionForm {
    
    private String screenid;
    private String processid;
    private String[] item;

    public String[] getItem() {
        return item;
    }

    public void setItem(String[] item) {
        this.item = item;
    }

    public String getProcessid() {
        return processid;
    }

    public void setProcessid(String processid) {
        this.processid = processid;
    }

    public String getScreenid() {
        return screenid;
    }

    public void setScreenid(String screenid) {
        this.screenid = screenid;
    }

    /**
     *
     */
    public UpdateOntologyForm() {
        super();
        this.item = new String[500];
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
        return errors;
    }
}
