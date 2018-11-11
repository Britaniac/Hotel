<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<table>
<tr>
<td>Capacity</td>
<td>Cost</td>
<td>Class</td>
</tr>
<c:forEach items="${roomList}" var="room">
<tr>
<td>${room.capacity}</td>
<td>${room.cost}</td>
<td>${room.rClass}</td>
</tr>
</c:forEach>
</table>
</body>
</html>