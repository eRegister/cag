<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<h2><spring:message code="appointments.list.title" /></h2>
<a href="appointment.form"><spring:message code="appointments.Appointment.add" /></a>

<br />
<br />

<b class="boxHeader"><spring:message code="appointments.Appointment.all" /></b>

<form method="post" class="box">
<table>
	<tr>
		<th>Patient</th>
		<th>Location</th>
		<th>When</th>
		<th>Provider</th>
		<th>&nbsp;</th>
	</tr>
	<c:forEach var="appointment" items="${appointments}">
		<tr>
			<td><a href="${pageContext.request.contextPath}/patientDashboard.form?patientId=${appointment.patient.patientId}">${appointment.patient.personName}</a></td>
			<td>${appointment.location}</td>
			<td>${appointment.when}</td>
			<td>${appointment.provider}</td>
			<td align="right"><a href="appointment.form?appointmentId=${appointment.appointmentId}">Edit</a></td>
		</tr>
	</c:forEach>
</table>

<!--<input type="submit" value="Delete Appointments" name="action">-->
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>
