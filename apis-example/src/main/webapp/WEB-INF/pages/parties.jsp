<%@ page language="Java" pageEncoding="UTF-8" contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">
      <h1>Parties</h1> <span class="head-search"><t:search /></span>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p id="copyright">Marmalade APIs. <a href="http://github.com/mfornos">Github</a>.</p>
    </jsp:attribute>
    <jsp:body>
    
    <c:forEach items="${model.person}" var="person" varStatus="status">
      <t:person model="${person}"/>
    </c:forEach>
    
    <c:forEach items="${model.organisation}" var="organisation" varStatus="status">
      <t:organisation model="${organisation}"/>
    </c:forEach>
    
    <c:if test="${model.isEmpty()}">
      Nothing was found.
    </c:if>

    </jsp:body>
</t:layout>
