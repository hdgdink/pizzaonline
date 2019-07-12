<%@tag description="navigation_bar_for_user" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav role='navigation'>
  <ul>
    <li>
      <a href="pizza_logged">
        <fmt:message key="default.pizza"/>
      </a>
    </li>

    <li>
      <a href="subs_logged">
        <fmt:message key="default.subs"/>
      </a>
    </li>

    <li>
      <a href="bev_logged">
        <fmt:message key="default.beverages"/>
      </a>
    </li>
  </ul>
</nav>