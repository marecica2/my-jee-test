<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div>
	<h4>Book store <a href="?lang=sk"><fmt:message key="sk"/></a> | <a href="?lang=en"><fmt:message key="en"/></a> </h4>
	<a href="${pageContext.request.contextPath}/">Home</a> | 
	<c:if test="${userSession.user == null}">
		<a href="${pageContext.request.contextPath}/login">Login</a>  
	</c:if>
	<c:if test="${userSession.user != null}">
		${userSession.user.login}
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input type="submit" value="Logout">
		</form:form>				
	</c:if>
	 	
</div>
<br/>
<br/>