<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Guestbook v2</title>
</head>
<body>
<h1>My Guestbook</h1>
<form method="post">
	<table>
		<tr>
			<td>Name</td>
			<td><input type="text" name="name" /></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><input type="text" name="email" /></td>
		</tr>
		<tr>
			<td>Comment</td>
			<td><textarea name="comment" rows="3" cols="50"></textarea></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Submit" /></td>
		</tr>
	</table>
</form>
<div style="background-color: #BBB">
	<h3><c:out value="${param.name}" /> (<c:out value="${param.email}" />)</h3>
	<p><c:out value="${param.comment}" /></p>
</div>
</body>
</html>