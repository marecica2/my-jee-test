<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
</head>
<body>
	<%@ include file="menu.jsp" %>
	
	<h3>Login</h3>
	<form:form action="${pageContext.request.contextPath}/login" modelAttribute="user">
		<div>
			<form:input path="login"/><br/>
			<form:errors path="login" cssClass="error"/>
		</div>
		<div>
			<form:password path="password"/><br/>
			<form:errors path="password" cssClass="error"/>
		</div>
		
		<button type="submit">Submit</button>
		<a href="${pageContext.request.contextPath}/registration">Registration</a> |
		<a href="${pageContext.request.contextPath}/">Cancel</a>
	</form:form>
</body>
</html>