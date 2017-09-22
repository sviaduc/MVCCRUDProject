<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Industry</title>
</head>
<body>
  <h3>Industry</h3>
    <%-- <p>${sessionScope.stock}</p> --%>
    
   <table>
  <c:forEach var="industry" items="${industry}" >
    <tr>
      <td><c:out value="${stock.industry}" /></td>
    </tr>
  </c:forEach>
</table>
    
  <%-- <c:choose>
    <c:when test="${! empty stock}">
      <ul>
        <li>${stock.industry}</li>
        <li>${stock.sector}</li>
        <li>${stock.name}</li>
        <li>${stock.symbol}</li>
        <li>${stock.marketcap}</li>
      </ul>
    </c:when>
    <c:otherwise>
      <p>No stock found</p>
    </c:otherwise>
  </c:choose> --%>
  <%-- <form action="moveButton.do" method="GET">
    <!--<input type="submit" name="next" value="Get Previous State" />  -->
    <input type="submit" name="next" value="Get Next State" />
    <input type="hidden" name="currentState" value="${state.abbreviation}" />
  </form> --%> 
</body>
</html>
