<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Stocks</title>
</head>
<body>
	<h3>Delete Stock</h3>
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
</body>
</html>