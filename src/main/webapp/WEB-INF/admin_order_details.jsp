<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.text">
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title><fmt:message key="default.welcome"/></title>
        <link rel="stylesheet" type="text/css" href="../static/css/style.css"/>
        <link rel="stylesheet" type="text/css" href="../static/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="../static/css/modal.css"/>
        <script type='text/javascript' src='<c:url value="/static/js/bootstrap-select.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/webjars/jquery/1.11.1/jquery.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.js"/>'></script>
    </head>
    <body>

    <div id="main">

        <t:header/>
        <t:navigation_user/>
        <t:navigation_admin/>

        <div class="section">

            <div class="caption">
                <h2><fmt:message key="default.editOrderDetails"/></h2>
                <div class="msg-error">${orderDetails_change_error}</div>
                <table class="table">
                    <tr>
                        <td><fmt:message key="default.ID"/></td>
                        <td><fmt:message key="default.productId"/></td>
                        <td><fmt:message key="default.nameRu"/></td>
                        <td><fmt:message key="default.nameEn"/></td>
                        <td><fmt:message key="default.sizeName"/></td>
                        <td><fmt:message key="default.typeId"/></td>
                        <td><fmt:message key="default.typeName"/></td>
                        <td><fmt:message key="default.count"/></td>
                        <td><fmt:message key="default.orderId"/></td>
                        <td><fmt:message key="default.price"/></td>
                    </tr>
                    <c:forEach items="${allOrderDetails}" var="orderDetails">
                        <form id="edit_order_details" method="POST" action="edit_order_details">
                            <tr>
                                <input type="hidden" value="${orderDetails.id}" name="id"/>
                                <td>${orderDetails.id}</td>
                                <td>
                                    <input type="number" class="form-control" name="productId"
                                           value="${orderDetails.foodId}"
                                           required readonly="readonly"/>
                                </td>
                                <td>
                                    <input type="text" class="form-control" name="nameRu" readonly="readonly"
                                           value="${orderDetails.foodNameRu}"/>
                                </td>
                                <td>
                                    <input type="text" class="form-control" name="nameEn" readonly="readonly"
                                           value="${orderDetails.foodNameEn}"/>
                                </td>
                                <td>
                                    <input type="text" class="form-control" name="sizeName" readonly="readonly"
                                           value="${orderDetails.sizeName}"/>
                                </td>
                                <td>
                                    <input type="text" class="form-control" name="typeId" readonly="readonly"
                                           value="${orderDetails.typeId}"/>
                                </td>
                                <td>
                                    <input type="text" class="form-control" name="typeName" readonly="readonly"
                                           value="${orderDetails.typeName}"/>
                                </td>
                                <td>
                                    <input type="text" class="form-control" name="quantity" readonly="readonly"
                                           value="${orderDetails.quantity}"/>
                                </td>
                                <td>
                                    <input type="text" class="form-control" name="orderId" readonly="readonly"
                                           value="${orderDetails.orderId}"/>
                                </td>
                                <td>
                                    <input type="text" class="form-control" name="price" readonly="readonly"
                                           value="${orderDetails.finalPrice}"/>
                                </td>

                                <td>
                                    <button type="submit" name="update" value="${orderDetails.id}"
                                            class="btn btn-primary">
                                        <fmt:message key="default.update"/></button>
                                </td>
                                <td>
                                    <button type="submit" name="delete" value="${orderDetails.id}"
                                            class="btn btn-primary">
                                        <fmt:message key="default.delete"/></button>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </table>

                <hr>

                <h3><fmt:message key="default.createDetails"/></h3>
                <form id="create_order_details" method="POST" action="create_order_details">
                    <div class="input-group">

                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.productId"/></span>
                                <input type="number" class="form-control" id="productId_u" name="productId"
                                       value="" placeholder="..." readonly="readonly" required>
                            </div>
                        </div>

                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.nameRu"/></span>
                                <input type="text" class="form-control" id="nameRu_u" name="nameRu"
                                       value="" placeholder="..." readonly="readonly" required>
                            </div>
                        </div>

                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.nameEn"/></span>
                                <input type="text" class="form-control" id="nameEn_u" name="nameEn"
                                       value="" placeholder="..." readonly="readonly" required>
                            </div>
                        </div>

                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.typeId"/></span>
                                <input type="number" class="form-control" id="typeId_u" name="typeId"
                                       value="" placeholder="..." readonly="readonly" required>
                            </div>
                        </div>

                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.typeName"/></span>
                                <input type="text" class="form-control" id="typeName_u" name="typeName"
                                       value="" placeholder="..." readonly="readonly" required>
                            </div>
                        </div>

                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.sizeName"/></span>
                                <input type="text" class="form-control" id="sizeName_u" name="sizeName"
                                       value="" placeholder="..." readonly="readonly" required>
                            </div>
                        </div>

                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.count"/></span>
                                <input type="number" class="form-control" id="count_u" name="count"
                                       value="" placeholder="..." readonly="readonly" required min="1">
                            </div>
                        </div>

                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.orderId"/></span>
                                <input type="number" class="form-control" id="orderId_u" name="order_Id"
                                       value="" placeholder="..." readonly="readonly" required>
                            </div>
                        </div>

                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.price"/></span>
                                <input type="number" class="form-control" id="price_u" name="price"
                                       value="" placeholder="..." readonly="readonly" required min="1">
                            </div>
                        </div>

                        <hr>
                        <br>
                        <div class="col-lg-12">
                            <button type="submit" class="btn btn-success"><fmt:message
                                    key="default.create"/></button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
        <div id="rasporka"></div>
    </div>
    <t:footer/>
    </body>
    </html>
</fmt:bundle>