<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>

<jsp:useBean id="basket" class="fruitshop.Basket" scope="session" />
<p>Your shopping basket contains:</p>
<table>
<% 
	for (Item item : basket.getItems()) {
		out.println("<tr><td>" + item.getName() + "</td><td>" + item.getCost() + "</td></tr>");
	}
%>
</table>

<p><a href="index.jsp">Continue shopping</a> | <a href="checkout.jsp">Checkout</a></p>

<%@ include file="footer.jsp" %>