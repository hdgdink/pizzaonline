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
                <h2><fmt:message key="default.editfood"/></h2>
                <div class="msg-error">${food_change_error}</div>
                <table class="table">
                    <tr>
                        <td><fmt:message key="default.ID"/></td>
                        <td><fmt:message key="default.nameRu"/></td>
                        <td><fmt:message key="default.nameEn"/></td>
                        <td><fmt:message key="default.composRu"/></td>
                        <td><fmt:message key="default.composEn"/></td>
                        <td><fmt:message key="default.price"/></td>
                        <td><fmt:message key="default.typeId"/></td>
                        <td><fmt:message key="default.imgPath"/></td>
                        <td><fmt:message key="default.active"/></td>
                    </tr>
                    <c:forEach items="${products_list}" var="product">
                        <form id="edit_product" method="POST" action="edit_product">
                            <tr>
                                <input type="hidden" value="${product.id}" name="id"/>
                                <td>${product.id}</td>
                                <td>
                                    <input type="text" class="form-control" name="nameRu" value="${product.nameRu}"
                                           readonly="readonly"/>
                                </td>
                                <td>
                                    <input type="text" class="form-control" name="nameEn" value="${product.nameEn}"
                                           readonly="readonly"/>
                                </td>
                                <td>
                                    <input type="text" class="form-control" name="composRu"
                                           value="${product.discriptionRu}"
                                           required/>
                                </td>
                                <td>
                                    <input type="text" class="form-control" name="composEn"
                                           value="${product.discriptionEn}"
                                           required/>
                                </td>
                                <td>
                                    <input type="text" class="form-control" name="price" value="${product.price}"
                                           required min="1"/>
                                </td>
                                <td>
                                    <select class="selectpicker show-menu-arrow" data-width="auto" name="typeId">
                                        <c:forEach items="${typeList}" var="type">
                                            <option
                                                    <c:if test="${product.typeId==type.id}">selected</c:if>
                                                    value="${type.id}">
                                                <fmt:message key="${type.type}"/>
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input type="text" class="form-control" name="imgPath" value="${product.img}"
                                           required/>
                                </td>
                                <td>
                                    <select class="selectpicker show-menu-arrow" data-width="auto" name="active">
                                        <option value="true" <c:if test="${product.active==true}"> selected</c:if>>
                                            <fmt:message key="default.active"/>
                                        </option>
                                        <option value="false"<c:if test="${product.active==false}"> selected</c:if>>
                                            <fmt:message key="default.notActive"/>
                                        </option>
                                    </select>
                                </td>
                                <td>
                                    <button type="submit" name="update" value="${product.id}" class="btn btn-primary">
                                        <fmt:message key="default.update"/></button>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </table>

                <hr>

                <h3><fmt:message key="default.createProduct"/></h3>
                <form id="create_product" method="POST" action="create_product">
                    <div class="input-group">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.nameRu"/></span>
                                <input type="text" class="form-control" id="nameRu_u" name="nameRu"
                                       value="" placeholder="..." required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.nameEn"/></span>
                                <input type="text" class="form-control" id="nameEn_u" name="nameEn"
                                       value="" placeholder="..." required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.composRu"/></span>
                                <input type="text" class="form-control" id="composRu_u" name="composRu"
                                       value="" placeholder="..." required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.composEn"/></span>
                                <input type="text" class="form-control" name="composEn" required value="">
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.price"/></span>
                                <input type="number" class="form-control" id="price_u" name="price" value=""
                                       placeholder="..." min="1" required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.typeId"/></span>
                                <select class="selectpicker show-menu-arrow" data-width="auto" name="typeId">
                                    <c:forEach items="${typeList}" var="type">
                                        <option value="${type.id}">
                                            <fmt:message key="${type.type}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.imgPath"/></span>
                                <input type="text" class="form-control" name="imgPath"
                                       value="" placeholder="...">
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.active"/></span>
                                <select class="selectpicker show-menu-arrow" data-width="auto" name="active">
                                    <option value="true">
                                        <fmt:message key="default.active"/>
                                    </option>
                                    <option value="false">
                                        <fmt:message key="default.notActive"/>
                                    </option>
                                </select>
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