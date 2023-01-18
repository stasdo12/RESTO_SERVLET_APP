<%@ attribute name="user" type="com.epam.donetc.restaurant.database.entity.User" required="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<fmt:message key="label.tag"/>  <font color="#228b22" size="2"><b>${user.login}</b></font>