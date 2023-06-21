<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="name" required="true" %>
<%@ attribute name="address" required="true" %>

<a href="mailto:${address}" title="Email me!">${name}</a>
