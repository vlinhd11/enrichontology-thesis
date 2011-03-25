<%--
    Document   : itDocument
    Created on : Dec 29, 2010, 8:23:41 PM
    Author     : danhit
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>


<%@page import="com.tkorg.util.Constants" %>
<%@page import="com.tkorg.entities.MyKeyword" %>
<%@page import="com.tkorg.businesslogic.DisplayLinksBL" %>

<%@page import="java.io.IOException" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.tkorg.util.Global" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/mktree.css">
        <script type="text/javascript" src="./scripts/tree.js"></script>
        <script type="text/javascript" src="./scripts/keywordList.js"></script>
	<script type="text/javascript" src="./scripts/app.js"></script>
        <title><bean:message key="itDocument.title" /></title>
    </head>
    <body bgcolor="#FFFFFF">
        <h1><bean:message key="itDocument.heading" /></h1>
        
        <html:form action="/ITDocumentAction">
            <html:hidden property="screenid" value="" />
            <html:hidden property="processid" value="" />
            <table border="1" width="100%">
                <tr BGCOLOR="#FFF8C6">
                    <th width =\"3%\"><bean:message key="itDocument.no" /></th>
                    <th width =\"82%\"><bean:message key="itDocument.heading" /></th>
                </tr>
                <%
                    for(int i = 0; i < Global.keywordNameList.size(); i++) {
                %>
                <tr>
                    <td></td>
                    <td style="background-color: yellow" width="82%"><%=Global.entityList.get(i).getKeyword()%></td>
                </tr>
                <%
                        for (int j = 0; j < Global.entityList.size(); j++) {
                            if (Global.keywordNameList.get(i).equals(Global.entityList.get(j).getKeyword()) &&
                                    Global.entityList.get(j).isIsChosen() == true) {

                %>
                <tr>
                    <td width="3%"><%=Integer.toString(j - i + 1)%>.</td>
                    <td width="82%">
                        <a href="<%=Global.entityList.get(j).getLink()%>" target="_blank">
                            <%=Global.entityList.get(j).getLink()%>
                        </a>
                        <br>
                        <%=Global.entityList.get(j).getTitle()%>
                    </td>
                </tr>
            <%
                        }
                        }
                        }
            %>
            </table>
            <br>
            <html:button property="btnITDocument" onclick="submitForm(document.forms[0], 'IT_DOCUMENT', 'IT_DOCUMENT_01')" ><bean:message key="itDocument.submit" /></html:button>

        </html:form>

    </body>
</html>
