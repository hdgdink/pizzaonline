<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.text">
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Welcome to pizza menu</title>
        <link rel="stylesheet" type="text/css" href="../static/css/style.css"/>
        <link rel="stylesheet" type="text/css" href="../static/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="../static/css/modal.css"/>
    </head>
    <body>

    <div id="main">
        <div id="header" class="section">
            <p><img src="../static/img/turtlelogo.png"></p>
            <input type="text" value="<fmt:message key="default.username"/>" id="user_name"/>
            <input type="password" value="<fmt:message key="default.password"/>" id="password" c/>
            <a href="#" title="Enter" id="login_pop" class="button"> <fmt:message key="default.login"/> </a>
            <a href="#join_form" title="Registration" id="join_pop" class="button"> <fmt:message
                    key="default.signup"/></a>
        </div>

        <!-- popup registration -->
        <a href="#x" class="overlay" id="join_form"></a>
        <div class="popup">
            <h2><fmt:message key="default.signup"/></h2>
            <p><fmt:message key="default.caption"/></p>
            <div>
                <label for="login"><fmt:message key="default.username"/></label>
                <input type="text" id="login" value=""/>
            </div>
            <div>
                <label for="pass"><fmt:message key="default.password"/></label>
                <input type="password" id="pass" value=""/>
            </div>
            <div>
                <label for="firstname"><fmt:message key="default.firstname"/></label>
                <input type="text" id="firstname" value=""/>
            </div>
            <div>
                <label for="lastname"><fmt:message key="default.lastname"/></label>
                <input type="text" id="lastname" value=""/>
            </div>
            <input type="button" value="<fmt:message key="default.signup"/>" class="button"/>
            <a class="close" href="#close"></a>
        </div>

        <nav role='navigation'>
            <ul>
                <li><a href="#"><fmt:message key="default.pizza"/></a></li>
                <li><a href="#"><fmt:message key="default.salads"/></a></li>
                <li><a href="#"><fmt:message key="default.subs"/></a></li>
                <li><a href="#"><fmt:message key="default.beverages"/></a></li>
            </ul>
        </nav>

        <div id="menu" class="section">
            <h1><span><fmt:message key="default.pizzamenu"/></span></h1>
            <hr>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="../static/img/hawaiipizza.png">
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
                        </p>
                        <p>
                            <input type="text" value="${price}" readonly="readonly" class="input_select" id="price1"/>
                            <span class="span"><fmt:message key="default.currency"/></span>
                            <br>
                            <input class="btn btn-primary" type="button" value="<fmt:message key="default.addtolist"/>">
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="../static/img/godfatherpizza.png">
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
                        <p>
                            <input type="text" value="${price}" readonly="readonly" class="input_select" id="price2"/>
                            <span class="span"><fmt:message key="default.currency"/></span>
                            <br>
                            <input class="btn btn-primary" type="button" value="<fmt:message key="default.addtolist"/>">
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="../static/img/cubanpizza.png">
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
                        <p>
                            <input type="text" value="${price}" readonly="readonly" class="input_select" id="price3"/>
                            <span class="span"><fmt:message key="default.currency"/></span>
                            <br>
                            <input class="btn btn-primary" type="button" value="<fmt:message key="default.addtolist"/>">
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="../static/img/monalizapizza.png">
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
                        <p>
                            <input type="text" value="${price}" readonly="readonly" class="input_select" id="price4"/>
                            <span class="span"><fmt:message key="default.currency"/></span>
                            <br>
                            <input class="btn btn-primary" type="button" value="<fmt:message key="default.addtolist"/>">
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="../static/img/cheasepizza.png">
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
                        <p>
                            <input type="text" value="${price}" readonly="readonly" class="input_select" id="price5"/>
                            <span class="span"><fmt:message key="default.currency"/></span>
                            <br>
                            <input class="btn btn-primary" type="button" value="<fmt:message key="default.addtolist"/>">
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="../static/img/melonyspizza.png">
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
                        <p>
                            <input type="text" value="${price}" readonly="readonly" class="input_select" id="price6"/>
                            <span class="span"><fmt:message key="default.currency"/></span>
                            <br>
                            <input class="btn btn-primary" type="button" value="<fmt:message key="default.addtolist"/>">
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="../static/img/damianpizza.png">
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
                        <p>
                            <input type="text" value="${price}" readonly="readonly" class="input_select" id="price7"/>
                            <span class="span"><fmt:message key="default.currency"/></span>
                            <br>
                            <input class="btn btn-primary" type="button" value="<fmt:message key="default.addtolist"/>">
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="../static/img/assortipizza.png">
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
                        <p>
                            <input type="text" value="${price}" readonly="readonly" class="input_select" id="price8"/>
                            <span class="span"><fmt:message key="default.currency"/></span>
                            <br>
                            <input class="btn btn-primary" type="button" value="<fmt:message key="default.addtolist"/>">
                        </p>
                    </div>
                </div>
            </div>
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
    <div id="footer">
        <p>
            &copy; 2017 JavaLab#22 <br> PIZZA ONLINE <br> Vassilyev Andrey.
        </p>
    </div>
    </body>
    </html>
</fmt:bundle>