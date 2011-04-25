<%-- 
    Document   : ontology_showIndividuals
    Created on : 23-04-2011, 15:01:55
    Author     : DANHIT
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<div id="individual_ID">
<%
    String strIndividuals = (String) request.getAttribute("strIndividuals");
    String[] list = strIndividuals.split(";");
    if (list.length <= 0) {
%>
    <select id="listID" size="26" style="width: 100%;">
        <option>Không có dữ liệu</option>
    </select>
<%
    } else {
%>
    <select id="listID" size="26" onchange="showValues(this.value, 'listID02')" style="width: 100%;">
<%
    for (int i = 0; i < list.length; i++) {
%>
        <option value="<%=list[i]%>"><%=list[i]%></option>
<%
    }
%>
    </select>
<%
    }
%>
</div>