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
    </head>
    <body>


    <div id="main">

        <t:header_log_reg/>

        <t:navigation/>

        <div id="menu" class="section">
            <h1><span><fmt:message key="default.pizzamenu"/></span></h1>
            <hr>
            <c:forEach items="${pizzaList}" var="pizza">
                <div class="col-sm-6 col-md-4">
                    <div class="thumbnail">
                        <img src=${pizza.img}>
                        <div class="caption">
                            <c:if test="${locale.language=='ru'}"><h3>${pizza.nameRu}</h3></c:if>
                            <c:if test="${locale.language=='en'}"><h3>${pizza.nameEn}</h3></c:if>
                            <c:if test="${locale.language=='ru'}"><p>${pizza.discriptionRu}</p></h3></c:if>
                            <c:if test="${locale.language=='en'}"><p>${pizza.discriptionEn}</p></h3></c:if>
                            <p>
                                <select name="size" class="select">
                                    <option value="${price}"><fmt:message key="default.small"/></option>
                                    <option value="${price}"><fmt:message key="default.medium"/></option>
                                    <option value="${price}"><fmt:message key="default.big"/></option>
                                </select>
                            <p/>
                            <t:count_group/>
                            <p>
                                <input type="text" value="${price}" readonly="readonly" class="input_select"
                                       id="price1"/>
                                <span class="span"><fmt:message key="default.currency"/></span>
                                <br>
                                <input class="btn btn-primary" type="button"
                                       value="<fmt:message key="default.addtolist"/>">
                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div id="order" class="section">
            <h1><fmt:message key="default.order"/></h1>
            <hr>
            <table id="myTable">
                <tr>
                    <td><fmt:message key="default.name"/></td>
                    <td></td>
                    <td>. . . . . . . . . . . . . . . . . . . . . . . . . .</td>
                    <td></td>
                    <td><fmt:message key="default.price"/></td>
                </tr>

            </table>
            <hr>
            <input class="btn btn-primary" type="submit" value="<fmt:message key="default.checkout"/>"/>
        </div>

        <div id="rasporka"></div>
    </div>

    <t:footer/>

    </body>
    </html>
</fmt:bundle>