<%@ page language="Java" pageEncoding="UTF-8" contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">
      <h1>${model.name}</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p id="copyright">Marmalade APIs. <a href="http://github.com/mfornos">Github</a>.</p>
    </jsp:attribute>
    <jsp:body>

      <t:messages />

     <t:organisation model="${model}"/>
     <a href="/apis/capsule/party/delete/organisation/${model.id}">DELETE</a>

    </jsp:body>
</t:layout>
