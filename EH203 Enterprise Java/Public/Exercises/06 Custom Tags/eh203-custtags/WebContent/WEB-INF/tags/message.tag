<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="subject" required="true" %>

<div>
	<a href="#"
	onclick="document.getElementById('message').style.display='block'">
	${subject}
	</a>
	<div id="message" style="display: none"><jsp:doBody/></div>
</div>
