/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.actions;

import com.tkorg.businesslogic.ITDocumentBL;
import com.tkorg.forms.ITDocumentForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author DANHIT
 */
public class ITDocumentAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
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

        ITDocumentForm itDocumentForm = (ITDocumentForm) form;

        String screenid = itDocumentForm.getScreenid();
        String processid = itDocumentForm.getProcessid();

        if (screenid.equals("IT_DOCUMENT")) {
            if (processid.equals("IT_DOCUMENT_01")) {
                ITDocumentBL displayBL = new ITDocumentBL();
                displayBL.extractWithKeywords();

                return mapping.findForward(SUCCESS);
            }
        }
        
        return mapping.findForward(SUCCESS);
    }
}
