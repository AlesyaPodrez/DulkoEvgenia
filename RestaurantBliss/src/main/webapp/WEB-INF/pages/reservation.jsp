<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Евгения
  Date: 14.05.2015
  Time: 7:48
  To change this template use File | Settings | File Templates.
--%>
<t:template>
    <!--=====================
    Content
    ======================-->
    <section class="content">
        <div class="container">
            <div class="row">
                <div class="titleDiv"><h2>Закажите столик</h2></div>
                <div class="grid_4">
                    <form action="${pageContext.request.contextPath}/reservation" method="post">
                        <h3>Выберите дату и время</h3>

                        <div>
                            <input type="date" name="dateStr" value="${date}" min="${date}">
                            <input type="time" name="timeInStr" value="12:00" min="12:00" max="22:00">
                        </div>
                        <div class="form-actions">
                            <input type="submit" class="btn" value="поиск" class="form-submit">
                        </div>
                    </form>
                </div>
                <div class="grid_4">
                    <c:if test="${!empty tables}">
                        <h3>Выберите столик</h3>
                        <sec:authorize access="isAuthenticated()">
                            <form:form method="post"
                                       action="${pageContext.request.contextPath}/addReservationAuthenticated"
                                       commandName="client">
                                <div class="form_title color1">
                                    Выбранная дата:<input type="text" name="dateStr" value="${yourRes.RDate}"
                                                          readonly="true"><br>
                                    Выбранное время:<input type="text" name="timeInStr" value="${yourRes.RTime}"
                                                           readonly="true"><br>
                                </div>
                                <br>
                                <h4>Свободные столики</h4><br>
                                <table class="form_title color1">
                                    <tr>
                                        <th>Номер столика</th>
                                        <th>Количество мест</th>
                                    </tr>
                                    <c:forEach items="${tables}" var="table">
                                        <tr>
                                            <td><input type="radio" name="selectedTable" checked="true"
                                                       value="${table.id}">${table.id}</td>
                                            <td>${table.TNumOfSeats}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <br>
                                <input type="submit" class="btn" value="зарезервировать" class="form-submit">

                            </form:form>
                        </sec:authorize>
                        <sec:authorize access="isAnonymous()">
                            <form:form method="post" action="${pageContext.request.contextPath}/addReservation"
                                       commandName="client">
                                <div class="form_title color1">
                                    Выбранная дата:<input type="text" name="dateStr" value="${yourRes.RDate}"
                                                          readonly="true"><br>
                                    Выбранное время:<input type="text" name="timeInStr" value="${yourRes.RTime}"
                                                           readonly="true"><br>
                                </div>
                                <br>
                                <h4>Свободные столики</h4><br>
                                <table class="form_title color1">
                                    <tr>
                                        <th>Номер столика</th>
                                        <th>Количество мест</th>
                                    </tr>
                                    <c:forEach items="${tables}" var="table">
                                        <tr>
                                            <td><input type="radio" name="selectedTable" checked="true"
                                                       value="${table.getId()}">${table.getId()}</td>
                                            <td>${table.getTNumOfSeats()}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <br>

                                <h4>Введите контактную информацию</h4><br>
                                <table class="form_title color1">
                                    <tr>
                                        <td><form:input placeHolder="Фамилия:" path="cSurname" required="required"
                                                        pattern="^[А-Яа-яЁё]+$"
                                                        title="Фамилия должна состоять только из символов кириллицы"/></td>
                                    </tr>
                                    <tr>
                                        <td><form:input placeHolder="Имя:" path="cName" required="required"
                                                        pattern="^[А-Яа-яЁё]+$"
                                                        title="Имя должно состоять только из символов кириллицы"/></td>
                                    </tr>
                                    <tr>
                                        <td><form:input typr="tel" path="cPhone" required="required"
                                                        pattern="375[0-9]{9}"
                                                        placeholder="375XXXXXXXXX"
                                                        title="Телефон должен быть в формате 375ХХХХХХХХХ"/></td>
                                    </tr>
                                </table>
                                <input type="submit" class="btn" value="зарезервировать" class="form-submit">

                            </form:form>
                        </sec:authorize>
                    </c:if>
                </div>


                <div class="grid_4">
                    <h3>Время работы</h3>

                    <div class="form_title color1">
                        Мы открыты 7 дней в неделю с 12-00 до 24-00 <br>+1 101 889 9898
                    </div>

                </div>
            </div>
        </div>
    </section>

</t:template>