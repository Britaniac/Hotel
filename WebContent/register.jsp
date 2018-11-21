<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<style>
body{
	background: #00ace6;
}
</style>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="main.resources.messages" />

<html>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<form action="register_user" method="post">
<fmt:message key="label.login"/>
<br>
<input type=text name="login" required="required">
<br>
<fmt:message key="label.password"/>
<br>
<input type=password name="password" required="required">
<br>
<fmt:message key="label.fname"/>
<br>
<input type=text name="fName" required="required">
<br>
<fmt:message key="label.lname"/>
<br>
<input type=text name="lName" required="required">
<br>
<fmt:message key="label.email"/>
<br>
<input type=email name="email" required="required">
<br>
<fmt:message key="label.register" var="register"/>
<input type=submit value="${register}">
</form>
</body>
</html>