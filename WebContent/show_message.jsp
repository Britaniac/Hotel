<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<body>
<c:out value="${sessionScope.message}"/>
<a href="login_check">Go back</a>
</body>
</html>