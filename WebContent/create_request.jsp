<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<c:set var="user" value="${sessionScope.user}"/>
<fmt:setLocale value="${user.localeName}" />
<fmt:setBundle basename="main.resources.messages" />

<html>
<head>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<form action="new_request" method="post">
<input type="radio" name="sort" value="cost"><fmt:message key="label.sort_cost"/>
<input type="radio" name="sort" value="capacity"><fmt:message key="label.sort_capacity"/>
<input type="radio" name="sort" value="class"><fmt:message key="label.sort_class"/>
<input type="submit" value="Sort">
</form>
<h4><fmt:message key="label.available_rooms"/></h4>
<%@ include file="/WEB-INF/jspf/request_choose_room_form.jspf" %>
<p align="center">-------------------------------------------------------------------<fmt:message key="label.or"/>---------------------------------------------------------------------</p>
<h4><fmt:message key="label.set_room_params"/></h4>
<%@ include file="/WEB-INF/jspf/request_set_params_room_form.jspf" %>
</body>
</html>