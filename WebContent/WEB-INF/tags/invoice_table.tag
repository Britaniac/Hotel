<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>


<%@ attribute name="invoices" required="true" type="java.util.List" description="List of invoices" %>

<table>
 <tr>
  <td>ID</td>
  <td><fmt:message key="label.pay_sum"/></td>
  <td><fmt:message key="label.created"/></td>
  <td><fmt:message key="label.requestID"/></td>
 </tr>
<c:forEach items="${invoices}" var="invoice">
<tr>
<td>${invoice.id}</td>
<td>${invoice.sum}</td>
<td>${invoice.created}</td>
<td>${invoice.requestID}</td>
</tr>
</c:forEach>
</table>