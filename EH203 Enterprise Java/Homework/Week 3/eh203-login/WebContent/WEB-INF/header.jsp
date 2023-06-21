<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${param.title}</title>
<link href="style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="header">
		<div id="headertitle">SecureSite :: ${param.title}</div>
		<div id="headermenu">
			<c:if test="${authenticatedUser != null}">
				Welcome ${authenticatedUser.name} | <a href="login.htm?logout">Logout</a>
			</c:if>
		</div>
	</div>
	<div id="menu">
		<a href="index.jsp">Home</a> | <a href="page2.jsp">Page 2</a> | <a href="page3.jsp">Page 3</a>
	</div>
		