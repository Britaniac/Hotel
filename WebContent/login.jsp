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
<html>

<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section>
<%@ include file="/WEB-INF/jspf/rooms_sidebar.jspf" %>

<form action="login_check" method="post">
Login:
<br>
<input type=text name="login">
<br>
Password:
<br>
<input type=password name="password">
<br>
<input type=submit value="Login"> OR 
<input type=submit value="Register" formaction="register.jsp">
</form>
</section>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>