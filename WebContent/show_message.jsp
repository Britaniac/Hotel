<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<head>
  <link rel="stylesheet" href="styles.css">
</head>
<c:set var="user" value="${sessionScope.user}"/>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="main.resources.messages" />

<html>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%-- <c:set var ="msg" value="${sessionScope.message}"/>
<c:choose>
<c:when test="${msg=='Success'}">
<fmt:message key="label.success"/>
</c:when>
<c:when test="${msg=='Failure'}">
<fmt:message key="label.failure"/>
</c:when>
</c:choose> --%>
<c:out value="${sessionScope.message}"/>
<a href="login.jsp"><fmt:message key="label.back"/></a>
</body>
</html>