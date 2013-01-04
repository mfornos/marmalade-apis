<%@tag description="Opportunity template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="model" required="true" type="marmalade.apis.capsule.api.opportunities.Opportunity" %>

<div class="party">
  ${model}
</div>

