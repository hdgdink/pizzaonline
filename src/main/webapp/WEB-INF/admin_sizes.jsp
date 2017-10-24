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
                <h2><fmt:message key="default.editSize"/></h2>
                <div class="msg-error">${size_change_error}</div>
                <table class="table">
                    <tr>
                        <td><fmt:message key="default.ID"/></td>
                        <td><fmt:message key="default.sizeValue"/></td>
                        <td><fmt:message key="default.sizeCaption"/></td>
                        <td><fmt:message key="default.active"/></td>
                    </tr>
                    <c:forEach items="${allSizesAdmin}" var="size">
                        <form id="edit_sizes" method="POST" action="edit_sizes">
                            <tr>
                                <input type="hidden" value="${size.id}" name="id"/>
                                <td>${size.id}</td>
                                <td>
                                    <input type="number" class="form-control" name="value" value="${size.size}"
                                           required min="1"/>
                                </td>
                                <td>
                                    <input type="text" class="form-control" name="name" value="${size.name}"/>
                                </td>
                                <td>
                                    <select class="selectpicker show-menu-arrow" data-width="auto" name="active">
                                        <option value="true" <c:if test="${size.active==true}"> selected</c:if>>
                                            <fmt:message key="default.active"/>
                                        </option>
                                        <option value="false"<c:if test="${size.active==false}"> selected</c:if>>
                                            <fmt:message key="default.notActive"/>
                                        </option>
                                    </select>
                                </td>
                                <td>
                                    <button type="submit" name="update" value="${size.id}" class="btn btn-primary">
                                        <fmt:message key="default.update"/></button>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </table>

                <hr>

                <h3><fmt:message key="default.createSize"/></h3>
                <form id="create_size" method="POST" action="create_size">
                    <div class="input-group">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.sizeValue"/></span>
                                <input type="number" class="form-control" id="value_u" name="value"
                                       value="" placeholder="..." min="1" required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.sizeCaption"/></span>
                                <input type="text" class="form-control" id="name_u" name="name"
                                       value="" placeholder="..." required>
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