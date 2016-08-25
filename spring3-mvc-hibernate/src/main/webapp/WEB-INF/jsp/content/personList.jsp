<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty personList}">
	<table class="table	table-hover">
		<tr>
			<th><spring:message code="label.name"/></th>
			<th>&nbsp;</th>
			<th>&nbsp;</th>
		</tr>

		<c:forEach items="${personList}" var="person">
			<tr>
				<td>${person.lastname}, ${person.firstname}</td>
				<td>
					<a href="getPersonDetail.do?personId=${person.id}">
						<span class="glyphicon glyphicon-edit"></span>
					</a>
				</td>
				<td>
					<a href="deletePerson.do?personID=${person.id}">
						 <span class="glyphicon glyphicon-remove"></span>
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
<c:if test="${empty personList}">
	<h3><spring:message code="person.nopersonstoshow"/></h3>
</c:if>