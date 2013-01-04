<%@tag description="Contacts template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="model" required="true" type="marmalade.apis.capsule.api.parties.Contacts" %>

<dt>Contacts</dt>
<dd>
  <c:if test="${not empty model.email}">
  <em>Email</em>
  <ul class="links">
    <c:forEach items="${model.email}" var="item">
       <li>${item.emailAddress} <span class="type">${item.type}</span></li>
    </c:forEach>
  </ul>
  </c:if>
  <c:if test="${not empty model.phone}">
  <em>Phone</em>
  <ul class="links">
    <c:forEach items="${model.phone}" var="item">
       <li>${item.phoneNumber} <span class="type">${item.type}</span></li>
    </c:forEach>
  </ul>
  </c:if>
  <c:if test="${not empty model.address}">
  <em>Address</em>
  <ul class="links">
    <c:forEach items="${model.address}" var="item">
       <li>${item.street} ${item.country} ${item.city} <span class="type">${item.type}</span></li>
    </c:forEach>
  </ul>
  </c:if>
  <c:if test="${not empty model.website}">
  <em>Website</em>
  <ul class="links">
    <c:forEach items="${model.website}" var="item">
       <li>${item.url} ${item.webService} <span class="type">${item.type}</span></li>
    </c:forEach>
  </ul>
  </c:if>
</dd>
