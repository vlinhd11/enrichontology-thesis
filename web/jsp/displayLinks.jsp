<%-- 
    Document   : displayLinks01
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

<%@page import="java.io.IOException" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/mktree.css">
        <script type="text/javascript" src="./scripts/tree.js"></script>
        <script type="text/javascript" src="./scripts/keywordList.js"></script>
	<script type="text/javascript" src="./scripts/app.js"></script>
        <title><bean:message key="display.title" /></title>
    </head>
    <body bgcolor="#FFFFFF">
        <h1><bean:message key="display.heading" /></h1>
        
        <html:form action="/DisplayLinksAction">
            <%
                ArrayList < MyKeyword > googleList = (ArrayList<MyKeyword>) session.getAttribute(Constants.GOOGLE_LIST);
                ArrayList < MyKeyword > yahooList = (ArrayList < MyKeyword >) session.getAttribute(Constants.YAHOO_LIST);
                Boolean isGoogle = (Boolean) session.getAttribute(Constants.IS_GOOGLE);
                Boolean isYahoo = (Boolean) session.getAttribute(Constants.IS_YAHOO);

                //Search by Google
                if (isGoogle == true) {
            %>
            <table border="1" width="100%">
                <tr BGCOLOR="#FFF8C6">
                    <th width =\"3%\"><bean:message key="display.no" /></th>
                    <th width =\"82%\"><bean:message key="display.resultByGoogle" /></th>
                </tr>
                <%
                    for(int i = 0; i < googleList.size(); i++) {
                %>
                <tr>
                    <td></td>
                    <td style="background-color: yellow" width="82%"><%=googleList.get(i).getName()%></td>
                </tr>
                <%
                        for (int j = 0; j < googleList.get(i).getLinkList().size(); j++) {
                %>
                <tr>
                    <td width="3%"><%=Integer.toString(j+1)%>.</td>
                    <td width="82%">
                        <a href="<%=googleList.get(i).getLinkList().get(j)%>" target="_blank">
                            <%=googleList.get(i).getLinkList().get(j)%>
                        </a>
                        <br>
                        <%=googleList.get(i).getTitleList().get(j)%>
                    </td>
                </tr>
            <%
                        }
                    }
            %>
            </table><br><br>
            <%
                }

                //Search by Yahoo
                if (isYahoo == true) {
            %>
            <table border="1" width="100%">
                <tr BGCOLOR="#FFF8C6">
                    <th width =\"3%\"><bean:message key="display.no" /></th>
                    <th width =\"97%\"><bean:message key="display.resultByYahoo" /></th>
                </tr>
                <%
                    for(int i = 0; i < yahooList.size(); i++) {
                %>
                <tr>
                    <td></td>
                    <td style="background-color: yellow" width="82%"><%=yahooList.get(i).getName()%></td>
                </tr>
                <%
                        for (int j = 0; j < yahooList.get(i).getLinkList().size(); j++) {
                %>
                <tr>
                    <td width="3%"><%=Integer.toString(j+1)%>.</td>
                    <td width="97%">
                        <a href="<%=yahooList.get(i).getLinkList().get(j)%>" target="_blank">
                            <%=yahooList.get(i).getLinkList().get(j)%>
                        </a>
                        <br>
                        <%=yahooList.get(i).getTitleList().get(j)%>
                    </td>
                </tr>
            <%
                        }
                    }
            %>
            </table>
            <br>
            <%
                }
            %>
            <%--<html:button property="btnDisplay" onclick="submitForm(document.forms[0], 'DISPLAY_LINKS', 'DISPLAY_LINKS_01')" ><bean:message key="display.submit" /></html:button>--%>

        </html:form>

    </body>
</html>
