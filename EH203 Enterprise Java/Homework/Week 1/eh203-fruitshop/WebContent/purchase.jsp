<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>

<jsp:useBean id="basket" class="fruitshop.Basket" scope="session" />
<p>You have purchased
<% 
	int itemId = Integer.parseInt(request.getParameter("itemId"));
	Item item = Stock.getItem(itemId);
	
	out.println(item.getName());
	
	basket.addItem(item);
%></p>

<p><a href="index.jsp">Continue shopping</a> | <a href="basket.jsp">View basket</a></p>

<%@ include file="footer.jsp" %>