/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.actions;

import com.tkorg.businesslogic.ITDocumentBL;
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
public class ITDocumentAction extends org.apache.struts.action.Action {
    
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

        ITDocumentBL displayBL = new ITDocumentBL();
        displayBL.extractWithKeywords();

        if (Global.keywordList.get(0).getIndividuals().size() > 0) {
            return mapping.findForward(SUCCESS);
        } else {
            request.setAttribute("screen", "extraction");
            request.setAttribute("isExist", false);
            return mapping.findForward(FAIL);
        }
    }
}
