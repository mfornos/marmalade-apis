<%@ page language="Java" pageEncoding="UTF-8" contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">
      <h1>Opportunities</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p id="copyright">Marmalade APIs. <a href="http://github.com/mfornos">Github</a>.</p>
    </jsp:attribute>
    <jsp:body>
    
    <c:forEach items="${model.opportunity}" var="opportunity" varStatus="status">
      <t:opportunity model="${opportunity}"/>
    </c:forEach>
    
    <c:if test="${model.isEmpty()}">
      Nothing was found.
    </c:if>

    </jsp:body>
</t:layout>
