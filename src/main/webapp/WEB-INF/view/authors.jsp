<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Authors</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript.js"></script>
</head>
<body>
	<%@ include file="menu.jsp" %>
	
	<h4>List of Authors</h4>
	<table class="search" border="1">
	    <tr>
	        <th>First Name</th>
	        <th>Last Name</th>
	        <th></th>
	    </tr>
		<c:forEach var="author" items="${authors}" varStatus="status">
		    <tr>
		        <td>${author.firstName}</td> 
		        <td>${author.lastName}</td> 
		        <td>
		        	<form method="POST" action="${pageContext.request.contextPath}/author/delete">
						<input type="hidden" name="id" value="${author.id}">
			        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			        	<button type="submit">delete</button>
		        	</form>
		        	<a href="?edit=${author.id}">edit</a>
		        </td> 
		    </tr>
		</c:forEach>
	</table>
	
	<c:if test="${edit != null}">
		<c:set var="action" scope="page" value="edit"/>
		<h3>Edit author</h3>
	</c:if>	
	<c:if test="${edit == null}">
		<c:set var="action" scope="page" value="add"/>
		<h3>Create author</h3>
	</c:if>	
	<form:form action="${pageContext.request.contextPath}/author/${action}" modelAttribute="author">
		<c:if test="${edit != null}">
			<form:hidden path="id" />
		</c:if>	
		<div>
			<form:input path="firstName"/><br/>
			<form:errors path="firstName" cssClass="error"/>
		</div>
		<div>
			<form:input path="lastName"/><br/>
			<form:errors path="lastName" cssClass="error"/>
		</div>
		
		
		<button type="submit">Submit</button>
		<a href="${pageContext.request.contextPath}/">Cancel</a>
	</form:form>
		
	<c:if test="${edit != null}">
		<p>Books</p>
		<c:forEach var="book" items="${author.books}">
			${book} <a href="${pageContext.request.contextPath}/author/book/delete">delete</a></br>
		</c:forEach>
		
		<p>Add book</p>
		<form action="${pageContext.request.contextPath}/author/book/add" method="post">
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        	<input type="hidden" name="author" value="${author.id}"/>
			<select name="book">
				<c:forEach var="book" items="${books}">
					<option value="${book.id}">${book.title}</option>
				</c:forEach>
			<select>
			<input type="submit">
		</form>		
	</c:if>	
			


</body>
</html>