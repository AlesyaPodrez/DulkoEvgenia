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
        <div class="grid_12">
            <c:if test="${!empty table}">
                <form:form method="post" action="../editTable" commandName="table">
                    <h2>Добавьте стол</h2>
                    <table class="simple-little-table">
                        <tr>
                            <td>Имя поля</td>
                            <td>Значение</td>
                            <td>Информация</td>
                        </tr>
                        <tr hidden="true">
                            <td>Id</td>
                            <td><form:input path="id" readonly="true"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><form:label path="TNumOfSeats">
                                Количество мест
                            </form:label></td>
                            <td><form:input path="tNumOfSeats"/></td>
                            <td><form:errors path="tNumOfSeats"></form:errors></td>

                        </tr>
                        <tr>
                            <th colspane="2"><input type="submit" value="Сохранить"/></th>
                        </tr>
                    </table>
                </form:form>
            </c:if>
            <c:if test="${!empty dishType}">
                <form:form method="post" action="../editDishType" commandName="dishType">
                    <h2>Добавьте тип блюда</h2>
                    <table class="simple-little-table">
                        <tr>
                            <td>Имя поля</td>
                            <td>Значение</td>
                            <td>Информация</td>
                        </tr>
                        <tr hidden="true">
                            <td>Id</td>
                            <td><form:input path="id" readonly="true"/></td>
                        </tr>
                        <tr>
                            <td><form:label path="DtName">
                                Название блюда
                            </form:label></td>
                            <td><form:input path="dtName"/></td>
                            <td><form:errors path="dtName"></form:errors></td>

                        </tr>
                        <tr>
                            <th colspane="2"><input type="submit" value="Сохранить"/></th>
                        </tr>
                    </table>
                </form:form>
            </c:if>
            <c:if test="${!empty dish}">
                <form:form method="post" action="../editDish" commandName="dish" enctype="multipart/form-data">
                    <h2>Добавьте блюдо</h2>
                    <table class="simple-little-table">
                        <tr>
                            <td>Имя поля</td>
                            <td>Значение</td>
                            <td>Информация</td>
                        </tr>
                        <tr hidden="true">
                            <td>Id</td>
                            <td><form:input path="id" readonly="true"/></td>
                        </tr>
                        <tr>
                            <td><form:label path="DName">
                                Название блюда
                            </form:label></td>
                            <td><form:input path="dName"/></td>
                            <td><form:errors path="dName"></form:errors></td>
                        </tr>
                        <tr>
                            <td><form:label path="DComposition">
                                Описание
                            </form:label></td>
                            <td><form:input path="dComposition"/></td>
                            <td><form:errors path="dComposition"></form:errors></td>
                        </tr>
                        <tr>
                            <td><form:label path="DMass">
                                Масса
                            </form:label></td>
                            <td><form:input path="dMass"/></td>
                            <td><form:errors path="dMass"></form:errors></td>
                        </tr>
                        <tr>
                            <td><form:label path="DPrice">
                                Цена
                            </form:label></td>
                            <td><form:input path="dPrice"/></td>
                            <td><form:errors path="dPrice"></form:errors></td>
                        </tr>
                        <tr>
                            <td><form:label path="DtId">
                                Тип блюда
                            </form:label></td>
                            <td><form:select path="dtId">
                                <c:forEach items="${dishTypes}" var="dishType">
                                    <option value="${dishType.id}">${dishType.dtName}</option>
                                </c:forEach>
                            </form:select>
                            </td>
                        </tr>
                        <tr>
                            <td><form:label path="DPicture">
                                Фото
                            </form:label></td>
                            <td><form:input type="file" path="dPicture"/></td>
                        </tr>
                        <br>
                        <tr>
                            <th colspane="2"><input type="submit" value="Сохранить"/></th>
                        </tr>
                    </table>
                </form:form>
            </c:if>
        </div>
    </div>
</t:template>
