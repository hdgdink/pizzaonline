<%@tag description="header_with_login_bar" pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setBundle basename="messages" var="msg" scope="session"/>

<div id="header" class="section">
  <p><img src="/resources/img/turtlelogo.png"></p>

  <form:form id="login" method="POST" action="login">
    <input type="text" name="username" id="user_name"/>
    <input type="password" name="password"  id="password"/>

    <button class="button" id="login_pop" type="submit">
      <fmt:message key="default.login"/>
    </button>

    <t:error-info error="${error}"/>
  </form:form>

  <a href="#join_form" title="Registration" id="join_pop" class="button">
    <fmt:message key="default.signup"/>
  </a>

  <t:theme/>
  <t:locale/>
</div>

<a href="#x" class="overlay" id="join_form"></a>

<div class="popup">
  <h2><fmt:message key="default.signup"/></h2>
  <p><fmt:message key="default.caption"/></p>

  <form:form id="registration-form" method="POST" action="register" >
    <div>
      <label for="login">
        <fmt:message key="default.username"/>
      </label>

      <input type="text" id="login" name="username" required="true" autofocus="true"/>

    </div>

    <div>
      <label for="email">
        <fmt:message key="default.email"/>
      </label>

      <input id="email" type="email" name="email" required="true"/>
      <form:errors path="email"/>
    </div>

    <div>
      <label for="pass">
        <fmt:message key="default.password"/>
      </label>

      <input type="password" id="pass" name="password"  onkeyup="checkForm(this)" required="true"/>
      <form:errors path="password"/>
    </div>

    <div>
      <label for="pass">
        <fmt:message key="default.re-password"/>
      </label>

      <input type="password" id="re-pass" name="re-password"  onkeyup="checkForm(this)" required="true"/>
    </div>

    <div>
      <label for="firstname">
        <fmt:message key="default.firstname"/>
      </label>

      <input type="text" id="firstname" name="firstname" required="true"/>
    </div>

    <div>
      <label for="lastname">
        <fmt:message key="default.lastname"/>
      </label>

      <input type="text" id="lastname" name="lastname" required="true"/>
    </div>

    <t:error-info error="${error}"/>
    <button type="submit" class="btn btn-primary">
      <fmt:message key="default.signup"/>
    </button>
  </form:form>

  <a class="close" href="#close"></a>
</div>