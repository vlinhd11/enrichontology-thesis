<%-- 
    Document   : result
    Created on : 25-03-2011, 12:02:03
    Author     : DANHIT
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/mktree.css">
        <script type="text/javascript" src="./scripts/tree.js"></script>
        <script type="text/javascript" src="./scripts/keywordList.js"></script>
	<script type="text/javascript" src="./scripts/app.js"></script>
        <title><bean:message key="result.title" /></title>
    </head>
    <body>
        <html:form action="/ResultAction">
            <html:hidden property="screenid" value="" />
            <html:hidden property="processid" value="" />
            
            <table align="center" border="0">
                <tr>
                    <td align="center">
                        <%
                            boolean isExist = (Boolean) request.getAttribute("isExist");
                            String screen = (String) request.getAttribute("screen");
                            if (screen.equals("updateOnotlogy")) {
                                if (isExist) {
                        %>
                        <h1 align="center"><bean:message key="result.updateOntology.success" /></h1>
                        <%
                                } else {
                        %>
                        <h1 align="center"><bean:message key="result.updateOntology.fail" /></h1>
                        <%
                                }
                            } else if (screen.equals("classify")) {
                                if (isExist) {
                        %>
                        <h1 align="center"><bean:message key="result.classify.success" /></h1>
                        <%
                                } else {
                        %>
                        <h1 align="center"><bean:message key="result.classify.fail" /></h1>
                        <%
                                }
                            } else if (screen.equals("extraction")) {
                                if (isExist) {
                        %>
                        <h1 align="center"><bean:message key="result.extraction.success" /></h1>
                        <%
                                } else {
                        %>
                        <h1 align="center"><bean:message key="result.extraction.fail" /></h1>
                        <%
                                }
                            }
                        %>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <a href="#" onclick="submitForm(document.forms[0], 'RESULT', 'RESULT_01')"><bean:message key="result.submit"/></a>
                    </td>
                </tr>
            </table>

        </html:form>
    </body>
</html>
