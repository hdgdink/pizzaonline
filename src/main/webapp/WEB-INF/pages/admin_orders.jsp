<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.text">
  <html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
      <title><fmt:message key="default.welcome"/></title>
      <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
      <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
      <link href="<c:url value="/resources/css/modal.css" />" rel="stylesheet">

      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
      <script type="text/javascript" src="resources/js/bootstrap-select.js"></script>
      <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.js"></script>
    </head>

    <body>
      <div id="main">
        <t:header/>
        <t:navigation_user/>
        <t:navigation_admin/>

        <div class="section">
          <div class="caption">
            <h2>
              <fmt:message key="default.editOrders"/>
            </h2>

            <div class="msg-error">
              ${order_change_error}
            </div>

            <table class="table">
              <tr>
                <td><fmt:message key="default.ID"/></td>
                <td><fmt:message key="default.userId"/></td>
                <td><fmt:message key="default.sumOfOrder"/></td>
                <td><fmt:message key="default.orderAddress"/></td>
                <td><fmt:message key="default.orderPhone"/></td>
                <td><fmt:message key="default.status"/></td>
              </tr>

              <c:forEach items="${orderList}" var="order">
                <form:form id="edit_order" method="POST" action="edit_order">
                  <tr>
                    <input type="hidden" value="${order.id}" name="id"/>
                    <td>${order.id}</td>

                    <td>
                      <input type="number" class="form-control" name="userId" value="${order.userId}" required readonly="readonly"/>
                    </td>

                    <td>
                      <input type="number" class="form-control" name="sumOfOrder" value="${order.sumOfOrder}" required min="1" readonly="readonly"/>
                    </td>

                    <td>
                      <input type="text" class="form-control" name="address" value="${order.address}" required/>
                    </td>

                    <td>
                      <input type="text" class="form-control" name="phone" value="${order.phone}" required/>
                    </td>

                    <td>
                      <select class="select" data-width="auto" name="status">
                        <c:forEach items="${statusList}" var="status">
                          <option value="${status}"
                            <c:if test="${order.status==status}">selected</c:if>>${status}
                          </option>
                        </c:forEach>
                      </select>
                    </td>

                    <td>
                      <button type="submit" name="update" value="${order.id}" class="btn btn-primary">
                        <fmt:message key="default.update"/>
                      </button>
                    </td>
                  </tr>
                </form:form>
              </c:forEach>
            </table>

            <hr>

            <h3><fmt:message key="default.createOrder"/></h3>

            <form:form id="create_order" method="POST" action="create_order">
              <div class="input-group">

                <div class="col-lg-12">
                  <div class="input-group">
                    <span class="input-group-addon"><fmt:message key="default.userId"/></span>
                    <input type="number" class="form-control" id="userId_u" name="userId" value="" placeholder="..." required>
                  </div>
                </div>

                <div class="col-lg-12">
                  <div class="input-group">
                    <span class="input-group-addon"><fmt:message key="default.sumOfOrder"/></span>
                    <input type="number" class="form-control" id="sumOfOrder_u" name="sumOfOrder" value="" placeholder="..." required min="1">
                  </div>
                </div>

                <div class="col-lg-12">
                  <div class="input-group">
                    <span class="input-group-addon"><fmt:message key="default.address"/></span>
                    <input type="text" class="form-control" id="address_u" name="address" value="" placeholder="..." required>
                  </div>
                </div>

                <div class="col-lg-12">
                  <div class="input-group">
                    <span class="input-group-addon"><fmt:message key="default.orderPhone"/></span>
                    <input type="text" class="form-control" id="phone_u" name="phone" value="" placeholder="..." required>
                  </div>
                </div>

                <div class="col-lg-12">
                  <div class="input-group">
                    <span class="input-group-addon"><fmt:message key="default.active"/></span>
                    <select class="select" data-width="auto" name="status">
                      <c:forEach items="${statusList}" var="status1">
                        <option value="${status1}" <c:if test="${status1==status}">selected</c:if>>
                          ${status1}
                        </option>
                      </c:forEach>
                    </select>
                  </div>
                </div>

                <hr>
                <br>

                <div class="col-lg-12">
                  <button type="submit" class="btn btn-success">
                    <fmt:message key="default.create"/>
                  </button>
                </div>
              </div>
            </form:form>
          </div>
        </div>

        <div id="rasporka"></div>
      </div>
      <t:footer/>
    </body>
  </html>
</fmt:bundle>