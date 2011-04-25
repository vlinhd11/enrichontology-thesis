<%-- 
    Document   : ontology_showValues
    Created on : 24-04-2011, 01:18:43
    Author     : DANHIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList" %>

<div id="values_ID">
<%
    ArrayList < String > listProperty = (ArrayList < String >) request.getAttribute("listProperty");
    ArrayList < String > listValue = (ArrayList < String >) request.getAttribute("listValue");
    if (listProperty.size() <= 0) {
%>
    <select id="listID02" size="26" style="width: 100%;">
        <option>Không có dữ liệu</option>
    </select>
<%
    } else {
%>
        <select id="listID02" size="26" style="width: 100%;">
<%
    for (int i = 0; i < listProperty.size(); i++) {
%>
        <option value="<%=listProperty.get(i)%>">
            <%=listProperty.get(i)%>: ''<%=listValue.get(i)%>''
        </option>
        <option></option>
<%
    }
%>
        </select>
<%
    }
%>
</div>