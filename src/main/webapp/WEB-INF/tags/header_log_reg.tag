<%@tag description="header_with_login_bar" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div id="header" class="section">
    <p><img src="../static/img/turtlelogo.png"></p>
<form method="POST" action="login">
    <input type="text" value="<fmt:message key="default.username"/>" name="username" id="user_name"/>
    <input type="password" value="<fmt:message key="default.password"/>" name="password" id="password" c/>
   <!--- <a href="#" title="Enter" id="login_pop" class="button"> <fmt:message key="default.login"/> </a>-->
    <button class="button" id="login_pop" type="submit"><fmt:message key="default.login"/></button>
</form>

    <a href="#join_form" title="Registration" id="join_pop" class="button"> <fmt:message
            key="default.signup"/></a>

    <div id="locale_changer" class="dropdown">
        <button class="btn btn-default dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">
            <c:if test="${locale.language=='ru'}"><img src='<c:url value="/static/img/RU.png"/>'/></c:if>
            <c:if test="${locale.language=='en'}"><img src='<c:url value="/static/img/EN.png"/>'/></c:if>
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="option"><a role="menuitem" tabindex="-1" href="locale?locale=ru"><img
                    src='<c:url value="/static/img/RU.png"/>'/> Русский</a></li>
            <li role="option"><a role="menuitem" tabindex="-1" href="locale?locale=en"><img
                    src='<c:url value="/static/img/EN.png"/>'/> English</a></li>
        </ul>
    </div>

</div>
<a href="#x" class="overlay" id="join_form"></a>
<div class="popup">
    <h2><fmt:message key="default.signup"/></h2>
    <p><fmt:message key="default.caption"/></p>

    <form id="registration-form" method="post" action="register">
        <div>
            <label for="login"><fmt:message key="default.username"/></label>
            <input type="text" id="login" name="username" required autofocus/>
        </div>
        <div>
            <label for="email"><fmt:message key="default.email"/></label>
            <input type="email" id="email" name="email" required/>
        </div>
        <div>
            <label for="pass"><fmt:message key="default.password"/></label>
            <input type="password" id="pass" name="password" required onkeyup="checkForm(this)"/>
        </div>
        <div>
            <label for="pass"><fmt:message key="default.re-password"/></label>
            <input type="password" id="re-pass" name="re-password" required onkeyup="checkForm(this)"/>
        </div>
        <div>
            <label for="firstname"><fmt:message key="default.firstname"/></label>
            <input type="text" id="firstname" name="firstname" required/>
        </div>
        <div>
            <label for="lastname"><fmt:message key="default.lastname"/></label>
            <input type="text" id="lastname" name="lastname" required/>
        </div>

        <div id="msg">
            <fmt:message key="message.error.matchpass"/>
        </div>
        <button type="submit" class="btn btn-primary"><fmt:message key="default.signup"/></button>
    </form>


    <a class="close" href="#close"></a>
</div>