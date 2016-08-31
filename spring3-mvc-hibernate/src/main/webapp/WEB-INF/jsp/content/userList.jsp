<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://pt.link.libraries/linktables" prefix="linktable"%>

<spring:url value="/users.html" var="urlExemplo">
	<spring:param name="username" value="${userID.username}" />
</spring:url>

<form:form method="post" action="changePermissions.do" modelAttribute="userID">	
	<linktable:table id="userTable" tableObject="${userTable}" currentUrl="${urlExemplo}" tableCssClasses="table table-hover">
		<jsp:attribute name="additionalHeaders">
             <th><spring:message code="comum.opcoes" /></th>
                                                            
		</jsp:attribute>
       <jsp:attribute name="additionalColumns">                                         
       <td>
            <spring:url value="profiles.html" var="rowUrlEdit">
                    <spring:param name="username" value="${rowObject.values['username']}" />
            </spring:url>
            <a href="${rowUrlEdit}"><span class="glyphicon glyphicon-edit"></span></a>                                                     
		</td>
                                                   
        </jsp:attribute> 
		
	</linktable:table>
	
</form:form>