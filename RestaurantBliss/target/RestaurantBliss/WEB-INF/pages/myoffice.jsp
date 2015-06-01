<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
  Created by IntelliJ IDEA.
  User: Евгения
  Date: 09.05.2015
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>

<t:template>
    <section class="content gallery pad1">

        <div class="container">
            <div class="row">
                <div class="grid_4">
                    <ul class="menu">

                        <li>
                            <a href="${pageContext.request.contextPath}/editClient">Изменить личные данные</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/myReservation">Зарезервированные столы</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/myOrder">Мои заказы</a>
                        </li>
                    </ul>
                    <div class="clear sep__1"></div>
                </div>

                <div class="grid_8">
                    <c:if test="${!empty mess}">
                        <span>${mess}</span>
                    </c:if>
                    <c:if test="${!empty orders}">
                        <h1>Мои заказы</h1>
                        <table class="simple-little-table">
                            <c:forEach items="${orders}" var="order">
                                    <tr style="font-weight: bold; font-size: 15px; color: #120d0c">
                                        <td>Заказ</td>
                                        <td>Дата доставки: <br>${order.ODeliveryDate}</td>
                                        <td>Время доставки: ${order.ODeliveryTime}</td>
                                        <td><a href="deleteOrder/${order.id}">Отменить</a></td>
                                    </tr>
                                    <c:forEach items="${order.orderDishCollection}" var="orderDish">
                                        <tr>
                                            <td>Блюдо</td>
                                            <td>Название: ${orderDish.dish.DName}</td>
                                            <td>Цена: ${orderDish.dish.DPrice}</td>
                                            <td></td>
                                        </tr>
                                    </c:forEach>
                                    <tr style="font-weight: bold; font-size: 15px; color: #120d0c">
                                        <td></td>
                                        <td></td>
                                        <td>Итого: ${cost[orders.indexOf(order)]} р.</td>
                                        <td></td>
                                    </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                    <c:if test="${!empty reservations}">
                        <h1>Мои зарезервированные столики</h1>
                        <table class="simple-little-table">
                            <tr>
                                <th>Дата</th>
                                <th>Время</th>
                                <th>Номер столика</th>
                                <th>Количество человек</th>
                                <th>Отменить</th>
                            </tr>
                            <c:forEach items="${reservations}" var="reservation">
                                <tr>
                                    <td>${reservation.RDate}</td>
                                    <td>${reservation.RTime}</td>
                                    <td>${reservation.TId.id}</td>
                                    <td>${reservation.TId.TNumOfSeats}</td>
                                    <td><a href="deleteReservation/${reservation.id}">Отменить</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                    <c:if test="${!empty client}">
                        <h2>Редактирование личных данных</h2>
                        <form:form method="post" action="../editClient" commandName="client">
                            <table class="simple-little-table">
                                <tr>
                                    <td>Имя поля</td>
                                    <td>Значение</td>
                                </tr>
                                <tr hidden="true">
                                    <td>Id</td>
                                    <td><form:input path="id" readonly="true"/></td>
                                </tr>
                                <tr>
                                    <td><form:label path="CSurname">
                                        Фамилия
                                    </form:label></td>
                                    <td><form:input path="cSurname"/></td>
                                </tr>
                                <tr>
                                    <td><form:label path="CName">
                                        Имя
                                    </form:label></td>
                                    <td><form:input path="cName"/></td>
                                </tr>
                                <tr>
                                    <td><form:label path="CPhone">
                                        Номер телефона
                                    </form:label></td>
                                    <td><form:input path="cPhone"/></td>
                                </tr>
                                <tr>
                                    <td><form:label path="CAdress">
                                        Адресс
                                    </form:label></td>
                                    <td><form:input path="cAdress"/></td>
                                </tr>
                                <tr>
                                    <td colspane="2"><input type="submit" value="Сохранить"/></td>
                                </tr>
                            </table>
                        </form:form>
                    </c:if>

                </div>
            </div>
        </div>
    </section>
</t:template>
