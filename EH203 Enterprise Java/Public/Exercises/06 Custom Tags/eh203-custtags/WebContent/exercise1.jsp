<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:include page="exercise1header.jsp">
	<jsp:param name="title" value="Exercise 1" />
</jsp:include>

<p>My page's content...</p>

<jsp:include page="exercise1footer.jsp">
	<jsp:param name="email" value="rowanseymour@gmail.com" />
</jsp:include>