<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://pt.link.libraries/linktables" prefix="linktable"%>

<script>
jQuery( document ).ready(function() {
	   jQuery("#permission").attr("accept-charset", "UTF-8");
});
</script>

<spring:url value="/permissions.html" var="urlExemplo">
	<spring:param name="id" value="${permissionDTO.profileID}" />
	<spring:param name="name" value="${permissionDTO.profileName}" />
</spring:url>

<form:form method="post" action="changePermission.do" modelAttribute="permissionDTO">
<form:hidden path="profileID"/>	
	<linktable:table id="permissionTable" tableObject="${permissionTable}" currentUrl="${urlExemplo}" selectableRowsPropertyName="permissionIDList" tableCssClasses="table table-hover">

	</linktable:table>
	<button id="selectElements" type="submit" class="btn btn-primary">Atualizar</button>
	
</form:form>

