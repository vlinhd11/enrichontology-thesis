<%-- 
    Document   : welcome
    Created on : Dec 27, 2010, 8:22:47 PM
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

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/mktree.css">
        <script type="text/javascript" src="./scripts/app.js"></script>
        <title><bean:message key="welcome.title" /></title>
    </head>
    <body>
        <html:form action="WelcomeAction">
            <html:hidden property="screenid" value="" />
            <html:hidden property="processid" value="" />
            <table width="70%" border="0px" align="center">
                <tr align="center">
                    <td colspan="2">
                        <h1 style="color:blue;background-color:yellow" align="center"><bean:message key="welcome.heading"/></h1>
                    </td>
                </tr>
                <tr>
                    <td width="50%" align="left">
                        <bean:message key="welcome.content" />
                        <br><br><br>
                        <bean:message key="welcome.member_of_group" />
                        <br>
                        &nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="welcome.author01" />
                        <br>
                        &nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="welcome.author02" />
                        <br><br>
                        <a href="#" onclick="submitForm(document.forms[0], 'WELCOME', 'WELCOME_00')"><bean:message key="welcome.run"/></a>
                    </td>
                    <td width="50%" align="right">
                        <img src="./css/welcome.jpg" border="0" align="middle" alt="">
                    </td>
                </tr>
            </table>
  	</html:form>
    </body>
</html>
