<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:forEach var="menuItem" items="${menu}" >

	<tr class="treegrid-${menuItem.id} 
		${(not empty menuItem.parentId ? 'treegrid-parent-' : '')}${(not empty menuItem.parentId ?  menuItem.parentId : '')}
	">
		
		<td>${menuItem.label}</td>
		<td>${menuItem.href}</td>
		<td>${menuItem.position}</td>
		<td>
			<a class="addChildMenuItem" data-parentId="${menuItem.id}">
				<span class="glyphicon glyphicon-plus"></span>
			</a>
		</td>
		<td>
			<a class="editMenuItem" data-id="${menuItem.id}"
				data-position="${menuItem.position}" data-href="${menuItem.href}"
				data-label="${menuItem.label}">
				<span class="glyphicon glyphicon-edit"></span>
			</a>
		</td>
		<td>
			<a href="deleteMenuItem.do?menuItemID=${menuItem.id}">
				<span class="glyphicon glyphicon-remove"></span>
			</a>
		</td>
	</tr><!-- This tag is opened on choose above -->
	<c:if test="${fn:length(menuItem.childs) > 0}">
		<c:set var="menu" value="${menuItem.childs}" scope="request"/>
		<jsp:include page="/WEB-INF/jsp/include/creategridtreelist.jsp"/>
    </c:if>
</c:forEach>