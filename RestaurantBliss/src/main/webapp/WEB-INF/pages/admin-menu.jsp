<%--
  Created by IntelliJ IDEA.
  User: Евгения
  Date: 24.05.2015
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:template>

    <section class="content gallery pad1">

        <div class="container">
            <div class="row">
                <div class="grid_4">
                    <ul class="menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/getDishTypes">Работа с типами блюд</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/getDishes">Работа с блюдами</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/getTables">Работа со столами</a>
                        </li>
                    </ul>
                </div>
                <div class="grid_8">
                    <c:if test="${!empty mess}">
                        <span style="text-align: center">${mess}</span>
                    </c:if>
                        <c:if test="${!empty dishTypes}">
                            <h2>Типы блюд</h2>
                            <table class="simple-little-table">
                                <tr>
                                    <th>Типы блюд</th>
                                    <th>&nbsp;Действие</th>
                                </tr>
                                <c:forEach items="${dishTypes}" var="dishType">
                                    <tr>
                                        <td>${dishType.dtName}</td>
                                        <td><a href="deleteDishType/${dishType.id}">Удалить</a> |
                                            <a href="editDishType/${dishType.id}">Изменить</a></td>
                                    </tr>
                                </c:forEach>
                                <tr><th><a href="addDishType">Добавить</a></th></tr>
                            </table>
                        </c:if>

                    <c:if test="${!empty dishes}">
                        <h2>Блюда</h2>
                        <table class="simple-little-table">
                            <tr>
                                <th>Название</th>
                                <th>Описание</th>
                                <th>Масса</th>
                                <th>Цена</th>
                                <th>&nbsp;Действие</th>
                            </tr>
                            <c:forEach items="${dishes}" var="dish">
                                <tr>
                                    <td>${dish.DName}</td>
                                    <td>${dish.DComposition}</td>
                                    <td>${dish.DMass}</td>
                                    <td>${dish.DPrice}</td>
                                    <td><a href="deleteDish/${dish.id}">Удалить</a> |
                                        <a href="editDish/${dish.id}">Изменить</a></td>
                                </tr>
                            </c:forEach>
                            <tr><th><a href="addDish">Добавить</a></th></tr>
                        </table>
                    </c:if>

                    <c:if test="${!empty tables}">
                        <h2>Столы</h2>
                        <table class="simple-little-table">
                            <tr>
                                <th>Номер стола</th>
                                <th>Количество мест</th>
                                <th>&nbsp;Действие</th>
                            </tr>
                            <c:forEach items="${tables}" var="table">
                                <tr>
                                    <td>${table.id}</td>
                                    <td>${table.TNumOfSeats}</td>
                                    <td><a href="deleteTable/${table.id}">Удалить</a> |
                                        <a href="editTable/${table.id}">Изменить</a></td>
                                </tr>
                            </c:forEach>
                            <tr><th><a href="addTable">Добавить</a></th></tr>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>
    </section>
</t:template>
