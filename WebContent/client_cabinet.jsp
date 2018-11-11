<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<%-- <jsp:useBean id="userBean" scope="session" class=ua.nure.koval.hotel.entity.User ></jsp:useBean> --%>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<c:set var="user" value="${sessionScope.user}"/>
<h3>Hello <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></h3>
<c:set var="request" value="${sessionScope.userRequest}"/>
<c:choose>
<c:when test="${not empty request}">
<p>Your request #<c:out value="${request.id}"/> is being processed</p>
<c:set var="id" value="${request.id}"/>
<c:set var="link" value="delete_request?id=${id}"/>
<form action="${link}" method="post">
<input type="submit" value="Cancel"></input>
</form>
</c:when>
<c:otherwise>
<form action="new_request"  method="post">
<input type="submit" value="Create new"></input>
</form>
</c:otherwise>
</c:choose>
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