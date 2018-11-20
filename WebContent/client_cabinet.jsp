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
<h3><fmt:message key="label.hello"/> <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></h3>
<%@ include file="/WEB-INF/jspf/request_view.jspf" %>
<form action="new_request"  method="post">
<fmt:message key="label.new_request" var="newReq"/>
<input type="submit" value="${newRec}"></input>
<br>
<br>
<h3><fmt:message key="label.inv_awaiting"/></h3>
<%@ include file="/WEB-INF/jspf/invoice_view.jspf" %>
</form>
</body>
</html>