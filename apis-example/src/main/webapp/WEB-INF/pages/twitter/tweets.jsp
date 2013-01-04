<%@ page language="Java" pageEncoding="UTF-8" contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">
      <h1>Tweets</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p id="copyright">Marmalade APIs. <a href="http://github.com/mfornos">Github</a>.</p>
    </jsp:attribute>
    <jsp:body>
    
    <t:messages />
    
    <c:forEach items="${model}" var="tweet" varStatus="status">
      <t:tweet model="${tweet}"/>
    </c:forEach>
    
    </jsp:body>
</t:layout>
