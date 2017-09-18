<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Watchlist</title>
</head>
<body>
  <h3>Stock:</h3>
    <%-- <p>${sessionScope.stock}</p> --%>
  <c:choose>
    <c:when test="${! empty stock}">
      <ul>
        <li>${stock.industry}</li>
        <%-- <li>${stock.sector}</li> --%>
        <li>${stock.name}</li>
        <li>${stock.symbol}</li>
       <%--  <li>${stock.marketcap}</li> --%>
      </ul>
    </c:when>
    <c:otherwise>
      <p>No stock found</p>
    </c:otherwise>
  </c:choose>
  
   <form action="UpdateStock.do" method="GET">
  
    <input type="submit" name="updateStock" value="Update Stock" />
    <input type="hidden" name="name" value="${stock.name}" /> 
  </form>
</body>
</html>
