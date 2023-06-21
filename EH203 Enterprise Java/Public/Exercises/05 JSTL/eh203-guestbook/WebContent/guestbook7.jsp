<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Guestbook v7</title>
<style type="text/css">
* {
	font-family: Georgia;
}
</style>
</head>
<body>
<div style="width: 500px; margin: auto">
<h1>My Guestbook</h1>
<div style="background-color: #FFD; margin-bottom: 3px; border: 1px solid #DDD; -moz-border-radius: 4px; padding: 3px;">
<form method="post">
	<table style="width: 100%">
		<tr>
			<td>Name</td>
			<td><input type="text" name="name" style="width: 99%" /></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><input type="text" name="email" style="width: 99%" /></td>
		</tr>
		<tr>
			<td>Comment</td>
			<td><textarea name="comment" rows="3" cols="50" style="width: 99%"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center"><input type="submit" value="Submit" /></td>
		</tr>
	</table>
</form>
</div>
<c:if test="${empty entries}">
	<div style="text-align: center; font-style: italic">No entries</div>
</c:if>
<c:forEach items="${entries}" var="entry">
	<div style="background-color: #EEE; margin-bottom: 3px; border: 1px solid #CCC; -moz-border-radius: 4px; padding: 3px;">
		<div style="font-weight: bold">
			<c:choose>
				<c:when test="${entry.email ne ''}">
					<a href="mailto:${entry.email}"><c:out value="${entry.name}" /></a>
				</c:when>
				<c:otherwise>
					<c:out value="${entry.name}" />
				</c:otherwise>
			</c:choose>
			<span style="font-size: 11px"> on <fmt:formatDate value="${entry.date}" dateStyle="full" /></span>
		</div>
		<p><c:out value="${entry.comment}" /></p>
	</div>
</c:forEach>
</div>
</body>
</html>