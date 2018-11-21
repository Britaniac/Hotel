<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<style>
body {
	width:100%;
   padding-bottom:10%;   /* Height of the footer */
}
form {
	padding-top: 10%;
	height:70%;
	background: #00ace6;
	text-align: center;
}
</style>

<c:set var="user" value="${sessionScope.user}"/>
<fmt:setLocale value="ru" scope="session"/>
<fmt:setBundle basename="main.resources.messages"/>

<html>

<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section>
<%@ include file="/WEB-INF/jspf/rooms_sidebar.jspf" %>

<form action="login_check" method="post">
<fmt:message key="label.login"/>
<br>
<input type=text name="login">
<br>
<fmt:message key="label.password"/>
<br>
<input type=password name="password">
<br>
<fmt:message key="label.submit" var="submit"/>
<input type=submit value="${submit}"></input> <fmt:message key="label.or"/>
<a href="register.jsp"><fmt:message key="label.register"/></a>
</form>
</section>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
