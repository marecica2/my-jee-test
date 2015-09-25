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
	<p>Current Locale : ${pageContext.response.locale}</p>	
	<a href="?lang=sk"><fmt:message key="sk"/></a> | <a href="?lang=en"><fmt:message key="en"/></a> 
	<br/>
	<br/>
	<table class="search" border="1">
	    <tr>
	        <th><fmt:message key="book.title"/></th>
	        <th><fmt:message key="book.author"/></th>
	        <th></th>
	    </tr>
		<c:forEach var="book" items="${books}" varStatus="status">
		    <tr>
		        <td>${book.title}</td> 
		        <td>${book.author}</td> 
		        <td>
		        	<form method="POST" action="${pageContext.request.contextPath}/book/delete">
						<input type="hidden" name="id" value="${book.id}">
			        	<button type="submit">delete</button>
		        	</form>
		        </td> 
		    </tr>
		</c:forEach>
	</table>
	
	<h3>Create Book</h3>
	<form:form action="${pageContext.request.contextPath}/book/add" modelAttribute="bookForm">
		<div>
			<form:input path="author"/><br/>
			<form:errors path="author" cssClass="error"/>
		</div>
		<div>
			<form:input path="title"/><br/>
			<form:errors path="title" cssClass="error"/>
		</div>
		<div>
			<form:input path="pages"/><br/>
			<form:errors path="pages" cssClass="error"/>
		</div>
		<button type="submit">Submit</button>
	</form:form>
</body>
</html>