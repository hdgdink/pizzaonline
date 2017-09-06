<%@tag description="header" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div id="header" class="section">
    <p><img src="../static/img/turtlelogo.png"></p>
    <div id="welcome">
        <h3>Happy to see you</h3>
    </div>
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
