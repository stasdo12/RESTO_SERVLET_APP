<%--
  Created by IntelliJ IDEA.
  User: sante
  Date: 09.12.2022
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mylib" tagdir="/WEB-INF/tags" %>

<html>
<c:set var="title" value="Orders" scope="page"/>
<%@include file="../jspf/head.jspf"%>
<body>

<%@include file="../jspf/header.jspf"%>

<div class="grey_background">


  <table class="table">
    <thead>
    <tr>
      <th>Order id</th>
      <th>User id</th>
      <th>Status</th>
      <th>Dishes</th>
      <th>Total</th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="receipts" scope="session" type="java.util.List"/>
    <c:forEach items="${receipts}" var="receipt">
      <jsp:useBean id="receipt" class="com.epam.donetc.restaurant.database.entity.Receipt"/>

      <tr>
        <td>${receipt.id}</td>
        <td>${receipt.user.id}</td>
        <td>${receipt.status}

          <form class="menu_filter_sort" method="post" action="${pageContext.request.contextPath}/manageOrders">
            <select  id="status" name="status">
              <option value="New">New</option>
              <option value="Approved">Approved</option>
              <option value="Cancelled">Cancelled</option>
              <option value="Cooking">Cooking</option>
              <option value="Delivering">Delivering</option>
              <option value="Received">Received</option>
            </select>
            <input value="${receipt.id}" name="id" style="display: none">
            <input class="manager-orders-apply" type="submit" value="Apply">

          </form>
        </td>
        <td>
          <c:forEach items="${receipt.dishes}" var="dishAndAmount">

            ${dishAndAmount.key.name}: ${dishAndAmount.key.price} * ${dishAndAmount.value}<br>
          </c:forEach>
        </td>
        <td>${receipt.total}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<div class="menu-pagination">
  <form method="get" action="${pageContext.request.contextPath}/manageOrders">
    <c:forEach var="number" begin="1" end="${maxPage}">
      <div class="menu-pagination-item">
        <input type="submit" name="currentPage" value="${number}"  >
      </div>
    </c:forEach>
  </form>
</div>
<%@include file="../jspf/footer.jspf"%>

</body>
</html>