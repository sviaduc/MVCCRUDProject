<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link href="css/master.css" rel="stylesheet">
<title>Stocks</title>
</head>
<body>
	<h1>Delete Stock</h1>
	<div class="container">
	<form:form action="DeleteStock.do" modelAttribute="stock">
		<table>
			
			<tr>
				<td>Symbol:</td>
				<td><form:input path="symbol" /></td>
				<td><form:errors path="symbol" /></td>
			</tr>
			
			
		</table>
		<input type="submit" value="Delete Stock" />
	</form:form>
	</div>
</body>
</html>