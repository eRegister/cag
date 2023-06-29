<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />

<script type="text/javascript">
	
	function enableSaveButton(relType, id) {
		document.getElementById("saveAppointmentButton").disabled = false;
	}

</script>

<h2><spring:message code="appointments.form.title" /></h2>

<spring:hasBindErrors name="location">
	<spring:message code="fix.error"/>
	<br />
</spring:hasBindErrors>

<form:form commandName="appointment">
<fieldset>
	<table>
		<tr>
			<td>Patient Id</td>
			<td>
			<spring:bind path="patient">
				<openmrs_tag:patientField formFieldName="${status.expression}" initialValue="${status.value}" linkUrl="${pageContext.request.contextPath}/admin/patients/patient.form" callback="enableSaveButton" allowSearch="${encounter.encounterId == null}"/>
			</spring:bind>
			<form:errors path="patient" />
			</td>
		</tr>
		<tr>
			<td>Date</td>
			<td>
				<spring:bind path="when">
					<input type="text" name="${status.expression}" size="10" 
						   value="${status.value}" onClick="showCalendar(this)" />
				   <small>(<openmrs:datePattern />)</small>
				</spring:bind>
				<form:errors path="when" />
			</td>
		</tr>
		<tr>
			<td>Provider</td>
			<td>
				<spring:bind path="provider">
					<openmrs_tag:userField formFieldName="${status.expression}" initialValue="${status.value}" roles="Provider;" linkUrl="${pageContext.request.contextPath}/admin/users/user.form" callback="enableSaveButton"/>
				</spring:bind>
				<form:errors path="provider" />
			</td>
		</tr>
		<tr>
			<td>Location</td>
			<td>
				<spring:bind path="location">
					<openmrs:fieldGen formFieldName="${status.expression}" type="org.openmrs.Location" val="" />
				</spring:bind>
				<form:errors path="location" />
			</td>
		</tr>
		<tr>
			<td>Reason</td>
			<td><form:textarea path="reason" rows="3" />
				<form:errors path="reason" /></td>
		</tr>
	</table>

	<input type="hidden" name="phrase" value='<request:parameter name="phrase" />'/>
	<input type="submit" id="saveAppointmentButton" value='<spring:message code="appointments.Appointment.save"/>' disabled>
	&nbsp;
	<input type="button" value='<spring:message code="general.cancel"/>' onclick="history.go(-1); return; document.location='index.htm?autoJump=false&phrase=<request:parameter name="phrase"/>'">
</fieldset>
</form:form>

<%@ include file="/WEB-INF/template/footer.jsp"%>
