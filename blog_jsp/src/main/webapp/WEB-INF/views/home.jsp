<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정. -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Blog_Jsp</title>
        <link rel="stylesheet" href="./static/css/home.css">
        
        <!-- Bootstrap CSS 링크. -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
        rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
        crossorigin="anonymous">

    </head>
    <body>
    <div>
        <%@ include file="./fragments/header.jspf"%>
        <%@ include file="./fragments/pointMessage.jspf" %>
        <main>
            <div id="header"></div>

            <div class="ret">
                <div>
                    <a href="https://github.com/" target="_blank">
                        <img class="github" src="./static/image/github.png"/>
                    </a>
                </div>
                <div class="mt-2">
                    <a href="https://www.instagram.com/" target="_blank">
                        <img class="InstagramIG" src="./static/image/InstagramIG.png"/>
                    </a>
                </div>
            </div>
            <!-- 메인 이미지 -->
            <div>

                <section>
                    <div id="headerimg">

                        <img id="wallpaper" alt="test"
                            src="./static/image/main_wallpaper.jpg">
                    </div>
                </section>
            </div>
            <div class="middle"></div>
             
            <!-- 카드형 이미지 -->
            <div id="homebody">
                <section>
                    <div class="card-container">
                    <!-- 파란색 라인 & 텍스트 -->
                        <div class="lines">
                            <div class="lines-text">
                                <span>Our image</span>
                                <p>Some quick
                                    example text to build on the card
                                    title and make up the bulk of the
                                    card's content.</p>
                            </div>
                        </div>
                        <div class="card">
                            <img src="./static/image/hc1.jpg" class="img" >
                            <div class="card-body">
                                <p class="card-text">Yega Wirye
                                    Multipurpose Sports Center - Perspective View.</p>
                            </div>
                        </div>
                        <div class="card">
                            <img src="./static/image/hc2.jpg" class="img" >
                            <div class="card-body">
                                <p class="card-text">ANU CJ Gayang-dong
                                <br>Development Plan - Perspective View.
                                </p>
                            </div>
                        </div>
                        <div class="card" >
                            <img src="./static/image/hc3.jpg" class="img">
                            <div class="card-body">
                                <p class="card-text">Archifactory Architecture
                                Multipurpose Public Office - Perspective View.</p>
                            </div>
                        </div>
                        <div class="card">
                            <img src="./static/image/hc4.jpg" class="img" >
                            <div class="card-body">
                                <p class="card-text">
                                Public Health Center New Building Design - Perspective View.</p>
                            </div>
                        </div>
                        <div class="card">
                            <img src="./static/image/hc5.jpg" class="img" >
                            <div class="card-body">
                                <p class="card-text">
                                   Geoje Municipal Jangseungpo Library Detailed Design - Perspective View.</p>
                            </div>
                        </div>
                        <div class="card">
                            <img src="./static/image/hc6.jpg" class="img" >
                            <div class="card-body">
                                <p class="card-text">Seongnae Samsung Apartment - Perspective View.</p>
                            </div>
                        </div>
                        <div class="card">
                            <img src="./static/image/hc7.jpg" class="img"  >
                            <div class="card-body">
                                <p class="card-text"> 
                                Majang Serim Apartment Partial Bird's-eye View.</p>
                            </div>
                        </div>

                        <div class="card">
                            <img src="./static/image/hc8.jpg" class="img" >
                            <div class="card-body">
                                <p class="card-text">ORWS Serenity Golf and Resort
                                <br>Interior Design.</p>
                            </div>
                        </div>
                        <div class="card">
                            <img src="./static/image/hc9.jpg" class="img" >
                            <div class="card-body">
                                <p class="card-text">Grand Hotel Architectural Design Proposal 
                                - Perspective View.</p>
                            </div>
                        </div>
                        <div class="card">
                            <img src="./static/image/hc10.jpg" class="img" >
                            <div class="card-body">
                                <p class="card-text">Chuncheon-si Dong-myeon Mancheolli Development - Perspective View.</p>
                            </div>
                        </div>
                        <!-- 이미지 모달 -->
                        <div class="modal" id="cardModal" tabindex="-1">
                            <span class="close">&times;</span>
                            <img class="modalcontent" >
                            </div>
                        </div>
                </section>
            </div>
            

            <section>
                <div class="footer"></div>
                    <div class="footer-container">
                        <div>
                            
                        </div>
                    </div>
            </section>


            </main>
        </div>
    <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>
        
        <c:url var="homeJS" value="static/js/home.js"/>
        <script src="${homeJS}"></script>
    </body>
</html>