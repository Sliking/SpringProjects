<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script>
jQuery( document ).ready(function() {
	   jQuery("#person").attr("accept-charset", "UTF-8");
});
</script>

<form:form method="post" action="addOrUpdatePerson.do" commandName="person">
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
			<td><input class="btn btn-primary" type="submit" value="<spring:message code="label.addorupdateperson"/>" /></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</table>
</form:form>