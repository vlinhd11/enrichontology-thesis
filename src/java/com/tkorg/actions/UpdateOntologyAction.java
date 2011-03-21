/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tkorg.actions;

import com.tkorg.businesslogic.DisplayLinksBL;
import com.tkorg.businesslogic.SearchOntologyBL;
import com.tkorg.businesslogic.UpdateOntologyBL;
import com.tkorg.extraction.Extraction_Main;
import com.tkorg.forms.UpdateOntologyForm;
import com.tkorg.search.ClassActions;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
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

        String screenid = updateOntologyForm.getScreenid();
        String processid = updateOntologyForm.getProcessid();
        String[] item = updateOntologyForm.getItem();

        if (screenid.equals("UPDATE_ONTOLOGY")) {
            if (processid.equals("UPDATE_ONTOLOGY_01")) {
                UpdateOntologyBL displayBL = new UpdateOntologyBL();

                displayBL.choseItems(item);
                displayBL.inputOntology();

                //reset.
                ClassActions.classesArrayList.removeAll(ClassActions.classesArrayList);
                Extraction_Main.keywordList.removeAll(Extraction_Main.keywordList);
                SearchOntologyBL.googleList.removeAll(SearchOntologyBL.googleList);
                SearchOntologyBL.yahooList.removeAll(SearchOntologyBL.yahooList);
                SearchOntologyBL.keywordNameList.removeAll(SearchOntologyBL.keywordNameList);
                DisplayLinksBL.chosenLinks.removeAll(DisplayLinksBL.chosenLinks);

                return mapping.findForward(SUCCESS);
            }
        }

        return mapping.findForward(FAIL);
    }
}
