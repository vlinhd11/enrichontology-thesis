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
        <html:form action="/SearchOntologyAction">
            <html:hidden property="li_id" value=""/>
            <html:hidden property="screenid" value=""/>
            <html:hidden property="processid" value=""/>
            <html:hidden property="query_string" value=""/>
            
            <table border="1" width="<bean:message key="size.width" />" align="center" style="margin-top: -5px; padding: 0px">
                <tr>
                    <td width="35%" align="left" valign="top">
                        <h3>Classes</h3>
                        <input id="input_class" type="text" size="40" value="">&nbsp;
                        <input name="button01" type="button" onclick="chose('input_class', 'classListID', 'listID03')" value="Chọn">
                        <%--<div id="scroll_box">--%>
                            <jsp:useBean id="classes" class="com.tkorg.businesslogic.SearchOntologyBL" scope="page">
                                <jsp:getProperty name="classes" property="result" />
                            </jsp:useBean>
                        <%--</div>--%>
                    </td>
                    <td width="35%" align="center" valign="top" >
                        <img src="./css/searching.jpg" alt="" border="0" width="70%" height="70%" align="middle" />
                    </td>
                    <td width="30%" align="left" valign="top">
                        <h3><bean:message key="searchOntology.list" /></h3>
                        <select id="listID03" name="listquery" size="7" style="width: 100%;" />
                        <br />
                        <input type="button" value="Xóa một khái niệm" onclick="removeConcept('listID')" />
			<input type="button" value="Xóa tất cả" onclick="removeAll('listID')" />
                        <br /><br />
                        <h3><bean:message key="searchOntology.ChoseSearchEngine" /></h3>
			<html:checkbox property="google" value="google" >Google</html:checkbox>
			&nbsp;&nbsp;
                        <bean:message key="searchOntology.maxResult" />&nbsp;&nbsp;
			<html:select property="m_google" >
                            <html:option value="5" >5</html:option>
                            <html:option value="10" >10</html:option>
                            <html:option value="20" >20</html:option>
                            <html:option value="30" >30</html:option>
                            <html:option value="40" >40</html:option>
                            <html:option value="50" >50</html:option>
                        </html:select>
                        <br />
                        <html:checkbox property="yahoo" value="yahoo" >Yahoo</html:checkbox>
                        &nbsp;&nbsp;&nbsp;
                        <bean:message key="searchOntology.maxResult" />&nbsp;&nbsp;
                        <html:select property="m_yahoo" >
                            <html:option value="5" >5</html:option>
                            <html:option value="10" >10</html:option>
                            <html:option value="20" >20</html:option>
                            <html:option value="30" >30</html:option>
                            <html:option value="40" >40</html:option>
                            <html:option value="50" >50</html:option>
                        </html:select>
                        <br /><br />
                        <h3><bean:message key="searchOntology.radio.name" /></h3>
                        <html:radio property="my_property" value="individual"><bean:message key="searchOntology.radio.my_property.individual" /></html:radio>
                        <br />
                        <html:radio property="my_property" value="relation"><bean:message key="searchOntology.radio.my_property.relation" /></html:radio>
                        <br /><br />
                        <html:button property="btnSearch" onclick="submitForm(document.forms[0], 'SEARCH_ONTOLOGY', 'SEARCH_ONTOLOGY_01')"><bean:message key="searchOntology.submit" /></html:button>
                    </td>
                </tr>
            </table>
        </html:form>
    </body>
</html>
