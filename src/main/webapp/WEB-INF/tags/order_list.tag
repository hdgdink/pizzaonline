<%@tag description="list of orders" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div id="order" class="section">
  <h1><fmt:message key="default.order"/></h1>
  <hr>
  <t:info info="${succesfullyOrdered}"/>

  <div>
    <table class="table">

      <tr>

        <td>
          <fmt:message key="default.name"/>
        </td>

        <td>
          <fmt:message key="default.size"/>
        </td>

        <td>
          <fmt:message key="default.count"/>
        </td>

        <td>
          <fmt:message key="default.price"/>
        </td>

      </tr>
    </table>

    <c:forEach items="${order_details}" var="product">
      <form action="del_from_orderlist" method="get">
        <div class="caption">
          <table class="table">
            <tr>

              <c:if test="${locale.language=='ru'}">
                <td>
                  ${product.foodNameRu}
                </td>
              </c:if>

              <c:if test="${locale.language=='en'}">
                <td>
                  ${product.foodNameEn}
                </td>
              </c:if>

              <td>
                <fmt:message key="${product.sizeName}"/>
              </td>

              <td>
                ${product.quantity}
              </td>

              <td>
                ${product.finalPrice}
              </td>

              <input type="hidden" name="order_detail_id" value="${product.id}"/>
              <input type="hidden" name="order_detail_final_price" value="${product.finalPrice}"/>

              <td>
                <input type="submit" class="btn btn-primary" value="✖"/>
              </td>

            </tr>
          </table>
        </div>
      </form>
    </c:forEach>
  </div>

  <h3 class="h3"><fmt:message key="default.sumOfOrder"/>${order.sumOfOrder}</h3>
  <hr>

    <c:choose>
      <c:when test="${order.sumOfOrder==0 || order.sumOfOrder==null}">
        <a href="#error_form" title="Add to order" class="btn btn-primary">
          <fmt:message key="default.checkout"/>
        </a>
      </c:when>

      <c:otherwise>
        <a href="#address_form" title="Add to order" class="btn btn-primary">
          <fmt:message key="default.checkout"/>
        </a>
      </c:otherwise>
    </c:choose>


    <a href="#x" class="overlay" id="error_form"></a>

    <div class="popup">
      <h2>
        <fmt:message key="default.listIsEmpty"/>
      </h2>

      <a class="close" href="#close"></a>
    </div>

    <a href="#x" class="overlay" id="address_form"></a>

    <div class="popup">
      <form action="chekout_order" method="post">
        <h4>
          <fmt:message key="default.address"/>
        </h4>

        <hr>

        <h6>
          <fmt:message key="default.yourAddress"/>
        </h6>

        <input type="text" name="address" required/>
        <br>
        <hr>

        <h6>
          <fmt:message key="default.yourPhone"/>
        </h6>

        <input type="tel" pattern="^\(\d{3}\)\d{3}-\d{2}-\d{2}$" name="phone" required/>
        <br>
        <hr>

        <t:error-info error="${error}"/>
        <input type="submit" class="btn btn-primary" value="<fmt:message key="default.checkout"/>"/>
        <a class="close" href="#close"></a>
      </form>
    </div>
</div>