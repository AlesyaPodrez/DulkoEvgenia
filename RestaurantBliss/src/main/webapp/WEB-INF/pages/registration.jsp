<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Евгения
  Date: 23.05.2015
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<t:template>
    <!--=====================
    Content
    ======================-->
    <section class="content gallery pad1">

        <div class="container">
            <div class="row">
                <div class="grid_4"></div>
                <div class="grid_4" class="form_title color1">
                    <h2>Зарегистрироваться</h2>
                    <c:if test="${!empty mess}">
                            <span style="color: red; ">${mess}</span>
                    </c:if>
                    <form method="post" action="registration">
                        <form:form method="post" action="registration" commandName="user">
                            <table cellspacing="3px">
                                <tr>
                                    <td><form:label path="ULogin">Логин:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</form:label></td>
                                    <td><form:input path="uLogin" required="required"/></td>
                                </tr>
                                <tr>
                                    <td><form:label path="UPassword">Пароль:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</form:label></td>
                                    <td><form:input path="uPassword" type="password" required="required"/></td>
                                </tr>
                            </table>
                            <form:form method="post" action="registration" commandName="client">
                                <table cellspacing="5">
                                    <tr>
                                        <td><form:label path="CSurname">Фамилия:</form:label></td>
                                        <td><form:input path="cSurname" required="required" pattern="^[А-Яа-яЁё]+$"
                                                        title="Фамилия должна состоять только из символов кириллицы"/></td>
                                    </tr>
                                    <tr>
                                        <td><form:label path="CName">Имя:</form:label></td>
                                        <td><form:input path="cName" required="required" pattern="^[А-Яа-яЁё]+$"
                                                        title="Имя должно состоять только из символов кириллицы"/></td>
                                    </tr>
                                    <tr>
                                        <td><form:label path="CPhone">Телефон: +</form:label></td>
                                        <td><form:input type="tel" path="cPhone" required="required"
                                                        pattern="375[0-9]{9}"
                                                        placeholder="375XXXXXXXXX"
                                                        title="Телефон должен быть в формате 375ХХХХХХХХХ"/></td>
                                    </tr>
                                    <tr>
                                        <td><form:label path="CAdress">Адрес:</form:label></td>
                                        <td><form:input path="cAdress" required="required"/></td>
                                    </tr>
                                </table>
                                <input type="submit" class="btn" value="Зарегистрироваться"/>
                            </form:form>
                        </form:form>
                    </form>

                </div>
                <div class="grid_4"></div>
            </div>
        </div>
    </section>
</t:template>
