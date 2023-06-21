<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="gallery" required="true" %>
<%@ attribute name="image" required="true" %>

<div style="padding: 10px; float: left">
	<a href="gallery/${gallery}/${image}" title="${image}"><img border="0" src="gallery/${gallery}/thumbs/${image}" /></a>
</div>
