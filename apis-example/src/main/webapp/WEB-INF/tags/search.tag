<%@tag description="Search template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<form class="search" action="/apis/capsule/parties" method="GET">
   <input class="search-query" type="text" name="q" value="${param.q}" placeholder="search..." autocomplete="off" /> 
   <input type="submit" class="icon icon-search hide-text" />
</form>
