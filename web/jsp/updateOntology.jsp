<%--
    Document   : updateOntology
    Created on : Dec 29, 2010, 8:23:41 PM
    Author     : danhit
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@page import="com.tkorg.util.Constants" %>
<%@page import="com.tkorg.entities.MyKeyword" %>
<%@page import="com.tkorg.util.Global" %>

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
        <title><bean:message key="updateOntology.title" /></title>
    </head>
    <body bgcolor="#FFFFFF">
        <h1><bean:message key="updateOntology.heading" /></h1>
        
        <html:form action="/UpdateOntologyAction">

            <table border="1" width="100%">
                <tr BGCOLOR="#FFF8C6">
                    <th width ="3%"><bean:message key="updateOntology.no" /></th>
                    <th width ="79%"><bean:message key="updateOntology.heading" /></th>
                    <th width="3%"><bean:message key="updateOntology.remove" /></th>
                </tr>
                <%
                    for(int i = 0; i < Global.keywordList.size(); i++) {
                        String temp01 = Global.checkSize(Global.keywordList.get(i).getName());
                %>
                <tr>
                    <td></td>
                    <td style="background-color: yellow" width="82%"><%=temp01%></td>
                    <td></td>
                </tr>
                <%
                        for (int j = 0; j < Global.keywordList.get(i).getIndividuals().size(); j++) {
                            String content = Global.keywordList.get(i).getIndividuals().get(j).getContent()
                                    + "<br />"
                                    + "<a href=#>" + Global.keywordList.get(i).getIndividuals().get(j).getLink() + "</a>";
                            content = Global.checkSize(content);
                %>
                <tr>
                    <td width="3%"><%=Integer.toString(j + 1)%>.</td>
                    <td width="79%"><%=content%></td>
                    <td width="3%">
                <%
                            String temp = "" + i + "-" + j;
                %>
                        <html:multibox property="item" value="<%=temp%>" ></html:multibox>
                    </td>
                </tr>
            <%
                        }
                    }
            %>
            </table>
        </html:form>
    </body>
</html>
