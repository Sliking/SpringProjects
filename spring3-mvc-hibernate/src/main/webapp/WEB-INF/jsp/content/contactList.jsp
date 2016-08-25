<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${!empty contactList}">
	<table class="table	table-hover">
		<tr>
			<th><a href="index.html?order=firstname"><spring:message code="label.name"/></a>
				<c:if test="${orderEnum == 'firstName'}">
					<span class="glyphicon ${orderWay == 'asc' ? 'glyphicon glyphicon-sort-by-alphabet':'glyphicon glyphicon-sort-by-alphabet-alt'}"></span>
				</c:if>
			</th>
			<th><a href="index.html?order=email"><spring:message code="label.email"/></a>
				<c:if test="${orderEnum == 'email'}">
					<span class="glyphicon ${orderWay == 'asc' ? 'glyphicon glyphicon-sort-by-alphabet':'glyphicon glyphicon-sort-by-alphabet-alt'}"></span>
				</c:if>
			</th>
			<th><a href="index.html?order=telephone"><spring:message code="label.telephone"/></a>
				<c:if test="${orderEnum == 'telephone'}">
					<span class="glyphicon ${orderWay == 'asc' ? 'glyphicon glyphicon-sort-by-order':'glyphicon glyphicon-sort-by-order-alt'}"></span>
				</c:if>
			</th>
			<th><a href="index.html?order=birthday"><spring:message code="label.birthDay"/></a>
				<c:if test="${orderEnum == 'birthDay'}">
					<span class="glyphicon ${orderWay == 'asc' ? 'glyphicon glyphicon-sort-by-order':'glyphicon glyphicon-sort-by-order-alt'}"></span>
				</c:if>
			</th>
			<th><a href="index.html?order=address"><spring:message code="label.address"/></a>
				<c:if test="${orderEnum == 'address'}">
					<span class="glyphicon ${orderWay == 'asc' ? 'glyphicon glyphicon-sort-by-alphabet':'glyphicon glyphicon-sort-by-alphabet-alt'}"></span>
				</c:if>
			</th>
			<th><a href="index.html?order=club"><spring:message code="label.club"/></a>
				<c:if test="${orderEnum == 'club'}">
					<span class="glyphicon ${orderWay == 'asc' ? 'glyphicon glyphicon-sort-by-alphabet':'glyphicon glyphicon-sort-by-alphabet-alt'}"></span>
				</c:if>
			</th>
			<td>&nbsp;</td>
			<td>&nbsp;</td>

		
		<c:forEach items="${contactList}" var="contact">
		
			<tr>
				<td>
				
				${contact.lastname}, ${contact.firstname}
				</td>
				<td>${contact.email}</td>
				<td>${contact.telephone}</td>
				
				<td><fmt:formatDate pattern="dd-MM-yyyy" value="${contact.birthDay}" /></td>
				<td>${contact.address} - ${contact.zipCode}</td>
				<td>${contact.club}</td>
				<td>
					<a href="getDetail.do?contactId=${contact.id}">
						<span class="glyphicon glyphicon-edit"></span>
					</a>
				</td>
				<td>
					<a href="delete.do?contactId=${contact.id}">
						 <span class="glyphicon glyphicon-remove"></span>
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
<c:if test="${empty contactList}">
	<h3><spring:message code="label.nocontactstoshow"/></h3>
</c:if>