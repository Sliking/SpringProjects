<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
jQuery( document ).ready(function() {
	$.datepicker.setDefaults($.datepicker.regional['pt']); 
	$.datepicker.setDefaults({ dateFormat: 'yy-mm-dd' });
	
	$('#datetimepicker1').datepicker();
});
</script>

<form:form method="post" action="addOrUpdateDocument.do" commandName="document">
	<form:hidden path="id"/>
	<table class="table">
		<tr>
			<td><form:label path="author"><spring:message code="label.author" /></form:label></td>
			<td><form:input  path="author" cssClass="form-control" /></td>
			<td><form:errors path="author" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><form:label path="length"><spring:message code="label.length" /></form:label></td>
			<td><form:input path="length" cssClass="form-control" type="number"/></td>
			<td><form:errors path="length" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><form:label path="extention"><spring:message code="label.extention" /></form:label></td>
			<td><form:input path="extention" cssClass="form-control"/></td>
			<td><form:errors path="extention" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><form:label path="creationDate"><spring:message code="label.creationDate" /></form:label></td>
			<td>
				<fmt:formatDate value="${document.creationDate}" var="dateString" pattern="yyyy-mm-dd" />
				<form:input path="creationDate" cssClass="form-control" id='datetimepicker1' pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"/>
			</td>
			<td><form:errors path="creationDate" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><input class="btn btn-primary" type="submit" value="<spring:message code="label.addorupdatedocument"/>" /></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</table>
</form:form>