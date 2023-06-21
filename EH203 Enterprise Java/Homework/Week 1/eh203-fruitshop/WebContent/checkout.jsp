<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>

<jsp:useBean id="basket" class="fruitshop.Basket" scope="session" />
<p>You are about to purchase:</p>
<table>
<%
	for (Item item : basket.getItems()) {
		out.println("<tr><td>" + item.getName() + "</td><td>" + item.getCost() + "</td></tr>");
	}
	
	double cost = basket.totalCost();
%>
</table>

<p>Total cost: <%= cost %></p>

<p><a href="basket.jsp">Edit basket</a> | <a href="confirm.jsp">Confirm</a></p>

<%@ include file="footer.jsp" %>