<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Pizza</title>
    <link rel="stylesheet" type="text/css" href="../static/css/test1.css"/>
    <link rel="stylesheet" type="text/css" href="../static/css/bootstrap.css"/>

    <script src='../static/js/button.js'></script>

</head>
<body>
<div id="main">
    <div id="header" class="section">
        <p><img src="../static/img/turtlelogo.png"></p>
        <input type="text" value="user name" id="username"/>
        <input type="password" value="password" id="password" c/>
        <button title="Enter" id="login" class="button"> LOGIN</button>
        <button title="Registration" id="sigup" class="button"> SIGN UP</button>
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
                    <p>
                        <select name="size" class="select">
                            <option value="small">Small 8"</option>
                            <option value="medium">Medium 12"</option>
                            <option value="big">Big 18"</option>
                        </select>
                    </p>
                    <p>
                        <a href="#" class="btn btn-default" role="button">-</a>
                        <input type="text" class="labelcount" readonly="readonly" value="0" maxlength="6">
                        <a href="#" class="btn btn-primary" role="button">+</a>
                        <br>
                        <br>
                        <input class="btn btn-primary" type="button" value="OK">
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
                    <p>
                        <select name="size" class="select">
                            <option value="small">Small 8"</option>
                            <option value="medium">Medium 12"</option>
                            <option value="big">Big 18"</option>
                        </select>
                    </p>
                    <p>
                        <a href="#" class="btn btn-default" role="button">-</a>
                        <input type="text" class="labelcount" readonly="readonly" value="0" maxlength="6">
                        <a href="#" class="btn btn-primary" role="button">+</a>
                        <br>
                        <br>
                        <input class="btn btn-primary" type="button" value="OK">
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
                    <p>
                        <select name="size" class="select">
                            <option value="small">Small 8"</option>
                            <option value="medium">Medium 12"</option>
                            <option value="big">Big 18"</option>
                        </select>
                    </p>
                    <p>
                        <a href="#" class="btn btn-default" role="button">-</a>
                        <input type="text" class="labelcount" readonly="readonly" value="0" maxlength="6">
                        <a href="#" class="btn btn-primary" role="button">+</a>
                        <br>
                        <br>
                        <input class="btn btn-primary" type="button" value="OK">
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
                    <p>
                        <select name="size" class="select">
                            <option value="small">Small 8"</option>
                            <option value="medium">Medium 12"</option>
                            <option value="big">Big 18"</option>
                        </select>
                    </p>
                    <p>
                        <a href="#" class="btn btn-default" role="button">-</a>
                        <input type="text" class="labelcount" readonly="readonly" value="0" maxlength="6">
                        <a href="#" class="btn btn-primary" role="button">+</a>
                        <br>
                        <br>
                        <input class="btn btn-primary" type="button" value="OK">
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
                    <p>
                        <select name="size" class="select">
                            <option value="small">Small 8"</option>
                            <option value="medium">Medium 12"</option>
                            <option value="big">Big 18"</option>
                        </select>
                    </p>
                    <p>
                        <a href="#" class="btn btn-default" role="button">-</a>
                        <input type="text" class="labelcount" readonly="readonly" value="0" maxlength="6">
                        <a href="#" class="btn btn-primary" role="button">+</a>
                        <br>
                        <br>
                        <input class="btn btn-primary" type="button" value="OK">
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
                    <p>
                        <select name="size" class="select">
                            <option value="small">Small 8"</option>
                            <option value="medium">Medium 12"</option>
                            <option value="big">Big 18"</option>
                        </select>
                    </p>
                    <p>
                        <a href="#" class="btn btn-default" role="button">-</a>
                        <input type="text" class="labelcount" readonly="readonly" value="0" maxlength="6">
                        <a href="#" class="btn btn-primary" role="button">+</a>
                        <br>
                        <br>
                        <input class="btn btn-primary" type="button" value="OK">
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
                    <p>
                        <select name="size" class="select">
                            <option value="small">Small 8"</option>
                            <option value="medium">Medium 12"</option>
                            <option value="big">Big 18"</option>
                        </select>
                    </p>
                    <p>
                        <a href="#" class="btn btn-default" role="button">-</a>
                        <input type="text" class="labelcount" readonly="readonly" value="0" maxlength="6">
                        <a href="#" class="btn btn-primary" role="button">+</a>
                        <br>
                        <br>
                        <input class="btn btn-primary" type="button" value="OK">
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
                    <p>
                        <select name="size" class="select">
                            <option value="small">Small 8"</option>
                            <option value="medium">Medium 12"</option>
                            <option value="big">Big 18"</option>
                        </select>
                    </p>
                    <p>
                        <a href="#" class="btn btn-default" role="button">-</a>
                        <input type="text" class="labelcount" readonly="readonly" value="0" maxlength="6">
                        <a href="#" class="btn btn-primary" role="button">+</a>
                        <br>
                        <br>
                        <input class="btn btn-primary" type="button" value="OK">
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