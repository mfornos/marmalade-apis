<%@tag description="Organisation template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="model" required="true" type="marmalade.apis.capsule.api.parties.Organisation" %>

<div class="party">
  <img src="${model.pictureURL}" alt="${model.name}" />
  <dl>
    <dt>Name</dt><dd><a href="/apis/capsule/party/organisation/${model.id}">${model.name}</a></dd>

    <c:choose> 
      <c:when test="${not empty model.contacts}">
        <t:contacts model="${model.contacts}" />
      </c:when>
      <c:otherwise>
        <dt>Contacts</dt><dd>-</dd>
      </c:otherwise>
    </c:choose>

  </dl>
</div>

