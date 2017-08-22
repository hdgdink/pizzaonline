<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Welcome to pizza menu</title>
    <link rel="stylesheet" type="text/css" href="../static/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="../static/css/bootstrap.css"/>
</head>
<body>

<div id="main">
    <div id="header" class="section">
        <p><img src="../static/img/turtlelogo.png"></p>
        <h3> We are happy to see you + ${firstname}</h3>
    </div>

    <nav role='navigation'>
        <ul>
            <li><a href="#">PIZZA</a></li>
            <li><a href="#">SALADS</a></li>
            <li><a href="#">SUBS</a></li>
            <li><a href="#">BEVERAGES</a></li>
        </ul>
    </nav>

    <div id="menu" class="section">
        <h1><span>Pizza Menu</span></h1>
        <hr>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="../static/img/hawaiipizza.png">
                <div class="caption">
                    <h3>Hawaiian Pizza</h3>
                    <p>Pineapple and ham.</p>
                    <br>
                    <p>
                        <select name="size" class="select"
                                onchange="document.getElementById('price1').value=this.value">
                            <option value="900">Small 8"</option>
                            <option value="1200">Medium 12"</option>
                            <option value="1500">Big 18"</option>
                        </select>
                    </p>
                    <p>
                        <input type="text" value="900" readonly="readonly" class="input_select" id="price1"/>
                        <span class="span">.kzt</span>
                        <br>
                        <input class="btn btn-primary" type="button" value="Add to order list">
                    </p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="../static/img/godfatherpizza.png">
                <div class="caption">
                    <h3>Godfather Pizza</h3>
                    <p>Ham, sausage, pepperoni and onions.</p>
                    <br>
                    <p>
                        <select name="size" class="select"
                                onchange="document.getElementById('price2').value=this.value">
                            <option value="900">Small 8"</option>
                            <option value="1200">Medium 12"</option>
                            <option value="1500">Big 18"</option>
                        </select>
                    </p>
                    <p>
                        <input type="text" value="900" readonly="readonly" class="input_select" id="price2"/>
                        <span class="span">.kzt</span>
                        <br>
                        <input class="btn btn-primary" type="button" value="Add to order list">
                    </p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="../static/img/cubanpizza.png">
                <div class="caption">
                    <h3>Cuban Style Pizza</h3>
                    <p>Sweet plantains and ground beef.</p>
                    <br>
                    <p>
                        <select name="size" class="select"
                                onchange="document.getElementById('price3').value=this.value">
                            <option value="900">Small 8"</option>
                            <option value="1200">Medium 12"</option>
                            <option value="1500">Big 18"</option>
                        </select>
                    </p>
                    <p>
                        <input type="text" value="900" readonly="readonly" class="input_select" id="price3"/>
                        <span class="span">.kzt</span>
                        <br>
                        <input class="btn btn-primary" type="button" value="Add to order list">
                    </p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="../static/img/monalizapizza.png">
                <div class="caption">
                    <h3>Mona Lisa Pizza</h3>
                    <p>Ham, mushrooms, onions, green peppers and black olives.</p>
                    <br>
                    <p>
                        <select name="size" class="select"
                                onchange="document.getElementById('price4').value=this.value">
                            <option value="900">Small 8"</option>
                            <option value="1200">Medium 12"</option>
                            <option value="1500">Big 18"</option>
                        </select>
                    </p>
                    <p>
                        <input type="text" value="900" readonly="readonly" class="input_select" id="price4"/>
                        <span class="span">.kzt</span>
                        <br>
                        <input class="btn btn-primary" type="button" value="Add to order list">
                    </p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="../static/img/cheasepizza.png">
                <div class="caption">
                    <h3>White Cheese Pizza </h3>
                    <p>Sricotta, mozzarella and parmesan cheese, garlic oil.</p>
                    <br>
                    <p>
                        <select name="size" class="select"
                                onchange="document.getElementById('price5').value=this.value">
                            <option value="900">Small 8"</option>
                            <option value="1200">Medium 12"</option>
                            <option value="1500">Big 18"</option>
                        </select>
                    </p>
                    <p>
                        <input type="text" value="900" readonly="readonly" class="input_select" id="price5"/>
                        <span class="span">.kzt</span>
                        <br>
                        <input class="btn btn-primary" type="button" value="Add to order list">
                    </p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="../static/img/melonyspizza.png">
                <div class="caption">
                    <h3>Melany's Pizza </h3>
                    <p>Ham, pineapple, corn and onions.</p>
                    <br>
                    <br>
                    <p>
                        <select name="size" class="select"
                                onchange="document.getElementById('price6').value=this.value">
                            <option value="900">Small 8"</option>
                            <option value="1200">Medium 12"</option>
                            <option value="1500">Big 18"</option>
                        </select>
                    </p>
                    <p>
                        <input type="text" value="900" readonly="readonly" class="input_select" id="price6"/>
                        <span class="span">.kzt</span>
                        <br>
                        <input class="btn btn-primary" type="button" value="Add to order list">
                    </p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="../static/img/damianpizza.png">
                <div class="caption">
                    <h3>Damian's Pizza </h3>
                    <p>Chicken, BBQ sauce and onions.</p>
                    <br>
                    <br>
                    <p>
                        <select name="size" class="select"
                                onchange="document.getElementById('price7').value=this.value">
                            <option value="900">Small 8"</option>
                            <option value="1200">Medium 12"</option>
                            <option value="1500">Big 18"</option>
                        </select>
                    </p>
                    <p>
                        <input type="text" value="900" readonly="readonly" class="input_select" id="price7"/>
                        <span class="span">.kzt</span>
                        <br>
                        <input class="btn btn-primary" type="button" value="Add to order list">
                    </p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="../static/img/assortipizza.png">
                <div class="caption">
                    <h3>Pizza Assorti Gourmet</h3>
                    <p>A assortment of 4 kinds of pizza! Cuban style, Melany's, Damian's, Hawaiian.</p>
                    <br>
                    <p>
                        <select name="size" class="select"
                                onchange="document.getElementById('price8').value=this.value">
                            <option value="900">Small 8"</option>
                            <option value="1200">Medium 12"</option>
                            <option value="1500">Big 18"</option>
                        </select>
                    </p>
                    <p>
                        <input type="text" value="900" readonly="readonly" class="input_select" id="price8"/>
                        <span class="span">.kzt</span>
                        <br>
                        <input class="btn btn-primary" type="button" value="Add to order list">
                    </p>
                </div>
            </div>
        </div>
    </div>
    <div id="order" class="section">
        <h1>Your order</h1>
        <hr>
        <table id="myTable">
            <tr>
                <td>Name</td>
                <td></td>
                <td>. . . . . . . . . . . . . . . . . . . . . . . . . .</td>
                <td></td>
                <td>price</td>
            </tr>

        </table>
        <hr>
        <input class="btn btn-primary" type="submit" value="Confirm"/>

    </div>
    <div id="rasporka"></div>
</div>
<div id="footer">
    &copy; 2017 JavaLab#22 Vassilyev Andrey.
</div>


</body>
</html>