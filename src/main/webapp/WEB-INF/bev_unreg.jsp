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
        <t:header_log_reg/>

        <t:navigation/>

        <div id="menu" class="section">
            <h1><span><fmt:message key="default.beverages"/></span></h1>
            <hr>
            <c:forEach items="${bevList}" var="bev">
                <div class="col-sm-6 col-md-4">
                    <div class="thumbnail">
                        <img src=${bev.img}>
                        <input type="hidden" name="food" value="${bev.id}"/>
                        <div class="caption">
                            <c:if test="${locale.language=='ru'}"><h3>${bev.nameRu}</h3></c:if>
                            <c:if test="${locale.language=='en'}"><h3>${bev.nameEn}</h3></c:if>
                            <c:if test="${locale.language=='ru'}"><p>${bev.discriptionRu}</p></h3></c:if>
                            <c:if test="${locale.language=='en'}"><p>${bev.discriptionEn}</p></h3></c:if>
                            <p>
                                <select name="size" class="select">
                                    <c:forEach items="${sizeList}" var="size">
                                        <option value="${size.size}"><fmt:message key="${size.name}"/></option>
                                    </c:forEach>
                                </select>
                            <p/>
                            <t:count_group/>
                            <p>
                                <input type="text" value="${price}" readonly="readonly" class="input_select"
                                       id="price1"/>
                                <span class="span"><fmt:message key="default.currency"/></span>
                                <br>
                                <a href="#message_form" title="Add to order" class="add btn btn-primary">
                                    <fmt:message key="default.addtolist"/></a>
                            </p>
                            <a href="#x" class="overlay" id="message_form"></a>
                            <div class="popup">
                                <h2><fmt:message key="default.loginNeed"/></h2>
                                <a class="close" href="#close"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <t:order_list/>

        <div id="rasporka"></div>
    </div>
    <t:footer/>
    </body>
    </html>
</fmt:bundle>
