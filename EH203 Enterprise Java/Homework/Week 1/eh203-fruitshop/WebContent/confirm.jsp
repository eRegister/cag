<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>

<jsp:useBean id="basket" class="fruitshop.Basket" scope="session" />
<%
	double cost = basket.checkout();
%>

<p>You have been billed <%= cost %> RWF</p>

<p>Thank you for using Fruit shop. Please come again</p>

<p><a href="index.jsp">Continue shopping</a> | <a href="basket.jsp">View basket</a></p>

<%@ include file="footer.jsp" %>