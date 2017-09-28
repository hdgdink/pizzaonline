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
        <c:forEach items="${foodList}" var="food">
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src=${food.img}>
                    <div class="caption">
                        <h3>${food.nameRu}</h3>
                        <p>${food.discriptionRu}</p>

                        <p>
                            <select name="size" class="select"
                                    onchange="document.getElementById('price1').value=this.value">
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
<!---

            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="../static/img/pizza/hawaiipizza.png">
                    <div class="caption">
                        <h3><fmt:message key="default.hawaiian"/></h3>
                        <p><fmt:message key="hawaiian.consist"/></p>
                        <br>
                        <p>
                            <select name="size" class="select"
                                    onchange="document.getElementById('price1').value=this.value">
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
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="../static/img/pizza/godfatherpizza.png">
                    <div class="caption">
                        <h3><fmt:message key="default.godfather"/></h3>
                        <p><fmt:message key="godfather.consist"/></p>
                        <br>
                        <p>
                            <select name="size" class="select"
                                    onchange="document.getElementById('price2').value=this.value">
                                <option value="${price}"><fmt:message key="default.small"/></option>
                                <option value="${price}"><fmt:message key="default.medium"/></option>
                                <option value="${price}"><fmt:message key="default.big"/></option>
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
                    <img src="../static/img/pizza/cubanpizza.png">
                    <div class="caption">
                        <h3><fmt:message key="default.cuban"/></h3>
                        <p><fmt:message key="cuban.consist"/></p>
                        <br>
                        <p>
                            <select name="size" class="select"
                                    onchange="document.getElementById('price3').value=this.value">
                                <option value="${price}"><fmt:message key="default.small"/></option>
                                <option value="${price}"><fmt:message key="default.medium"/></option>
                                <option value="${price}"><fmt:message key="default.big"/></option>
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
                    <img src="../static/img/pizza/monalizapizza.png">
                    <div class="caption">
                        <h3><fmt:message key="default.monaliza"/></h3>
                        <p><fmt:message key="monaliza.consist"/></p>
                        <br>
                        <p>
                            <select name="size" class="select"
                                    onchange="document.getElementById('price4').value=this.value">
                                <option value="${price}"><fmt:message key="default.small"/></option>
                                <option value="${price}"><fmt:message key="default.medium"/></option>
                                <option value="${price}"><fmt:message key="default.big"/></option>
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
                    <img src="../static/img/pizza/cheasepizza.png">
                    <div class="caption">
                        <h3><fmt:message key="default.cheese"/></h3>
                        <p><fmt:message key="cheese.consist"/></p>
                        <br>
                        <p>
                            <select name="size" class="select"
                                    onchange="document.getElementById('price5').value=this.value">
                                <option value="${price}"><fmt:message key="default.small"/></option>
                                <option value="${price}"><fmt:message key="default.medium"/></option>
                                <option value="${price}"><fmt:message key="default.big"/></option>
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
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="../static/img/pizza/melonyspizza.png">
                    <div class="caption">
                        <h3><fmt:message key="default.melany"/></h3>
                        <p><fmt:message key="melany.consist"/></p>
                        <br>
                        <br>
                        <p>
                            <select name="size" class="select"
                                    onchange="document.getElementById('price6').value=this.value">
                                <option value="${price}"><fmt:message key="default.small"/></option>
                                <option value="${price}"><fmt:message key="default.medium"/></option>
                                <option value="${price}"><fmt:message key="default.big"/></option>
                            </select>
                        </p>
                        <t:count_group/>
                        <p>
                            <input type="text" value="${price}" readonly="readonly" class="input_select"
                                   id="price6"/>
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
                    <img src="../static/img/pizza/damianpizza.png">
                    <div class="caption">
                        <h3><fmt:message key="default.damian"/></h3>
                        <p><fmt:message key="damian.consist"/></p>
                        <br>
                        <br>
                        <p>
                            <select name="size" class="select"
                                    onchange="document.getElementById('price7').value=this.value">
                                <option value="${price}"><fmt:message key="default.small"/></option>
                                <option value="${price}"><fmt:message key="default.medium"/></option>
                                <option value="${price}"><fmt:message key="default.big"/></option>
                            </select>
                        </p>
                        <t:count_group/>
                        <p>
                            <input type="text" value="${price}" readonly="readonly" class="input_select"
                                   id="price7"/>
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
                    <img src="../static/img/pizza/assortipizza.png">
                    <div class="caption">
                        <h3><fmt:message key="default.assorti"/></h3>
                        <p><fmt:message key="assorti.consist"/></p>
                        <br>
                        <p>
                            <select name="size" class="select"
                                    onchange="document.getElementById('price8').value=this.value">
                                <option value="${price}"><fmt:message key="default.small"/></option>
                                <option value="${price}"><fmt:message key="default.medium"/></option>
                                <option value="${price}"><fmt:message key="default.big"/></option>
                            </select>
                        </p>
                        <t:count_group/>
                        <p>
                            <input type="text" value="${price}" readonly="readonly" class="input_select"
                                   id="price8"/>
                            <span class="span"><fmt:message key="default.currency"/></span>
                            <br>
                            <input class="btn btn-primary" type="button"
                                   value="<fmt:message key="default.addtolist"/>">
                        </p>
                    </div>
                </div>
            </div>

        ---->
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