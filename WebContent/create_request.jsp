<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<h4>Please choose one of the free rooms</h4>
<%@ include file="/WEB-INF/jspf/request_choose_room_form.jspf" %>
<p align="center">-------------------------------------------------------------------OR---------------------------------------------------------------------</p>
<h4>Set parameters for your room</h4>
<%@ include file="/WEB-INF/jspf/request_set_params_room_form.jspf" %>
</body>
</html>