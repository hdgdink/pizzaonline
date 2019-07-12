<%@tag description="navigation_bar_for_admin" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="nav_section">
  <nav role="navigation">
    <ul>
      <li>
        <a href="admin_cabinet">
          <fmt:message key="default.editUser"/>
        </a>
      </li>

      <li>
        <a href="admin_products">
          <fmt:message key="default.editfood"/>
        </a>
      </li>

      <li>
        <a href="admin_orders">
          <fmt:message key="default.editOrders"/>
        </a>
      </li>

      <li>
        <a href="admin_order_details">
          <fmt:message key="default.editOrderDetails"/>
        </a>
      </li>

      <li>
        <a href="admin_sizes">
          <fmt:message key="default.editSize"/>
        </a>
      </li>

      <li>
        <a href="admin_types">
          <fmt:message key="default.editType"/>
        </a>
      </li>
    </ul>
  </nav>
</div>