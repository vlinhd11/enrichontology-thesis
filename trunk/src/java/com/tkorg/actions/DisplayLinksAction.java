/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.actions;

import com.tkorg.businesslogic.DisplayLinksBL;
import com.tkorg.download.Download;
import com.tkorg.forms.DisplayLinksForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import vn.hus.nlp.tokenizer.TokenizerProvider;

/**
 *
 * @author danhit
 */
public class DisplayLinksAction extends org.apache.struts.action.Action {
    
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

        DisplayLinksForm displayLinksForm = (DisplayLinksForm) form;

        String screenid = displayLinksForm.getScreenid();
        String processid = displayLinksForm.getProcessid();

        if (screenid.equals("ERROR")) {
            if (processid.equals("ERROR_01")) {
                DisplayLinksBL displayBL = new DisplayLinksBL();
                displayBL.downloadLinks();
                
                return mapping.findForward(SUCCESS);
            }
        }
        return mapping.findForward(SUCCESS);
    }
}
