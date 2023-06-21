<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="gal" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${fn:replace(gallery, "_", " ")}</title>
</head>
<body>
<h1>${fn:replace(gallery, "_", " ")}</h1>
<div style="background-color: #BBB; -moz-border-radius: 10px; overflow: hidden">
<c:forEach items="${images}" var="entry">
	<gal:thumb gallery="${gallery}" image="${entry.image}" />
</c:forEach>
</div>
<p><a href="index.htm">Home</a></p>
</body>
</html>