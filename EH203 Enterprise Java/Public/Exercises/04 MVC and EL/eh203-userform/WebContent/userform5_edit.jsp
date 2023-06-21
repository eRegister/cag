<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="eh203.userform.User2" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit User</title>
</head>
<body>
<h1>Edit User</h1>
<form method="post" action="Exercise01e">
<p>Name: <input type="text" name="user_name" value="${curUser.name}" /></p>
<p>Age: <input type="text" name="user_age" value="${curUser.age}" /></p>
<p>Admin: <input type="checkbox" name="user_admin" value="true" ${curUser.admin ? "checked='checked'" : ""} /></p>
<p>Work email: <input type="text" name="user_work" value="${curUser.emailAddresses.Work}" /></p>
<p>Home email: <input type="text" name="user_home" value="${curUser.emailAddresses.Home}" /></p>	
<p><input type="submit" value="Update" /></p>
</form>
</body>
</html>