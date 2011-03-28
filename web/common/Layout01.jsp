<%-- 
    Document   : Layout
    Created on : 25-03-2011, 21:25:17
    Author     : DANHIT
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/mktree.css">
        <title><tiles:getAsString name="title" /></title>
    </head>
    <body>
        <table width="1000" align="center" border="0" cellpadding="0" style="margin-top: -10px;">
            <tr>
                <td colspan="2" align="center">
                    <tiles:insert attribute="header" />
                </td>
            </tr>
            <tr>
                <td bgcolor="rgb(180, 255, 180)" width="30%" valign="top" align="left" nowrap>
                    <tiles:insert attribute="menu" />
                </td>
                <td align="center" width="70%" valign="top">
                    <tiles:insert attribute="body" />
                </td>
            </tr>
        </table>
        <tiles:insert attribute="footer" />
    </body>
</html>
