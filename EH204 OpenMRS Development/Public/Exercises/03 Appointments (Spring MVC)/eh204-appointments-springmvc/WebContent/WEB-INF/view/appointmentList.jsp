<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of appointments</title>
</head>
<body>
<h1>Appointments</h1>
<hr />
<table>
	<tr>
		<th>Patient name</th>
		<th>Date</th>
	</tr>
<c:forEach items="${appointments}" var="appointment">
	<tr>
		<td>${appointment.patientName}</td>
		<td>${appointment.date}</td>
	</tr>
</c:forEach>
</table>
<p><a href="index.jsp">Back to menu</a></p>
</body>
</html>