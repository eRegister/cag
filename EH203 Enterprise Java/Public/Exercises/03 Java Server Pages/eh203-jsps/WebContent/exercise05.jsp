<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exercise 5</title>
</head>
<body>
<h1>Parameters</h1>
<table border="1">
<%
	for (Object name : request.getParameterMap().keySet()) {
		String value = request.getParameter(name.toString());
		
		out.println("<tr><td>" + name + "</td><td>" + value + "</td></tr>");
	}
%>
</table>
<h2></h2>
<form method="post">
<input type="text" name="test_post" /><input type="submit" value="Submit" />
</form>
</body>
</html>