<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<c:set var="user" value="${sessionScope.user}"/>
<h3>Hello <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></h3>
<%@ include file="/WEB-INF/jspf/request_view.jspf" %>
<form action="new_request"  method="post">
<input type="submit" value="Create new"></input>
</form>
<c:if test="${request.invoiceId > 0}">
<p>You have an invoice awaiting payment:</p>
<c:set var="id" value="${request.invoiceId}"/>
<c:set var="link" value="pay?${id}"/>
<form action="${link}">
<input type="submit" value="Pay"></input>
</form>
</c:if>
</body>
</html>