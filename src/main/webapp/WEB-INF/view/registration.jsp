<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Registration</title>
</head>
<body>
	<%@ include file="menu.jsp" %>
	
	<c:if test="${edit == null}">
		<c:set var="action" scope="page" value="add"/>
		<h3>Registration</h3>
	</c:if>	
	<c:if test="${edit != null}">
		<c:set var="action" scope="page" value="edit"/>
		<h3>Update Personal settings</h3>
	</c:if>	
	
	<form:form action="${pageContext.request.contextPath}/registration" modelAttribute="user">
		<c:if test="${edit != null}">
			<form:hidden path="id" />
		</c:if>	
		<div>
			Login
			<form:input path="login" placeholder="Login"/><br/>
			<form:errors path="login" cssClass="error"/>
		</div>
		<div>
			First name
			<form:input path="firstName" placeholder="first name"/><br/>
			<form:errors path="firstName" cssClass="error"/>
		</div>
		<div>
			Last name
			<form:input path="lastName" placeholder="last name"/><br/>
			<form:errors path="lastName" cssClass="error"/>
		</div>
		<div>
			Password
			<form:password path="password"/><br/>
			<form:errors path="password" cssClass="error"/>
		</div>
		
		<button type="submit">Submit</button>
		<a href="${pageContext.request.contextPath}/">Cancel</a>
	</form:form>
</body>
</html>