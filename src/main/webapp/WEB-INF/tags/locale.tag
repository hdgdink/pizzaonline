<%@tag description="local-cnanger" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="locale_changer" class="dropdown">
  <button class="btn btn-default dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">
    <c:if test="${locale.language=='ru'}">
      <img src='<c:url value="/resources/img/RU.png"/>'/>
    </c:if>

    <c:if test="${locale.language=='en'}">
      <img src='<c:url value="/resources/img/EN.png"/>'/>
    </c:if>

    <span class="caret"/>
  </button>

  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
    <li role="option">
      <a role="menuitem" tabindex="-1" href="locale?lang=ru">
        <img src='<c:url value="/resources/img/RU.png"/>'/>
        Русский
      </a>
    </li>

    <li role="option">
      <a role="menuitem" tabindex="-1" href="locale?lang=en">
        <img src='<c:url value="/resources/img/EN.png"/>'/>
        English
      </a>
    </li>
  </ul>
</div>