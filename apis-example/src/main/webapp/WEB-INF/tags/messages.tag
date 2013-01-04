<%@tag description="Messages template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty successMessage}">
  <div class="messages solid-green">
  <h1>Success</h1>
  <a href="#" class="close">X</a>
  <p>
    ${successMessage}
  </p>
  </div>
</c:if>

<c:if test="${not empty errorMessage}">
  <div class="messages solid-red">
  <h1>Error</h1>
  <a href="#" class="close">X</a>
  <p>
    ${errorMessage}
  </p>
  </div>
</c:if>
