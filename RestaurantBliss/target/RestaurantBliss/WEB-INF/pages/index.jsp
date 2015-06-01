<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
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
    <!--=====================
    Content
    ======================-->
    <section class="content">
        <div class="container">
            <div class="row">
                <div class="grid_4">
                    <div class="banner">
                        <div class="gall_block">
                            <img src="${pageContext.request.contextPath}/res/images/page1_img1.jpg" alt="">

                            <div class="bann_capt ">
                                <div class="maxheight">
                                    <img src="${pageContext.request.contextPath}/res/images/icon1.png" alt="">

                                    <div class="bann_title">Меню</div>
                                    <a href="${pageContext.request.contextPath}/menu">Подробнее</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="grid_4">
                    <div class="banner">
                        <div class="gall_block">
                            <div class="bann_capt  bn__1">
                                <div class="maxheight">
                                    <img src="${pageContext.request.contextPath}/res/images/icon2.png" alt="">

                                    <div class="bann_title">Онлайн резервирование</div>
                                    <a href="${pageContext.request.contextPath}/reservation">Подробнее</a>
                                </div>
                            </div>
                            <img src="${pageContext.request.contextPath}/res/images/page1_img2.jpg" alt="">
                        </div>
                    </div>
                </div>
                <div class="grid_4">
                    <div class="banner ">
                        <div class="gall_block">
                            <img src="${pageContext.request.contextPath}/res/images/page1_img3.jpg" alt="">

                            <div class="bann_capt  bn__2">
                                <div class="maxheight">
                                    <img src="${pageContext.request.contextPath}/res/images/icon3.png" alt="">

                                    <div class="bann_title">Онлайн заказ</div>
                                    <a href="${pageContext.request.contextPath}/order">Подробнее</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="grid_12">
                    <h2>Блюда от шефа</h2>
                </div>
                <div class="gallery">
                    <div class="grid_4"><a href="${pageContext.request.contextPath}/res/images/big1.jpg"
                                           class="gall_item"><img
                            src="${pageContext.request.contextPath}/res/images/page1_img4.jpg" alt=""></a><a href="${pageContext.request.contextPath}/menu"
                                                                                                             class="link1">+</a>

                        <div class="clear"></div>
                    </div>
                    <div class="grid_4"><a href="${pageContext.request.contextPath}/res/images/big2.jpg"
                                           class="gall_item"><img
                            src="${pageContext.request.contextPath}/res/images/page1_img5.jpg" alt=""></a><a href="${pageContext.request.contextPath}/menu"
                                                                                                             class="link1">+</a>

                        <div class="clear"></div>
                    </div>
                    <div class="grid_4"><a href="${pageContext.request.contextPath}/res/images/big3.jpg"
                                           class="gall_item"><img
                            src="${pageContext.request.contextPath}/res/images/page1_img6.jpg" alt=""></a><a href="${pageContext.request.contextPath}/menu"
                                                                                                             class="link1">+</a>

                        <div class="clear"></div>
                    </div>
                </div>

                <div class="grid_4">
                </div>
                <div class="grid_4">
                    <h2>Время работы</h2>
                    <ul class="shed">
                        <li><span>Завтрак:</span> 8AM - 11AM</li>
                        <li><span>Бизнес ланч:</span> 12AM - 12PM</li>
                        <li><span>Живая музыка:</span> 8AM - 11AM</li>
                    </ul>
                </div>
            </div>
        </div>

    </section>

</t:template>
