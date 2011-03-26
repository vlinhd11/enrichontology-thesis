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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<div>
    <table width="100%" align="left" cellpadding="20">
        <tr>
            <td>Danh sách tìm kiếm</td>
        </tr>
        <tr>
            <td><a href="DisplayLinksAction.do">Tải về máy</a></td>
        </tr>
        <tr>
            <td><a href="ITDocumentAction.do">Rút trích</a></td>
        </tr>
        <tr>
            <td><a href="UpdateOntologyAction.do">Cập nhật vào Ontology</a></td>
        </tr>
    </table>
</div>