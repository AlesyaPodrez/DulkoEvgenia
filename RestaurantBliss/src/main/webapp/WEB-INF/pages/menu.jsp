<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <section class="content gallery pad1">

        <div class="container">
            <div class="row">
                <c:if test="${!empty dishTypes}">
                    <div class="grid_4">
                        <ul class="menu">
                            <c:forEach items="${dishTypes}" var="dishType">
                            <li>
                                <a href="${pageContext.request.contextPath}/getDishCollection/${dishType.getId()}">${dishType.getDtName()}</a>
                                </c:forEach>
                        </ul>
                        <div class="clear sep__1"></div>
                    </div>
                    <div style="text-align:center" class="grid_4">
                        <br>

                        <h1 class="fa-3x">Горячие блюда</h1>

                        <div class="clear sep__1"></div>
                    </div>
                    <div class="grid_13">
                        <form action="${pageContext.request.contextPath}/findDish" method="post" id="search-block-form">
                            <div class="form-item">
                                <input type="text" name="dishName" value="" size="15" maxlength="128"
                                       placeholder="Найти...">
                            </div>
                            <div class="form-actions">
                                <input type="submit" value="Поиск" class="form-submit">
                            </div>
                        </form>
                    </div>
                </c:if>
            </div>

            <div class="row">
                <c:forEach items="${dishes}" var="dish">
                    <div class="grid_4">
                        <div class="gall_block">
                            <div class="maxheight">
                                <img src="${dish.DPicture}" alt="" width="368" height="265"></a>

                                <div class="gall_bot">
                                    <div class="text1"><a href="#">${dish.DName}</a></div>
                                        ${dish.DComposition}<br><br>
                                        ${dish.DPrice} р. | ${dish.DMass} гр.<br>
                                </div>
                            </div>
                        </div>
                        <div class="clear sep__1"></div>
                    </div>


                </c:forEach>

            </div>
        </div>
    </section>

</t:template>
