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
            <h1><span><fmt:message key="default.subs"/></span></h1>
            <hr>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="../static/img/subs/turkey_breast.png">
                    <div class="caption">
                        <h3>Turkey Breast</h3>
                        <p>Dive into tender turkey breast piled sky-high with everything from lettuce and tomatoes to
                            crispy cucumbers.</p>
                        <br><br>
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
                    <img src="../static/img/subs/cold_cut_combo.png">
                    <div class="caption">
                        <h3>Cold Cut Combo</h3>
                        <p>The Cold Cut Combo is stacked with turkey-based meats - ham, salami and bologna. It's topped
                            with crisp vegetables and served on freshly baked bread. This combo has a little bit of
                            everything.</p>
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
                    <img src="../static/img/subs/italian_bmt.png">
                    <div class="caption">
                        <h3>Italian B.M.T.</h3>
                        <p>This all-time Italian classic is filled with Genoa salami, spicy pepperoni, and Black Forest
                            Ham. Get it made the way you say with your favorite veggies on freshly baked bread.
                        </p>
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
                    <img src="../static/img/subs/meatball_marinara.png">
                    <div class="caption">
                        <h3>Meatball Marinara</h3>
                        <p> Enjoy Italian style meatballs drenched in irresistible marinara sauce, served on freshly
                            baked bread. </p>
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
                    <img src="../static/img/subs/oven_roasten.png">
                    <div class="caption">
                        <h3>Oven Roasted Chicken</h3>
                        <p>The Oven Roasted Chicken you love is piled high atop freshly baked bread with your favorite
                            toppings like crispy green peppers and cucumbers.</p>
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
                    <img src="../static/img/subs/roast_beef.png">
                    <div class="caption">
                        <h3>Roast Beef</h3>
                        <p>This tasty number, with less than 6g of fat, is piled high with lean roast beef and your
                            choice of fresh veggies, like crisp green peppers and juicy tomatoes.</p>
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
                    <img src="../static/img/subs/rotisserie_chiken.png">
                    <div class="caption">
                        <h3>Rotisserie-Style Chicken</h3>
                        <p>Our Rotisserie-Style Chicken Sandwich is made with tender, hand-pulled all-white meat chicken
                            with seasoning and marinade, raised without antibiotics. Try it with crisp veggies on
                            freshly baked bread.</p>
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
                    <img src="../static/img/subs/spicy_italian.png">
                    <div class="caption">
                        <h3>Spicy Italian</h3>
                        <p>A blend of pepperoni and salami, topped with cheese – try it with banana peppers, or your
                            choice of crisp veggies and condiments served hot on freshly baked bread.</p>
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
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="../static/img/subs/steak_cheese.png">
                    <div class="caption">
                        <h3>Steak & Cheese</h3>
                        <p>Two of the most irresistible ingredients in the world — piled high onto freshly baked bread
                            and your choice of crisp veggies.</p>
                        <br><br>
                        <p>
                            <select name="size" class="select"
                                    onchange="document.getElementById('price9').value=this.value">
                                <option value="${price}"><fmt:message key="default.small"/></option>
                                <option value="${price}"><fmt:message key="default.medium"/></option>
                                <option value="${price}"><fmt:message key="default.big"/></option>
                            </select>
                        </p>
                        <t:count_group/>
                        <p>
                            <input type="text" value="${price}" readonly="readonly" class="input_select"
                                   id="price9"/>
                            <span class="span"><fmt:message key="default.currency"/></span>
                            <br>
                            <input class="btn btn-primary" type="button"
                                   value="<fmt:message key="default.addtolist"/>">
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div id="order_subs" class="section">
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
