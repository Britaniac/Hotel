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
<%@ include file="/WEB-INF/jspf/request_view.jspf" %>
<%@ include file="/WEB-INF/jspf/invoice_form.jspf" %>

</body>
</html>