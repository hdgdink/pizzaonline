<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div  align="center">
    <h2>
        <font color="red"> ERROR ${pageContext.errorData.statusCode}</font>
    </h2>

    <img src="/static/img/error.png"/>
    <br>
    <a href="pizza"><fmt:message key="default.mainpage"/></a>

</div>
</body>
</html>
