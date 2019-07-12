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

            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <form id="about-form" method="POST" action="info_update">
                        <div>
                            <label for="username"><fmt:message key="default.username"/></label>
                            <input type="text" id="username" name="username" value="${user.username}" readonly/>
                        </div>
                        <div>
                            <label for="email"><fmt:message key="default.email"/></label>
                            <input type="email" id="email" name="email" value="${user.email}" required autofocus/>
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
                </div>
            </div>

            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <form id="pass_update" method="POST" action="pass_update">
                        <div>
                            <label for="old_pass"><fmt:message key="default.oldPassword"/></label>
                            <input type="password" id="old_pass" name="oldPassword" required/>
                        </div>
                        <div>
                            <label for="new_pass1"><fmt:message key="default.newPassword"/></label>
                            <input type="password" id="new_pass1" name="newPassword1" required/>
                        </div>
                        <div>
                            <label for="new_pass2"><fmt:message key="default.newPassword"/></label>
                            <input type="password" id="new_pass2" name="newPassword2" required/>
                        </div>
                        <t:error-info error="${error}"/>
                        <t:info info="${changePassSuccess}"/>
                        <button type="submit" class="btn btn-primary"><fmt:message key="default.update"/></button>
                    </form>
                </div>
            </div>

            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <div>
                        <label for="balance"><fmt:message key="default.balance"/></label>
                        <input type="text" id="balance" name="old_password" value="${user.balance}" readonly/>
                    </div>
                    <a href="#reup_form" title="Re-up balance" class="btn btn-primary">
                        <fmt:message key="default.reup"/></a>
                    <a href="#x" class="overlay" id="reup_form"></a>
                    <div class="popup">
                        <h2><fmt:message key="default.underConstruction"/></h2>
                        <a class="close" href="#close"></a>
                    </div>
                </div>
            </div>

        <div id="rasporka"></div>
    </div>
    <t:footer/>
    </body>
    </html>
</fmt:bundle>