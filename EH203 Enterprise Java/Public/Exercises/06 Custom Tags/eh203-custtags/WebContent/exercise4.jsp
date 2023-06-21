<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="ehsdi" uri="ehsdiTags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--<title>Using tag handler classes</title>-->
</head>
<body>

<h3>1. Simple text include tag handler</h3>
<p><ehsdi:first /></p>

<h3>2. Tag handler with body</h3>
<p><ehsdi:second>My body</ehsdi:second></p>

<h3>3. Tag handler with attributes</h3>
<p><ehsdi:copyright holder="EHSDI" year="2009" /></p>

</body>
</html>