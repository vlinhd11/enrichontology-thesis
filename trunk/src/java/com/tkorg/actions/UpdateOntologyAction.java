/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.actions;

import com.tkorg.businesslogic.UpdateOntologyBL;
import com.tkorg.forms.UpdateOntologyForm;
import com.tkorg.util.Global;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author DANHIT
 */
public class UpdateOntologyAction extends org.apache.struts.action.Action {
    
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

        UpdateOntologyForm updateOntologyForm = (UpdateOntologyForm) form;

        String[] item = updateOntologyForm.getItem();

        UpdateOntologyBL displayBL = new UpdateOntologyBL();
        if (Global.keywordList.size() > 0) {
            displayBL.choseItems(item);
            displayBL.inputOntology();

            boolean isExist = false;
            for (int i = 0; i < Global.keywordList.size(); i++) {
                if (Global.keywordList.get(i).getIndividuals().size() > 0) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                request.setAttribute("screen", "updateOnotlogy");
                request.setAttribute("isExist", true);
                return mapping.findForward(SUCCESS);
            } else {
                request.setAttribute("screen", "updateOnotlogy");
                request.setAttribute("isExist", false);
                return mapping.findForward(FAIL);
            }
        } else {
            request.setAttribute("screen", "updateOnotlogy");
            request.setAttribute("isExist", false);
            return mapping.findForward(FAIL);
        }
    }
}
