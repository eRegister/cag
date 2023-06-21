<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="items" required="true" type="java.util.Collection" %>
<%@ attribute name="ordered" required="false" type="java.lang.Boolean" %>

<c:choose>
	<c:when test="${ordered}">
		<ol>
	</c:when>
	<c:otherwise>
		<ul>
	</c:otherwise>
</c:choose>
<c:forEach items="${items}" var="item">
	<li>${item}</li>
</c:forEach>
<c:choose>
	<c:when test="${ordered}">
		</ol>
	</c:when>
	<c:otherwise>
		</ul>
	</c:otherwise>
</c:choose>
