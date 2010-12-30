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
        <script type="text/javascript" src="./scripts/app.js"></script>
        <title><bean:message key="welcome.title" /></title>
    </head>
    <body>
        <html:form action="WelcomeAction">
            <html:hidden property="screenid" value="" />
            <html:hidden property="processid" value="" />
            <h1><a href="#" onclick="submitForm(document.forms[0], 'WELCOME', 'WELCOME_00')"><bean:message key="welcome.heading"/></a></h1>
  	</html:form>
    </body>
</html>
