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

        <div id="cabinet" class="section">


            <form id="about-form" method="post" action="info_update">
                <div>
                    <label for="username"><fmt:message key="default.username"/></label>
                    <input type="text" id="username" name="username" value="${user.username}" required autofocus/>
                </div>
                <div>
                    <label for="email"><fmt:message key="default.email"/></label>
                    <input type="email" id="email" name="email" value="${user.email}" required/>
                </div>
                                <div>
                    <label for="firstname"><fmt:message key="default.firstname"/></label>
                    <input type="text" id="firstname" name="firstname" value="${user.firstname}" required/>
                </div>
                <div>
                    <label for="lastname"><fmt:message key="default.lastname"/></label>
                    <input type="text" id="lastname" name="lastname" value="${user.lastname}" required/>
                </div>

                <button type="submit" class="btn btn-primary"><fmt:message key="default.update"/></button>
            </form>

            <form id="pass_update" method="post" action="pass_update">
                <div>
                    <label for="old_pass"><fmt:message key="default.oldPassword"/></label>
                    <input type="password" id="old_pass" name="old_password"  required />
                </div>
                <div>
                    <label for="new_pass"><fmt:message key="default.newPassword"/></label>
                    <input type="password" id="new_pass" name="re-password" required />
                </div>

                <button type="submit" class="btn btn-primary"><fmt:message key="default.update"/></button>
            </form>

            <form id="balance_update" method="post" action="balance_update">
                <div>
                   <fmt:message key="default.balance"/><p>${user.balance}</p>
                    <input type="password" id="old_pass" name="old_password"  required />
                </div>


                <button type="submit" class="btn btn-primary"><fmt:message key="default.update"/></button>
            </form>

        </div>

        <div id="rasporka"></div>
    </div>
    <t:footer/>
    </body>
    </html>
</fmt:bundle>