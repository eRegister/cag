<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:include page="header.jsp">
	<jsp:param value="Login" name="title"/>
</jsp:include>
<div id="loginDialog">
	<form method="post" action="login.htm?redirect=${param.redirect}">
		<p>Username<br/><input type="text" name="login" /></p>
		<p>Password<br/><input type="password" name="password" /></p>
		<p><input type="submit" value="Login" /></p>
	</form>
</div>
<jsp:include page="footer.jsp" />