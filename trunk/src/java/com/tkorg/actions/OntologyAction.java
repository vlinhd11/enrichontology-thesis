/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.actions;

import com.tkorg.businesslogic.OntologyBL;
import com.tkorg.forms.OntologyForm;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author DANHIT
 */
public class OntologyAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String SHOW_INDIVIDUALS = "showIndividuals";
    private static final String SHOW_VALUES = "showValues";
    
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

        response.setContentType("text/html");
        String processid = request.getParameter("processid");

        if (processid.equals("ONTOLOGY_01")) {
            String strNameClass = request.getParameter("content");
            OntologyBL ontologyBL = new OntologyBL();
            String strIndividuals = ontologyBL.getIndividuals(strNameClass);
            request.setAttribute("strIndividuals", strIndividuals);
            return mapping.findForward(SHOW_INDIVIDUALS);
        } else if (processid.equals("ONTOLOGY_02")) {
            String individual_name = request.getParameter("individual_name");
            OntologyBL ontologyBL = new OntologyBL();
            ontologyBL.showValues(individual_name);
            request.setAttribute("listProperty", OntologyBL.listProperty);
            request.setAttribute("listValue", OntologyBL.listValue);
            return mapping.findForward(SHOW_VALUES);
        }
        return mapping.findForward(FAIL);
    }
}
