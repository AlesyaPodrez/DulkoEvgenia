<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
  Created by IntelliJ IDEA.
  User: Евгения
  Date: 09.05.2015
  Time: 18:35
--%>

<t:template>
    <!--=====================
    Content
    ======================-->
    <section class="content ctn__1">
        <div class="container">
            <div class="row">
                <div class="grid_12">
                    <h2>Как добраться</h2>

                    <div class="map">
                        <div class="row">
                            <div class="grid_9">
                                <figure class="">
                                    <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d24214.807650104907!2d-73.94846048422478!3d40.65521573400813!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2sus!4v1395650655094"
                                            style="border:0"></iframe>
                                </figure>
                            </div>
                            <div class="grid_3">
                                <div class="map_block">
                                    <div class="map_title">Адрес:</div>
                                    9870 St Vincent Place, <br> Glasgow, DC 45 Fr 45.
                                </div>
                                <div class="map_block">
                                    <div class="map_title">Телефоны:</div>
                                    +1 800 559 6580 <br>+1 800 603 6035
                                </div>
                                <div class="map_block">
                                    <div class="map_title">Email:</div>
                                    <a href="#">mail@demolink.org</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="grid_8">
                    <p>24/7 support is on for all <span class="color1 fw">.
                        <br>
                        Guys from <span class="color1 fw"><a href="http://www.templatetuning.com/" rel="nofollow">Template
                            Tuning</a></span> are always ready to help you with customization of the chosen theme.</p>
                    Kas facilisis, nulla vel viverra auctor, leo magna sodales felis, quis malesuada nibh odio ut
                    velitoin pharetra luctus diama scelerisque eros convallis accumsan.
                </div>
            </div>
        </div>
    </section>
    <%--<div class="form_block">--%>
        <%--<div class="container">--%>
            <%--<div class="row">--%>
                <%--<div class="grid_11">--%>
                    <%--<h2>Форма для связи</h2>--%>

                    <%--<form id="form">--%>
                        <%--<div class="form_spinner">--%>
                            <%--<img src="images/Preloader_4.gif" alt="">--%>
                        <%--</div>--%>
                        <%--<div class="modal fade response-message">--%>
                            <%--<div class="modal-dialog">--%>
                                <%--<div class="modal-content">--%>
                                    <%--<div class="modal-header">--%>
                                        <%--<button type="button" class="close" data-dismiss="modal"--%>
                                                <%--aria-hidden="true">&times;</button>--%>
                                        <%--<h4 class="modal-title">Modal title</h4>--%>
                                    <%--</div>--%>
                                    <%--<div class="modal-body">--%>
                                        <%--asfasfasf--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="response-error-message"></div>--%>
                        <%--<label class="name">--%>
                            <%--<input type="text" name="name" placeholder="Имя:" value=""--%>
                                   <%--data-constraints="@Required @JustLetters"/>--%>
                            <%--<span class="empty-message">*Это обязательное поле.</span>--%>
                            <%--<span class="error-message">*This is not a valid name.</span>--%>
                        <%--</label>--%>
                        <%--<label class="email">--%>
                            <%--<input type="text" name="email" placeholder="E-mail:" value=""--%>
                                   <%--data-constraints="@Required @Email"/>--%>
                            <%--<span class="empty-message">*Это обязательное поле.</span>--%>
                            <%--<span class="error-message">*This is not a valid email.</span>--%>
                        <%--</label>--%>
                        <%--<label class="phone">--%>
                            <%--<input type="text" name="phone" placeholder="Телефон:" value=""--%>
                                   <%--data-constraints="@Required @JustNumbers"/>--%>
                            <%--<span class="empty-message">*Это обязательное поле.</span>--%>
                            <%--<span class="error-message">*This is not a valid phone.</span>--%>
                        <%--</label>--%>
                        <%--<label class="message">--%>
                            <%--<textarea name="message" placeholder="Message:"--%>
                                      <%--data-constraints='@Required @Length(min=20,max=999999)'></textarea>--%>
                            <%--<span class="empty-message">*Это обязательное поле.</span>--%>
                            <%--<span class="error-message">*The message is too short.</span>--%>
                        <%--</label>--%>

                        <%--<div class="clear"></div>--%>
                        <%--<div class="btns">--%>
                            <%--<a href="#" data-type="reset" class="btn">очистить</a>--%>
                            <%--<a href="#" data-type="submit" class="btn">отправить</a>--%>
                        <%--</div>--%>
                    <%--</form>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
</t:template>
