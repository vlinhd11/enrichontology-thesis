/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.actions;

import com.tkorg.businesslogic.WelcomeBL;
import com.tkorg.util.Global;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import vn.hus.nlp.tokenizer.VietTokenizer;

/**
 *
 * @author DANHIT
 */
public class BTAction extends org.apache.struts.action.Action {
    
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

        Global.init();
        Global.vietToken = new VietTokenizer();
        WelcomeBL welcomeBL = new WelcomeBL();
        welcomeBL.initOntology();
        Global.isFirst = false;
        
        return mapping.findForward(SUCCESS);
    }
}
