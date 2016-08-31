<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script>
jQuery( document ).ready(function() {
	   jQuery("#menuItem").attr("accept-charset", "UTF-8");
});
</script>

<form:form method="post" action="addMenuItem.do" commandName="menuItem">
	<form:hidden path="id" id="formId"/>
	<table class="table">
		<tr>
			<td style="width:20%"><form:label path="label"><spring:message code="label.label" /></form:label></td>
			<td style="width:40%"><form:input path="label" cssClass="form-control" id="formLabel" /></td>
			<td style="width:40%"><form:errors path="label" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><form:label path="href"><spring:message code="label.href" /></form:label></td>
			<td><form:input  path="href" cssClass="form-control" id="formHref" /></td>
			<td><form:errors path="href" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><form:label path="position"><spring:message code="label.position" /></form:label></td>
			<td><form:input  path="position" cssClass="form-control" type="number" id="formPosition"/></td>
			<td><form:errors path="position" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><input class="btn btn-primary" type="submit" value="<spring:message code="label.addmenuitem"/>" /></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</table>
</form:form>