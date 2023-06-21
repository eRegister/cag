<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HitLog</title>
</head>
<body>
<h1>Access Log</h1>
<table border="1">
	<tr>
		<th>When</th>
		<th>What</th>
		<th>Who</th>
		<th>How long</th>
	</tr>
	<c:forEach items="${visits}" var="visit">
		<tr>
			<td>${visit.date}</td><td>${visit.url}</td><td>${visit.remoteAddr}</td><td>${visit.timeTaken}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>