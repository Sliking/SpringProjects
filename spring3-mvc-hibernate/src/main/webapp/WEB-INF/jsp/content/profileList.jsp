<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://pt.link.libraries/linktables" prefix="linktable"%>

<script>
jQuery( document ).ready(function() {
	   jQuery("#profile").attr("accept-charset", "UTF-8");
});
</script>

<spring:url value="/profiles.html" var="urlExemplo">
	<spring:param name="username" value="${profileDTO.username}" />
</spring:url>

<form:form method="post" action="changeProfile.do" modelAttribute="profileDTO">	
	<form:hidden path="id" />
	<linktable:table id="profileTable" tableObject="${profileTable}" currentUrl="${urlExemplo}" selectableRowsPropertyName="profileIDList" tableCssClasses="table table-hover">
		<jsp:attribute name="additionalColumns">                                         
			<td>
				<spring:url value="permissions.html" var="rowUrlEdit">
					<spring:param name="id" value="${rowObject.values['id']}" />
				</spring:url>
				<a href="${rowUrlEdit}"><span class="glyphicon glyphicon-edit"></span></a>  
			</td>                                       
		</jsp:attribute> 
	</linktable:table>
	<button id="selectElements" type="submit" class="btn btn-primary">Atualizar</button>
	
</form:form>

