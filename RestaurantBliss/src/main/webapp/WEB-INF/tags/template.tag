<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
    <html lang="en">
    <head>
        <title>Bliss</title>
        <meta charset="utf-8">
        <meta name = "format-detection" content = "telephone=no" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/stuck.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/search.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/menu.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/touchTouch.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/table.css">
        <script src="${pageContext.request.contextPath}/res/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/res/js/jquery-migrate-1.1.1.js"></script>
        <script src="${pageContext.request.contextPath}/res/js/script.js"></script>
        <script src="${pageContext.request.contextPath}/res/js/superfish.js"></script>
        <script src="${pageContext.request.contextPath}/res/js/jquery.equalheights.js"></script>
        <script src="${pageContext.request.contextPath}/res/js/jquery.mobilemenu.js"></script>
        <script src="${pageContext.request.contextPath}/res/js/jquery.easing.1.3.js"></script>
        <script src="${pageContext.request.contextPath}/res/js/tmStickUp.js"></script>
        <script src="${pageContext.request.contextPath}/res/js/jquery.ui.totop.js"></script>
        <script src="${pageContext.request.contextPath}/res/js/touchTouch.jquery.js"></script>

    </head>

    <body class="page1" id="top">
    <!--==============================
                  header
    =================================-->
    <header>
        <!--==============================
                    Stuck menu
        =================================-->
        <section id="stuck_container">
            <div class="container">
                <div class="row">
                    <div class="grid_12">
                        <h1>
                            <a href="${pageContext.request.contextPath}/">
                                <img src="${pageContext.request.contextPath}/res/images/logo.png" alt="Logo alt">
                            </a>
                        </h1><div class="navigation" class="login-link-container">
                        <nav>
                            <ul class="sf-menu1">
                                <sec:authorize access="isAnonymous()">
                                        <a href="/spring_security_login">войти  </a>
                                </sec:authorize>
                                <sec:authorize access="isAnonymous()">
                                        <a href="${pageContext.request.contextPath}/toRegistration">  зарегистрироваться</a>
                                </sec:authorize>
                                <sec:authorize access="isAuthenticated()">
                                    <a href = "/j_spring_security_logout">выйти</a>
                                </sec:authorize>
                            </ul>
                        </nav>
                            <nav>
                                <ul class="sf-menu">
                                    <li><a href="${pageContext.request.contextPath}/">главная</a></li>
                                    <li><a href="${pageContext.request.contextPath}/menu">меню</a></li>
                                    <li><a href="${pageContext.request.contextPath}/reservation">резервация</a></li>
                                    <li><a href="${pageContext.request.contextPath}/order">заказ на дом</a></li>
                                    <sec:authorize access="isAuthenticated()">
                                        <li><a href="${pageContext.request.contextPath}/myoffice">мой кабинет</a></li>
                                    </sec:authorize>
                                    <sec:authorize access="hasRole('admin')">
                                    <li><a href="${pageContext.request.contextPath}/adminMenu">админ</a></li>
                                    </sec:authorize>
                                </ul>
                            </nav>
                            <div class="clear"></div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </header>
    <!--=====================
          Content
======================-->
    <section class="content">
    <jsp:doBody/>
    </section>
    <!--==============================
                  footer
    =================================-->
    <footer id="footer">
        <div class="container">
            <div class="row">
                <div class="grid_12">
                    <div class="copyright"><span class="brand">Bliss </span> &copy; <span id="copyright-year"></span> | <a href="#">Privacy Policy</a> <div>Course project of Evgenia Dulko</div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    </body>
    </html>