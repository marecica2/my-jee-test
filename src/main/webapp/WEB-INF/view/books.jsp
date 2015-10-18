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
	<h4>List of Books</h4>
	<c:if test="${info != null}">
		<h3>${info}</h3>
	</c:if>		
	<c:if test="${error != null}">
		<h3>${error}</h3>
	</c:if>		
	<table class="search" border="1">
	    <tr>
	        <th><fmt:message key="book.title"/></th>
	        <th><fmt:message key="book.author"/></th>
	        <th>Genre</th>
	        <th></th>
	    </tr>
		<c:forEach var="book" items="${books}" varStatus="status">
		    <tr>
		        <td>${book.title}</td> 
		        <td>${book.author}</td> 
		        <td>${book.bookType}</td> 
		        <td>
		        	<form method="POST" action="${pageContext.request.contextPath}/book/delete">
						<input type="hidden" name="id" value="${book.id}">
			        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			        	<button type="submit">delete</button>
		        	</form>
		        	<a href="${pageContext.request.contextPath}/books?edit=${book.id}">edit</a>
		        </td> 
		    </tr>
		</c:forEach>
	</table>
	
	<c:if test="${edit != null}">
		<c:set var="action" scope="page" value="edit"/>
		<h3>Edit Book</h3>
	</c:if>	
	<c:if test="${edit == null}">
		<c:set var="action" scope="page" value="save"/>
		<h3>Create Book</h3>
	</c:if>	
	<form:form action="${pageContext.request.contextPath}/book/${action}" modelAttribute="book">
		<c:if test="${edit != null}">
			<form:hidden path="id" />
			<form:hidden path="version" />
		</c:if>	
		<div>
			<form:select path="bookType">
				<form:option value="FICTION">Fiction</form:option>
				<form:option value="NOVEL">Novel</form:option>
				<form:option value="POETRY">Poetry</form:option>
			</form:select>
			<br/>
			<form:errors path="bookType" cssClass="error"/>
		</div>
		<div>
			<form:input path="author" placeholder="author" /><br/>
			<form:errors path="author" cssClass="error"/>
		</div>
		<br/>
		<div>
			<form:input path="title" placeholder="title"/><br/>
			<form:errors path="title" cssClass="error"/>
		</div>
		<br/>
		<div>
			<form:input path="pages"/><br/>
			<form:errors path="pages" cssClass="error"/>
		</div>
		<button type="submit">Submit</button>
		<a href="${pageContext.request.contextPath}/">Cancel</a>
	</form:form>
</body>
</html>