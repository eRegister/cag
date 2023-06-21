<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String firstName = "";
String lastName = "";
for (Cookie cookie : request.getCookies()) {
	  if (cookie.getName().equals("firstName")) {
	    firstName = cookie.getValue();
	  } else if(cookie.getName().equals("lastName")) {
		  lastName = cookie.getValue();  
	  }
}
	if(!firstName.equals("") && !lastName.equals("")) {
		out.println("Hi "+ firstName +" "+ lastName+"!  I remember you!<br>");
		
		out.println("<a href=\""
+ response.encodeURL("SessionServlet2?remove=yes")
				 + "\">Remove Cookies</a>");

	
	} else {
%>
	<form action="SessionServlet" method="POST">
        First Name: <input type="text" name="firstName" size="20"><br>
        Surname: <input type="text" name="lastName" size="20">
        <br><br>
        <input type="submit" value="Submit">
    </form> 
<%
	}
%>
</body>
</html>