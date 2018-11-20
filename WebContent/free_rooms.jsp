<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<c:set var="user" value="${sessionScope.user}"/>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="main.resources.messages" />

<html>
<head>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<table>
<tr>
<td><fmt:message key="label.capacity"/></td>
<td><fmt:message key="label.cost"/></td>
<td><fmt:message key="label.class"/></td>
</tr>
<c:forEach items="${roomList}" var="room">
<tr>
<td>${room.capacity}</td>
<td>${room.cost}</td>
<td>${room.rClass}</td>
</tr>
</c:forEach>
</table>
</body>
</html>