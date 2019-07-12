<%@tag description="theme-cnanger" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="theme_changer" class="dropdown">
  <button class="btn btn-default dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">
    Change Theme
  </button>

  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
    <li role="option">
      <a class="dropdown-item" href="?theme=bootstrap">
        standart
      </a>
    </li>

    <li role="option">
      <a class="dropdown-item" href="?theme=bootstrap-theme">
        non-standart
      </a>
    </li>
  </ul>
</div>
