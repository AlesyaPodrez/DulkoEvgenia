<%--
  Created by IntelliJ IDEA.
  User: Евгения
  Date: 20.05.2015
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:template>

    <!--=====================
    Content
    ======================-->
    <section class="content">
        <div class="container">
            <div class="row">
                <div class="titleDiv"><h2>Проверьте свой заказ</h2></div>
                <sec:authorize access="isAnonymous()">
                    <form:form action="${pageContext.request.contextPath}/addOrder" method="post" commandName="client">
                        <c:if test="${!empty mess}">
                            <span style="text-align: center">${mess}</span>
                        </c:if>
                        <c:if test="${!empty dishes}">
                            <h3 style="text-align: center">Выбранные блюда</h3>
                            <table class="simple-little-table">
                                <tr>
                                    <th>Название</th>
                                    <th>Состав</th>
                                    <th>Масса</th>
                                    <th>Цена</th>
                                    <th>&nbsp;</th>
                                </tr>
                                <c:forEach items="${dishes}" var="dish">
                                    <tr>
                                        <td>${dish.DName}</td>
                                        <td>${dish.DComposition}</td>
                                        <td>${dish.DMass}</td>
                                        <td>${dish.DPrice}</td>
                                        <form:form action="${pageContext.request.contextPath}/deleteOrderDish"
                                                   method="get">
                                            <td hidden="true"><input name="dishId" value="${dish.id}"/></td>
                                            <td hidden="true"><input name="orderDish" value="${orderDish}"/></td>
                                            <td><input type="submit" class="btn" value="Отменить"/></td>
                                        </form:form>

                                    </tr>
                                </c:forEach>
                                <th>Итого: ${cost} p.</th>
                            </table>
                            <div>
                                <h4>Введите контактную информацию</h4><br>
                                <table class="form_title color1">
                                    <tr>
                                        <td>Фамилия:</td>
                                        <td><form:input path="cSurname" required="required"
                                                        pattern="^[А-Яа-яЁё]+$"
                                                        title="Фамилия должна состоять только из символов кириллицы"/></td>
                                    </tr>
                                    <tr>
                                        <td>Имя:</td>
                                        <td><form:input path="cName" required="required"
                                                        pattern="^[А-Яа-яЁё]+$"
                                                        title="Имя должно состоять только из символов кириллицы"/></td>
                                    </tr>
                                    <tr>
                                        <td>Номер телефона:</td>
                                        <td><form:input typr="tel" path="cPhone" required="required"
                                                        pattern="375[0-9]{9}"
                                                        placeholder="375XXXXXXXXX"
                                                        title="Телефон должен быть в формате 375ХХХХХХХХХ"/></td>
                                    </tr>
                                    <tr>
                                        <td>Адрес доставки:</td>
                                        <td><form:input path="cAdress" required="required"/></td>
                                    </tr>
                                    <tr>
                                        <td>Дата доставки:</td>
                                        <td><input name="date" type="date" value="${date}" min="${date}"/></td>
                                    </tr>
                                    <tr>
                                        <td>Время доставки:</td>
                                        <td><input name="time" type="time" value="12:00" min="12:00" max="22:00"/></td>
                                    </tr>
                                </table>
                            </div>
                            <input type="submit" class="btn" value="Заказать"/>
                        </c:if>
                    </form:form>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <form:form action="${pageContext.request.contextPath}/addOrderAuthenticated" method="post"
                               commandName="client">
                        <c:if test="${!empty mess}">
                            <span style="text-align: center">${mess}</span>
                        </c:if>
                        <c:if test="${!empty dishes}">
                            <h3 style="text-align: center">Выбранные блюда</h3>
                            <table class="simple-little-table">
                                <tr>
                                    <th>Название</th>
                                    <th>Состав</th>
                                    <th>Масса</th>
                                    <th>Цена</th>
                                    <th>&nbsp;</th>
                                </tr>
                                <c:forEach items="${dishes}" var="dish">
                                    <tr>
                                        <td>${dish.DName}</td>
                                        <td>${dish.DComposition}</td>
                                        <td>${dish.DMass}</td>
                                        <td>${dish.DPrice}</td>
                                        <form:form action="${pageContext.request.contextPath}/deleteOrderDish"
                                                   method="get">
                                            <td hidden="true"><input name="dishId" value="${dish.id}"/></td>

                                            <td hidden="true"><input name="orderDish" value="${orderDish}"/></td>
                                            <td><input type="submit" class="btn" value="Отменить"/></td>
                                        </form:form>
                                    </tr>
                                </c:forEach>
                                <th>Итого: ${cost} p.</th>
                            </table>
                            <div>
                                <h4>Выберите удобные для вас дату и время</h4><br>
                                <tr>
                                    <td>Дата доставки:</td>
                                    <td><input name="date" type="date" value="${date}" min="${date}"/></td>
                                </tr>
                                <tr>
                                    <td>Время доставки:</td>
                                    <td><input name="time" type="time" value="12:00" min="12:00" max="22:00"/></td>
                                </tr>
                                </table>
                            </div>
                            <input type="submit" class="btn" value="Заказать"/>
                        </c:if>
                    </form:form>
                </sec:authorize>
            </div>
        </div>
    </section>

</t:template>
