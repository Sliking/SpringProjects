<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty clubList}">
	<table class="table	table-hover">
		<tr>
			<th><spring:message code="label.name"/></th>
			<th>&nbsp;</th>
			<th>&nbsp;</th>
		</tr>

		<c:forEach items="${clubList}" var="club">
			<tr>
				<td>${club.name}</td>
				<td>
					<a href="getClubDetail.do?clubId=${club.id}">
						<span class="glyphicon glyphicon-edit"></span>
					</a>
				</td>
				<td>
					<a href="deleteClub.do?clubID=${club.id}">
						 <span class="glyphicon glyphicon-remove"></span>
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
<c:if test="${empty clubList}">
	<h3><spring:message code="club.noclubstoshow"/></h3>
</c:if>