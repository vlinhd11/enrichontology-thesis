<%-- 
    Document   : ontology
    Created on : 23-04-2011, 09:25:28
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

<%@page import="com.tkorg.util.Constants" %>
<%@page import="com.tkorg.businesslogic.OntologyBL" %>
<%@page import="javax.swing.JOptionPane" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../css/mktree.css">
        <script type="text/javascript" src="../scripts/tree.js"></script>
        <script type="text/javascript" src="../scripts/keywordList.js"></script>
	<script type="text/javascript" src="../scripts/app.js"></script>
        <script type="text/javascript" src="./scripts/ontology.js"></script>
        <title>Ontology</title>
    </head>
    <body>
        <table border="1" width="<bean:message key="size.width" />" align="center" style="margin-top: -5px; padding: 0px">
            <tr>
                <%--<td width="30%" align="left" valign="top" style="background-color: rgb(180, 255, 180);">--%>
                <td width="30%" align="left" valign="top">
                    <h3><bean:message key="searchOntology.ontology" /></h3>
                    <div id="scroll_box">
                        <jsp:useBean id="ontology" class="com.tkorg.businesslogic.OntologyBL" scope="page">
<%
                            String namePath = Constants.PATH_ONTOLOGY;
%>
                        <jsp:setProperty name="ontology" property="path" value="<%=namePath%>" />
                        <jsp:getProperty name="ontology" property="result" />
                        </jsp:useBean>
                    </div>
                </td>
                <td width="35%" align="left" valign="top">
                    <h3>Individual List</h3>
                     <div id="individual_ID">
                         <select id="listID" size="26" style="width: 100%;"/>
                    </div>
                </td>
                <td width="35%" align="left" valign="top">
                    <h3>Values List</h3>
                     <div id="values_ID">
                        <select id="listID02" size="26" style="width: 100%;"/>
                    </div>
                </td>
            </tr>
        </table>
    </body>
</html>

