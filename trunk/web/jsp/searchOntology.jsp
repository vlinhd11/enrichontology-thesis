<%-- 
    Document   : searchOntology
    Created on : Dec 27, 2010, 8:23:13 PM
    Author     : danhit
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.tkorg.util.Constants" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/mktree.css">
        <script type="text/javascript" src="./scripts/tree.js"></script>
        <script type="text/javascript" src="./scripts/keywordList.js"></script>
	<script type="text/javascript" src="./scripts/app.js"></script>
        <title><bean:message key="searchOntology.title" /></title>
    </head>
    <body>
        <h1 class="color" style="text-align: center;"><bean:message key="searchOntology.heading" /></h1>
        
        <html:form action="/SearchOntologyAction">
            <table border="1" cellpadding="10" width="90%" >
                <tr>
                    <td width="30%" align="left" valign="top" style="background-color: rgb(255, 248, 198);">
                        <h3><bean:message key="searchOntology.ontology" /></h3>
                        <h5><bean:message key="searchOntology.addConcept" /></h5>
			<html:hidden property="li_id" value=""/>
			<html:hidden property="screenid" value=""/>
			<html:hidden property="processid" value=""/>
			<html:hidden property="query_string" value=""/>
                        <div id="scroll_box">
                            <jsp:useBean id="ontology" class="com.tkorg.businesslogic.SearchOntologyBL" scope="session">
                                <%
                                  String namePath = Constants.PATH_ONTOLOGY;
                                %>
                                <jsp:setProperty name="ontology" property="path" value="<%=namePath%>" />
                                <jsp:getProperty name="ontology" property="result" />
                            </jsp:useBean>
                        </div>
                    </td>
                    <td width="30%" align="left" valign="top">
                        <h3>Concepts</h3>
			<select id="listID" name="listquery" size="20" style="width: 90%;" />
                        <br /><br /><br />
                        <input type="button" value="Remove" onclick="removeConcept('listID')" />
			<input type="button" value="Remove All" onclick="removeAll('listID')" />
                    </td>
                    <td width="30%" align="left" valign="top">
			<h3>Select Search Engine</h3>
			<html:checkbox property="google" value="google" >Google</html:checkbox>
			&nbsp;&nbsp;
			Max result&nbsp;&nbsp;
			<html:select property="m_google" >
                            <html:option value="2" >2</html:option>
                            <html:option value="10" >10</html:option>
                            <html:option value="20" >20</html:option>
                            <html:option value="30" >30</html:option>
                            <html:option value="40" >40</html:option>
                            <html:option value="50" >50</html:option>
                        </html:select>
                        <br />
                        <br />
                        <html:checkbox property="yahoo" value="yahoo" >Yahoo</html:checkbox>
                        &nbsp;&nbsp;
                        Max result&nbsp;&nbsp;
                        <html:select property="m_yahoo" >
                            <html:option value="2" >2</html:option>
                            <html:option value="10" >10</html:option>
                            <html:option value="20" >20</html:option>
                            <html:option value="30" >30</html:option>
                            <html:option value="40" >40</html:option>
                            <html:option value="50" >50</html:option>
                        </html:select>
                        <br /><br /><br /><br />
                       
                        <html:radio property="my_property" value="individual"><bean:message key="searchOntology.radio.my_property.individual" /></html:radio>
                        <html:radio property="my_property" value="relation"><bean:message key="searchOntology.radio.my_property.relation" /></html:radio>
                        <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
                        <html:button property="button01" onclick="submitForm(document.forms[0], 'SEARCH_ONTOLOGY', 'SEARCH_ONTOLOGY_01')">Submit Query</html:button>
                    </td>
                </tr>
            </table>
        </html:form>
    </body>
</html>
