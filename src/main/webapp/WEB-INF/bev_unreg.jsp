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
            <h1><span><fmt:message key="default.beverages"/></span></h1>
            <hr>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="../static/img/bevs/coca.png">
                    <div class="caption">
                        <h3>Coca-Cola</h3>
                        <p>
                            <select name="size" class="select"
                                    onchange="document.getElementById('price1').value=this.value">
                                <option value="${price}">0.5</option>
                                <option value="${price}">1.0</option>
                                <option value="${price}">2.0</option>
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
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="../static/img/bevs/fanta.png">
                    <div class="caption">
                        <h3>Fanta</h3>
                        <p>
                            <select name="size" class="select"
                                    onchange="document.getElementById('price2').value=this.value">
                                <option value="${price}">0.5</option>
                                <option value="${price}">1.0</option>
                                <option value="${price}">2.0</option>
                            </select>
                        </p>
                        <t:count_group/>
                        <p>
                            <input type="text" value="${price}" readonly="readonly" class="input_select"
                                   id="price2"/>
                            <span class="span"><fmt:message key="default.currency"/></span>
                            <br>
                            <input class="btn btn-primary" type="button"
                                   value="<fmt:message key="default.addtolist"/>">
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="../static/img/bevs/mirinda.png">
                    <div class="caption">
                        <h3>Mirinda</h3>
                        <p>
                            <select name="size" class="select"
                                    onchange="document.getElementById('price3').value=this.value">
                                <option value="${price}">0.5</option>
                                <option value="${price}">1.0</option>
                                <option value="${price}">2.0</option>
                            </select>
                        </p>
                        <t:count_group/>
                        <p>
                            <input type="text" value="${price}" readonly="readonly" class="input_select"
                                   id="price3"/>
                            <span class="span"><fmt:message key="default.currency"/></span>
                            <br>
                            <input class="btn btn-primary" type="button"
                                   value="<fmt:message key="default.addtolist"/>">
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="../static/img/bevs/pepsi.png">
                    <div class="caption">
                        <h3>Pepsi</h3>
                        <p>
                            <select name="size" class="select"
                                    onchange="document.getElementById('price4').value=this.value">
                                <option value="${price}">0.5</option>
                                <option value="${price}">1.0</option>
                                <option value="${price}">2.0</option>
                            </select>
                        </p>
                        <t:count_group/>
                        <p>
                            <input type="text" value="${price}" readonly="readonly" class="input_select"
                                   id="price4"/>
                            <span class="span"><fmt:message key="default.currency"/></span>
                            <br>
                            <input class="btn btn-primary" type="button"
                                   value="<fmt:message key="default.addtolist"/>">
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="../static/img/bevs/seven_up.png">
                    <div class="caption">
                        <h3>7UP</h3>
                        <p>
                            <select name="size" class="select"
                                    onchange="document.getElementById('price5').value=this.value">
                                <option value="${price}">0.5</option>
                                <option value="${price}">1.0</option>
                                <option value="${price}">2.0</option>
                            </select>
                        </p>
                        <t:count_group/>
                        <p>
                            <input type="text" value="${price}" readonly="readonly" class="input_select"
                                   id="price5"/>
                            <span class="span"><fmt:message key="default.currency"/></span>
                            <br>
                            <input class="btn btn-primary" type="button"
                                   value="<fmt:message key="default.addtolist"/>">
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div id="order_bev" class="section">
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
