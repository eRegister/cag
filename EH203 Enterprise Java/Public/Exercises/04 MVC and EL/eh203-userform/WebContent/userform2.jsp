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
<jsp:useBean id="curUser" class="eh203.userform.User2" scope="request" />
<p>Name: <jsp:getProperty name="curUser" property="name" /></p>
<p>Age: <jsp:getProperty name="curUser" property="age" /></p>
<p>Admin: <jsp:getProperty name="curUser" property="admin" /></p>
<p>Work email: <%=((User2)request.getAttribute("curUser")).getEmailAddresses().get("Work")%></p>
<p>Home email: <%=((User2)request.getAttribute("curUser")).getEmailAddresses().get("Home")%></p>
</body>
</html>