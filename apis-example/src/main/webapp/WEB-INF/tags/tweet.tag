<%@tag description="Tweet template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="model" required="true" type="marmalade.apis.twitter.api.tweets.Tweet" %>

<div class="tweet">
  <span class="user">
    <img src="${model.user.profileImageUrl}" /><br/>
    ${model.user.name}
    ${model.user.screenName}
  </span>
  <span class="date">${model.createdAt}</span>
  <span class="text">${model.html}</span>
</div>

