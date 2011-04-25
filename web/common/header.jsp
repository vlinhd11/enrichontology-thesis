<%-- 
    Document   : header
    Created on : 26-03-2011, 13:55:35
    Author     : DANHIT
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<link rel="stylesheet" type="text/css" href="./css/chrometheme/chromestyle.css">
<link rel="stylesheet" type="text/css" href="./css/mktree.css">
<script type="text/javascript" src="./scripts/tree.js"></script>
<script type="text/javascript" src="./scripts/keywordList.js"></script>
<script type="text/javascript" src="./scripts/app.js"></script>
<script type="text/javascript" src="./scripts/chromejs/chrome.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    cssdropdown.startchrome("chromemenu")
</script>

<div>
    <img src="./css/banner.jpg" border="0" alt="">
    <div class="chromestyle" id="chromemenu">
        <ul>
            <li><a href="IndexAction.do" onclick="showLoadingPage()"><bean:message key="header.home" /></a></li>
            <li><a href="Init.do" onclick="showLoadingPage()"><bean:message key="header.ontology" /></a></li>
            <li><a href="WelcomeAction.do" onclick="showLoadingPage()"><bean:message key="header.enrichOntology"/></a></li>
        </ul>
    </div>
    <%--<table align="center">
        <tr>
            <td align="center"><a href="IndexAction.do" onclick="showLoadingPage()"><bean:message key="header.home" /></a></td>
            <td align="center"><a href="Init.do" onclick="showLoadingPage()"><bean:message key="header.ontology" /></a></td>
            <td align="center"><a href="WelcomeAction.do" onclick="showLoadingPage()"><bean:message key="header.enrichOntology"/></a></td>
        </tr>
    </table>--%>
</div>
<div id="hidepage">
    <b><bean:message key="header.process"/></b><br>
    <img src="<bean:message key="image.wait"/>" alt=""><br>
    <a style="text-align: right" href="#" onclick="hideLoadingPage();">Close</a>
</div>