<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<form:form method="post" action="addOrUpdateUser.do" commandName="userID">
	<form:hidden path="username"/>
	<table class="table">
		<tr>
			<td style="width:20%"><form:label path="username"><spring:message code="label.name" /></form:label></td>
			<td style="width:40%"><form:input path="username" cssClass="form-control"/></td>
			<td style="width:40%"><form:errors path="username" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><form:label path="password"><spring:message code="label.password" /></form:label></td>
			<td><form:input  path="password" cssClass="form-control" type="password"/></td>
			<td><form:errors path="password" cssClass="h6" /></td>
		</tr>
		<tr>
			<td><label for="passworRepeat"><spring:message code="label.passwordRepeat" /></label></td>
			<td><input id="passworRepeat" name="lastname" class="form-control" type="password" value="" ></td>
			<td><span id="passwordRepeatErrors" class="h6"></span></td>
		</tr>
		<tr>
			<td><button class="btn btn-primary"  type="button" onclick="validatePasswordSubmit();" ><spring:message code="label.adduser"/></button></td>			
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</table>
</form:form>