<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.text">
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title><fmt:message key="default.welcome"/>${username}</title>
        <link rel="stylesheet" type="text/css" href="../static/css/style.css"/>
        <link rel="stylesheet" type="text/css" href="../static/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="../static/css/modal.css"/>
        <script type='text/javascript' src='<c:url value="/static/js/bootstrap-select.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/webjars/jquery/1.11.1/jquery.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/static/js/count_button.js"/>'></script>
    </head>
    <body>

    <div id="main">
        <t:header/>
        <t:navigation_user/>
        <div id="menu" class="section">
            <h1><span><fmt:message key="default.subs"/></span></h1>
            <hr>
            <c:forEach items="${subList}" var="sub">
                <form action="add_to_orderlist" method="get">
                    <div class="col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img src=${sub.img}>
                            <div class="caption">
                                <c:if test="${locale.language=='ru'}"><h3>${sub.nameRu}</h3></c:if>
                                <c:if test="${locale.language=='en'}"><h3>${sub.nameEn}</h3></c:if>
                                <c:if test="${locale.language=='ru'}"><p>${sub.discriptionRu}</p></h3></c:if>
                                <c:if test="${locale.language=='en'}"><p>${sub.discriptionEn}</p></h3></c:if>
                                <input type="hidden" name="food" value="${sub.id}"/>
                                <p>
                                    <select name="size" class="select"
                                            onchange="document.getElementById('${sub.id}').value=this.value*${sub.price};">
                                        <c:forEach items="${sizeList}" var="size" begin="3" end="5">
                                            <c:set var="size_value" value="${sizeList[0].size}"/>
                                            <option value="${size.size}"><fmt:message key="${size.name}"/></option>
                                        </c:forEach>
                                    </select>
                                <p/>
                                <t:count_group/>
                                <p>
                                    <input type="text" value="${size_value*sub.price}" readonly="readonly"
                                           class="input_select" id="${sub.id}"/>
                                    <span class="span"><fmt:message key="default.currency"/></span>
                                    <br>
                                    <input type="hidden" name="food" value="${sub.id}"/>
                                    <t:error-info error="${error}"/>
                                    <input class="add btn btn-primary" type="submit"
                                           value="<fmt:message key="default.addtolist"/>">
                                </p>
                            </div>
                        </div>
                    </div>
                </form>
            </c:forEach>
        </div>
        <t:order_list/>
        <div id="rasporka"></div>
    </div>
    <t:footer/>
    </body>
    </html>
</fmt:bundle>