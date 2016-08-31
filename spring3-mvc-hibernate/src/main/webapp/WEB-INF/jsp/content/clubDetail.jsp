<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script>
jQuery( document ).ready(function() {
	   jQuery("#club").attr("accept-charset", "UTF-8");
});
</script>

<form:form method="post" action="addOrUpdateClub.do" commandName="club">
	<form:hidden path="id" id="formId"/>
	<table class="table">
		<tr>
			<td style="width:20%"><form:label path="name"><spring:message code="label.name" /></form:label></td>
			<td style="width:40%"><form:input path="name" cssClass="form-control" /></td>
			<td style="width:40%"><form:errors path="name" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><input class="btn btn-primary" type="submit" value="<spring:message code="label.addorupdateclub"/>" /></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</table>
</form:form>