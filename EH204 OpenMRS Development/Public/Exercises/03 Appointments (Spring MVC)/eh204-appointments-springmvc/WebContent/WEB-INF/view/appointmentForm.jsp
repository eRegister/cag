<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add appointment</title>
</head>
<body>
<h1>Add appointment</h1>
<hr />
<form:form method="post" commandName="appointment">
	<form:input path="patientName"/>
	<form:errors path="patientName"/>
	<br/>
	<form:input path="date"/>
	<form:errors path="date"/>
	<input type="submit" value="Save" />
</form:form>
<p><a href="index.jsp">Back to menu</a></p>
</body>
</html>