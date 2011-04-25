<%-- 
    Document   : menu
    Created on : 26-03-2011, 13:57:51
    Author     : DANHIT
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<link rel="stylesheet" type="text/css" href="./css/mktree.css">

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<div>
    <table width="100%" align="left" cellpadding="10" style="background-color: rgb(180, 255, 180);">
        <tr>
            <td><bean:message key="menu.row01" /></td>
        </tr>
        <tr>
            <td>
                <img alt="" align="middle" src="<bean:message key="menu.arrow" />">
            </td>
        </tr>
        <tr>
            <td><a href="DisplayLinksAction.do" onclick="showLoadingPage()"><bean:message key="menu.row02" /></a></td>
        </tr>
        <tr>
            <td><bean:message key="menu.row03" /></td>
        </tr>
        <tr>
            <td><bean:message key="menu.row04" /></td>
        </tr>
    </table>
</div>