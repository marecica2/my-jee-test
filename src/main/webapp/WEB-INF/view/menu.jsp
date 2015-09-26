<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<h4>Book store <a href="?lang=sk"><fmt:message key="sk"/></a> | <a href="?lang=en"><fmt:message key="en"/></a> </h4>
	<a href="${pageContext.request.contextPath}/">Home</a> | 
	<c:if test="${userSession.user == null}">
		<a href="${pageContext.request.contextPath}/login">Login</a>  
	</c:if>
	<c:if test="${userSession.user != null}">
		${userSession.user.login}
		<a href="${pageContext.request.contextPath}/logout">Logout</a> 
	</c:if>
	 	
</div>
<br/>
<br/>