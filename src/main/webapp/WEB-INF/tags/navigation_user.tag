<%@tag description="navigation_bar_for_user" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav role='navigation'>
    <ul>
        <li><a href="pizza_loged"><fmt:message key="default.pizza"/></a></li>
        <li><a href="subs_loged"><fmt:message key="default.subs"/></a></li>
        <li><a href="beverage_loged"><fmt:message key="default.beverages"/></a></li>
        <li><a href="cabinet"><fmt:message key="default.cabinet"/></a></li>
    </ul>
</nav>