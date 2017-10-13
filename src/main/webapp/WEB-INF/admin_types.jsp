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
                <h2><fmt:message key="default.editType"/></h2>
                <div class="msg-error">${type_change_error}</div>
                <table class="table">
                    <tr>
                        <td><fmt:message key="default.ID"/></td>
                        <td><fmt:message key="default.name"/></td>
                        <td><fmt:message key="default.active"/></td>
                    </tr>
                    <c:forEach items="${typeList}" var="type">
                        <form id="edit_types" method="POST" action="edit_types">
                            <tr>
                                <input type="hidden" value="${type.id}" name="id"/>
                                <td>${type.id}</td>
                                <td>
                                    <input type="text" class="form-control" name="type" value="${type.type}"
                                           required/>
                                </td>
                                <td>
                                    <select class="selectpicker show-menu-arrow" data-width="auto" name="active">
                                        <option value="true" <c:if test="${type.active==true}"> selected</c:if>>
                                            <fmt:message key="default.active"/>
                                        </option>
                                        <option value="false"<c:if test="${type.active==false}"> selected</c:if>>
                                            <fmt:message key="default.notActive"/>
                                        </option>
                                    </select>
                                </td>
                                <td>
                                    <button type="submit" name="update" value="${type.id}" class="btn btn-primary">
                                        <fmt:message key="default.update"/></button>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </table>

                <hr>

                <h3><fmt:message key="default.createType"/></h3>
                <form id="create_type" method="POST" action="create_type">
                    <div class="input-group">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.name"/></span>
                                <input type="text" class="form-control" id="value_u" name="type"
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