<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="ehsdi" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Using TAG files</title>
</head>
<body>

<h3>1. Simple text include TAG</h3>
<p><ehsdi:copyright /></p>

<h3>2. Putting EL in the TAG</h3>
<p><ehsdi:useragent /></p>

<h3>3. TAG with attributes</h3>
<p><ehsdi:email name="Rowan" address="rowanseymour@gmail.com" /></p>

<h3>4. TAG with attributes and a body</h3>
<p><ehsdi:message subject="TAG files are great">This text is displayed when you click the view link, otherwise its hidden</ehsdi:message></p>

<h3>5. Object as attribute</h3>
<p><ehsdi:mapsize map="${header}" /></p>

</body>
</html>