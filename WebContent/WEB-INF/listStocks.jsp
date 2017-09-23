<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">

<title>Stocks</title>
</head>
<body>
	<h1>Watchlist</h1>
	<div class="container">
	<table>
	<form:form action="listStocks.do" modelAttribute="stock">
    <c:forEach var="stock" items="${stocks}">
        <tr><td>Stock: ${stock.name}, ${stock.symbol}, ${stock.industry}</td></tr>
    </c:forEach>
	</form:form>
 
  </table>
	</div>
	  
</body>
</html>