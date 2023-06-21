<%@ tag language="java" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ attribute name="map" required="true" type="java.util.Map" %>

Map size: <b>${fn:length(map)}</b>
