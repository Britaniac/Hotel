<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<style>
table {
    text-align: center;
    border: 1px solid black;
    border-collapse: collapse;
    width: 70%;
}
td {
	border: 1px solid black;
    border-collapse: collapse;
}
</style>
<html>

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