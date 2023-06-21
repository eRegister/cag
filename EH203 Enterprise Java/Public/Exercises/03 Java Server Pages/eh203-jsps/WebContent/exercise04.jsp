<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="eh203.jsps.*" %>
<%!
int count = 0; 
int getCount() {
	return ++count;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exercise 4: Declarations</title>
</head>
<body>
Number of page hits: <%= getCount() %>
</body>
</html>