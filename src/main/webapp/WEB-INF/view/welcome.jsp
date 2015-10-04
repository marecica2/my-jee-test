<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Books</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript.js"></script>
</head>
<body>
	<%@ include file="menu.jsp" %>
	<h4>Welcome to book store</h4>
	<a href="${pageContext.request.contextPath}/books">Go to store</a> <br/>
	<a href="${pageContext.request.contextPath}/registration">Register</a> <br/>
</body>
</html>