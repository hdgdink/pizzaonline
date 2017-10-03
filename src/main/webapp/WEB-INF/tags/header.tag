<%@tag description="header" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<div id="header" class="section">
    <p><img src="../static/img/turtlelogo.png"></p>
    <div id="welcome">
        <h2><fmt:message key="default.happy"/><a href="cabinet" title="Cabinet"> ${user.firstname}</a> </h2>
    </div>
    <div id="balance">
        <h3><fmt:message key="default.balance"/>${user.balance}</h3>
    </div>
    <form action="logout" method="get">
        <button class="button" id="logout" type="submit"><fmt:message key="default.logout"/></button>
    </form>
    <t:locale/>
</div>
