<%--
  Created by IntelliJ IDEA.
  User: sante
  Date: 09.12.2022
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${requestScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${requestScope.lang}>
<c:set var="title" value="client_orders" scope="page"/>
<%@include file="../jspf/head.jspf"%>
<body>

<%@include file="../jspf/header.jspf"%>
<jsp:useBean id="receipts" scope="session" type="java.util.List"/>
<div class="grey_background">
  <c:if test="${receipts.size()==0}">
    <div class="cart-empty">
      You didn't order anything yet.
    </div>
  </c:if>
  <c:if test="${receipts.size() != 0}">
  <table class="table">
    <thead>
    <tr>
      <th><fmt:message key="label.orderId"/></th>
      <th><fmt:message key="label.status"/></th>
      <th><fmt:message key="label.dishes"/></th>
      <th><fmt:message key="label.totalOrder"/></th>
      <th><fmt:message key="label.address-client"/> </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${receipts}" var="receipt">
      <jsp:useBean id="receipt" class="com.epam.donetc.restaurant.database.entity.Receipt"/>

      <tr>
        <td>${receipt.id}</td>
        <td>${receipt.status}</td>
        <td>
          <c:forEach items="${receipt.dishes}" var="dishAndAmount" >

            ${dishAndAmount.key.name}: ${dishAndAmount.key.price} * ${dishAndAmount.value}<br>
          </c:forEach>
        </td>
        <td>${receipt.total}</td>
        <td> <form method="post" action="${pageContext.request.contextPath}/controller?command=add_address">
          <input name="receiptId" style="display: none" value="${receipt.id}">
          <input class="amount-border" name="address" required minlength="5" >
          <input class="apply-cart" type="submit" value="<fmt:message key="label.applyButton"/>">
        </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<div class="menu-pagination">
  <form method="get" action="${pageContext.request.contextPath}/myOrders">
    <c:forEach var="number" begin="1" end="${maxPage}">
      <div class="menu-pagination-item">
        <input type="submit" name="currentPage" value="${number}" >
      </div>
    </c:forEach>
  </form>
</div>
</c:if>


</body>
</html>