<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script>
jQuery( document ).ready(function() {
	   jQuery("#profile").attr("accept-charset", "UTF-8");
});
</script>

<form:form method="post" action="addOrUpdateProfile.do" commandName="profile">
	<form:hidden path="profile_id"/>
	<table class="table">
		<tr>
			<td style="width:20%"><form:label path="profile_id"><spring:message code="label.id" /></form:label></td>
			<td style="width:40%"><form:input path="profile_id" cssClass="form-control"/></td>
			<td style="width:40%"><form:errors path="profile_id" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><form:label path="profile_name"><spring:message code="label.name" /></form:label></td>
			<td><form:input  path="profile_name" cssClass="form-control" /></td>
			<td><form:errors path="profile_name" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><input class="btn btn-primary" type="submit" value="<spring:message code="label.addorupdateprofile"/>" /></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</table>
</form:form>