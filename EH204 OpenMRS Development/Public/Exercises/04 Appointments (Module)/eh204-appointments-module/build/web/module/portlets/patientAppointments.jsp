<%@ include file="/WEB-INF/template/include.jsp" %>

<b class="boxHeader"><spring:message code="appointments.Appointment.patient" /></b>

<table>
	<tr>
		<th>Location</th>
		<th>When</th>
		<th>Provider</th>
		<th>&nbsp;</th>
	</tr>
	<c:forEach var="appointment" items="${model.patientAppointments}">
		<tr>
			<td>${appointment.location}</td>
			<td><openmrs:formatDate date="${appointment.when}" /></td>
			<td>${appointment.provider}</td>
			<td align="right"><a href="appointment.form?appointmentId=${appointment.appointmentId}">Edit</a></td>
		</tr>
	</c:forEach>
</table>