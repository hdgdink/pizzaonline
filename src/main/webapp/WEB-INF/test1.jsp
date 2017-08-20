<%--
  Created by IntelliJ IDEA.
  User: Лёха
  Date: 18.08.2017
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Pizza</title>
    <link rel="stylesheet" type="text/css" href="../static/css/test1.css"/>
</head>
<body>
<div id="main">

    <div id="header" class="section">
        <p><img src="../static/img/turtlelogo.png"></p>
        <input type="text" value="user name" id="username" class="button"/>
        <input type="password" value="password" id="password" class="button"/>
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

        <div id="fistLine">
            <div class="firstImage">
                <img src="../static/img/hawaiipizza.png">
            </div>
            <div class="secondImage">
                <img src="../static/img/godfatherpizza.png">
            </div>
            <div class="thirdImage">
                <img src="../static/img/cubanpizza.png">
            </div>
            <div class="fourthImage">
                <img src="../static/img/monalizapizza.png">
            </div>
            <div class="firstElement">
                <p><strong>Hawaiian Pizza</strong></p>
                <p>Pineapple and ham.</p>
                <br><br>
                <div class="action">
                    <select name="size">
                        <option value="small">Small 8"</option>
                        <option value="medium">Medium 12"</option>
                        <option value="big">Big 18"</option>
                    </select><br>
                    <button class="count">-</button>
                    <input type="text" class="labelcount" readonly="readonly" value="0" maxlength="6">
                    <button class="count">+</button>
                    <br>
                    <input class="count" type="button" value="OK">
                </div>
            </div>

            <div class="secondElement">
                <p><strong>Godfather Pizza</strong></p>
                <p>Ham, sausage, pepperoni and onions.</p>
                <br>
                <div class="action">
                    <select name="size">
                        <option value="small">Small 8"</option>
                        <option value="medium">Medium 12"</option>
                        <option value="big">Big 18"</option>
                    </select><br>
                    <button class="count">-</button>
                    <input type="text" class="labelcount" readonly="readonly" value="0" maxlength="6">
                    <button class="count">+</button>
                    <br>
                    <input class="count" type="button" value="OK">
                </div>
            </div>

            <div class="thirdElement">
                <p><strong>Cuban Style Pizza</strong></p>
                <p>Sweet plantains and ground beef.</p>
                <br>
                <div class="action">
                    <select name="size">
                        <option value="small">Small 8"</option>
                        <option value="medium">Medium 12"</option>
                        <option value="big">Big 18"</option>
                    </select><br>
                    <button class="count">-</button>
                    <input type="text" class="labelcount" readonly="readonly" value="0" maxlength="6">
                    <button class="count">+</button>
                    <br>
                    <input class="count" type="button" value="OK">
                </div>
            </div>
            <div class="fourthElement">
                <p><strong>Mona Lisa Pizza</strong></p>
                <p>Ham, mushrooms, onions, green peppers and black olives.</p>
                <div class="action">
                    <select name="size">
                        <option value="small">Small 8"</option>
                        <option value="medium">Medium 12"</option>
                        <option value="big">Big 18"</option>
                    </select><br>
                    <button class="count">-</button>
                    <input type="text" class="labelcount" readonly="readonly" value="0" maxlength="6">
                    <button class="count">+</button>
                    <br>
                    <input class="count" type="button" value="OK">
                </div>
            </div>
        </div>

        <div id="secondLine">
            <div class="firstImage">
                <img src="../static/img/cheasepizza.png">
            </div>
            <div class="secondImage">
                <img src="../static/img/melonyspizza.png">
            </div>
            <div class="thirdImage">
                <img src="../static/img/damianpizza.png">
            </div>
            <div class="fourthImage">
                <img src="../static/img/assortipizza.png">
            </div>
            <div class="firstElement">
                <p><strong>White Cheese Pizza </strong></p>
                <p>Sricotta, mozzarella and parmesan cheese, garlic oil.</p><br>
                <select name="size">
                    <option value="small">Small 8"</option>
                    <option value="medium">Medium 12"</option>
                    <option value="big">Big 18"</option>
                </select>
                <div>
                    <button class="count">-</button>
                    <input type="text" class="labelcount" readonly="readonly" value="0" maxlength="6">
                    <button class="count">+</button>
                    <input class="count" type="button" value="OK">
                </div>

            </div>
            <div class="secondElement">
                <p><strong>Melany's Pizza </strong></p>
                <p>Ham, pineapple, corn and onions.</p><br>
                <br>
                <select name="size">
                    <option value="small">Small 8"</option>
                    <option value="medium">Medium 12"</option>
                    <option value="big">Big 18"</option>
                </select>
                <div>
                    <button class="count">-</button>
                    <input type="text" class="labelcount" readonly="readonly" value="0" maxlength="6">
                    <button class="count">+</button>
                    <input class="count" type="button" value="OK">
                </div>

            </div>
            <div class="thirdElement">
                <p><strong>Damian's Pizza </strong></p>
                <p>Chicken, BBQ sauce and onions.</p><br>
                <br>
                <select name="size">
                    <option value="small">Small 8"</option>
                    <option value="medium">Medium 12"</option>
                    <option value="big">Big 18"</option>
                </select>
                <div>
                    <button class="count">-</button>
                    <input type="text" class="labelcount" readonly="readonly" value="0" maxlength="6">
                    <button class="count">+</button>
                    <input class="count" type="button" value="OK">
                </div>
            </div>
            <div class="fourthElement">
                <p><strong>Pizza Assorti Gourmet</strong></p>
                <p>A assortment of 4 kinds of pizza - 2 pieces each! Cuban style, Melany's, Damian's, Hawaiian.</p>
                <select name="size">
                    <option value="small">Small 8"</option>
                    <option value="medium">Medium 12"</option>
                    <option value="big">Big 18"</option>
                </select>
                <div>
                    <button class="count">-</button>
                    <input type="text" class="labelcount" readonly="readonly" value="0" maxlength="6">
                    <button class="count">+</button>
                    <input class="count" type="button" value="OK">
                </div>
            </div>
        </div>
    </div>


    <div id="order" class="section">
        <h1>Your order</h1>
        <td>
            <tr> fist <br></tr>
        </td>
        <hr>
        <input id="input" type="submit" value="Confirm" class="submit"/>
    </div>
    <div id="rasporka"></div>
</div>
<div id="footer">
    &copy; 2017 JavaLab#22 Vassilyev Andrey.
</div>
</body>
</html>