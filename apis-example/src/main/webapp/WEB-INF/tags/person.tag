<%@tag description="Person template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="model" required="true" type="marmalade.apis.capsule.api.parties.Person" %>

<div class="party">
  <img src="${model.pictureURL}" alt="${model.name}" />
  <dl>
    <c:if test="${not empty model.title}">
      <dt>Title</dt><dd>${model.title}</dd>
    </c:if>
    <dt>Name</dt><dd><a href="/apis/capsule/party/person/${model.id}">${model.name}</a></dd>

    <c:choose> 
      <c:when test="${not empty model.contacts}">
        <t:contacts model="${model.contacts}" />
      </c:when>
      <c:otherwise>
        <dt>Contacts</dt><dd>-</dd>
      </c:otherwise>
    </c:choose>
    
    <%--
    <c:if test="${model.customFields.isEmpty()}">
      <c:forEach items="${model.customFields}" var="field">
        <c:if test="${not empty field}">
          <dt>${field.label}</dt><dd>${field.value}</dd>
        </c:if>
      </c:forEach>
    </c:if>
    --%>
    
  </dl>
</div>

