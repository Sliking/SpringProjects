<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
jQuery( document ).ready(function() {
	$.datepicker.setDefaults($.datepicker.regional['pt']); 
	$.datepicker.setDefaults({ dateFormat: 'dd-mm-yy' });
	
	$('#datetimepicker1').datepicker();
});
</script>

<form:form method="post" action="addOrUpdate.do" commandName="contact">
	<form:hidden path="id"/>
	<table class="table">
		<tr>
			<td style="width:20%"><form:label path="firstname"><spring:message code="label.firstname" /></form:label></td>
			<td style="width:40%"><form:input path="firstname" cssClass="form-control"/></td>
			<td style="width:40%"><form:errors path="firstname" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><form:label path="lastname"><spring:message code="label.lastname" /></form:label></td>
			<td><form:input  path="lastname" cssClass="form-control" /></td>
			<td><form:errors path="lastname" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><form:label path="birthDay"><spring:message code="label.birthDay" /></form:label></td>
			<td>
				<fmt:formatDate value="${contact.birthDay}" var="dateString" pattern="dd-MM-yyyy" />
				<form:input path="birthDay" cssClass="form-control" id='datetimepicker1'  />
			</td>
			<td><form:errors path="birthDay" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><form:label path="email"><spring:message code="label.email" /></form:label></td>
			<td><form:input path="email" cssClass="form-control"/></td>
			<td><form:errors path="email" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><form:label path="telephone"><spring:message code="label.telephone" /></form:label></td>
			<td><form:input path="telephone" cssClass="form-control" type="number"/></td>
			<td><form:errors path="telephone" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><form:label path="address"><spring:message code="label.address" /></form:label></td>
			<td><form:input path="address" cssClass="form-control"/></td>
			<td><form:errors path="address" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><form:label path="zipCode"><spring:message code="label.zipCode" /></form:label></td>
			<td><form:input path="zipCode" cssClass="form-control"/></td>
			<td><form:errors path="zipCode" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><form:label path="club"><spring:message code="label.club" /></form:label></td>
			<td><form:input path="club" cssClass="form-control"/></td>
			<td><form:errors path="club" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><input class="btn btn-primary" type="submit" value="<spring:message code="label.addorupdatecontact"/>" /></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</table>
</form:form>