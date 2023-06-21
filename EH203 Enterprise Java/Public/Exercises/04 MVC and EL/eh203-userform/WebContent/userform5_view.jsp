<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="eh203.userform.User2" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View User</title>
</head>
<body>
<h1>View User</h1>
<form method="post">
<p>Name: ${curUser.name}
<p>Age: ${curUser.age}
<p>Admin: ${curUser.admin}</p>
<p>Work email: ${curUser.emailAddresses.Work}</p>
<p>Home email: ${curUser.emailAddresses.Home}</p>	
<p><a href="Exercise01e?edit">Edit</a></p>
</form>
</body>
</html>