/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.actions;

import com.tkorg.businesslogic.DisplayLinksBL;
import com.tkorg.util.Global;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.swing.JOptionPane;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author danhit
 */
public class DisplayLinksAction extends org.apache.struts.action.Action {
    
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

        DisplayLinksBL displayBL = new DisplayLinksBL();
        displayBL.downloadAndClassify();
        boolean isExist = false;
        for (int i = 0; i < Global.entityList.size(); i++) {
            if (Global.entityList.get(i).isIsChosen()) {
                isExist = true;
                break;
            }
        }
        if (isExist) {
            return mapping.findForward(SUCCESS);
        } else {
            request.setAttribute("screen", "classify");
            request.setAttribute("isExist", false);
            return mapping.findForward(FAIL);
        }
    }
}
