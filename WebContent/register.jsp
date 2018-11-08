<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<style>
body{
	background: #00ace6;
}
</style>

<html>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<form action="register_user" method="post">
Login:
<br>
<input type=text name="login" required="required">
<br>
Password:
<br>
<input type=password name="password" required="required">
<br>
First name:
<br>
<input type=text name="fName" required="required">
<br>
Last name:
<br>
<input type=text name="lName" required="required">
<br>
E-mail:
<br>
<input type=email name="email" required="required">
<br>
<input type=submit value="Register">
</form>
</body>
</html>