<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="ehsdi" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Objects as tag attributes</title>
</head>
<body>
<%
	ArrayList<String> list = new ArrayList<String>();
  list.add("Sunday");
  list.add("Monday");
  list.add("Tuesday");
  list.add("Wednesday");
  list.add("Thursday");
  list.add("Friday");
  list.add("Saturday");
  
  request.setAttribute("list", list);
%>
<ehsdi:arraylist items="${list}" ordered="true" />
</body>
</html>