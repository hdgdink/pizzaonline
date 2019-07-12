<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.text">
  <html>
    <head>
      <title>Error</title>
    </head>

    <body>
      <div align="center">
        <p align="center"><img src="/static/img/error.png"/></p>
        <br>

        <h2>
          <font color="red"> ERROR ${pageContext.errorData.statusCode}</font>
          <c:remove var="error"/>
        </h2>

        <br>
        <a href='<c:url value="/"/>'><fmt:message key="default.mainpage"/> </a>
      </div>
    </body>
  </html>
</fmt:bundle>