<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>

<p>Currently we have in stock:</p>
<table>
<% 
	for (Item item : Stock.getItems()) {
		out.println("<tr><td>" + item.getName() + "</td><td>" + item.getCost() + "</td><td><a href='purchase.jsp?itemId=" + item.getId() + "'>Buy</a></td></tr>");
	}
%>
</table>
<p><a href="basket.jsp">View basket</a></p>

<%@ include file="footer.jsp" %>