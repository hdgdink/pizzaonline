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
                <h2><fmt:message key="default.editUser"/></h2>
                <div class="msg-error">${user_change_error}</div>
                <table class="table">
                    <tr>
                        <td><fmt:message key="default.ID"/></td>
                        <td><fmt:message key="default.firstname"/></td>
                        <td><fmt:message key="default.lastname"/></td>
                        <td><fmt:message key="default.username"/></td>
                        <td><fmt:message key="default.email"/></td>
                        <td><fmt:message key="default.password"/></td>
                        <td><fmt:message key="default.role"/></td>
                        <td><fmt:message key="default.balanceAdmin"/></td>
                    </tr>
                    <c:forEach items="${user_list}" var="users">
                        <form id="edit_user" method="POST" action="edit_user">
                            <tr>
                                <input type="hidden" value="${users.id}" name="id"/>
                                <td>${users.id}</td>
                                <td><input type="text" class="form-control" name="firstname" value="${users.firstname}"
                                           required
                                           autofocus/></td>
                                <td><input type="text" class="form-control" name="lastname" value="${users.lastname}"
                                           required/></td>
                                <td><input type="text" class="form-control" name="username" value="${users.username}"
                                           required/></td>
                                <td><input type="email" class="form-control" name="email" value="${users.email}"
                                           required/></td>
                                <td><input type="text" class="form-control" name="password" value="${users.password}"
                                           required/></td>
                                <td>
                                    <select class="selectpicker show-menu-arrow" data-width="auto" name="role">
                                        <c:forEach items="${roles}" var="role">
                                            <option value="${role}"
                                                    <c:if test="${users.role==role}">selected</c:if>>${role}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td><input type="number" class="form-control" name="balance"
                                           <c:if test="${users.role!='CLIENT'}">readonly="" </c:if>
                                           value="${users.balance}">
                                </td>
                                <td>
                                    <button type="submit" name="update" value="${user.id}" class="btn btn-primary">
                                        <fmt:message
                                                key="default.update"/></button>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </table>

                <hr>

                <h3><fmt:message key="default.creatUser"/></h3>
                <form id="create_user" method="POST" action="create_user">
                    <div class="input-group">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.firstname"/></span>
                                <input type="text" class="form-control" id="firstname_u" name="firstname"
                                       value="" placeholder="..." required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.lastname"/></span>
                                <input type="text" class="form-control" id="lastname_u" name="lastname"
                                       value="" placeholder="..." required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.username"/></span>
                                <input type="text" class="form-control" id="username_u" name="username"
                                       value="" placeholder="..." required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.password"/></span>
                                <input type="password" class="form-control" name="password" required value="">
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.email"/></span>
                                <input type="email" class="form-control" id="email_u" name="email" value=""
                                       placeholder="..." required>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.role"/></span>
                                <select class="selectpicker show-menu-arrow" data-width="auto" name="role">
                                    <c:forEach items="${roles}" var="role1">
                                        <option value="${role1}" <c:if test="${role1==role}">selected</c:if>>
                                                ${role1}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="default.balanceAdmin"/></span>
                                <input type="number" class="form-control" name="balance"
                                       value="" placeholder="...">
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