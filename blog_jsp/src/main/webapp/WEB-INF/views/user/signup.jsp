<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정. -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Blog_jsp : 회원가입</title>
        <link rel="stylesheet" href="../static/css/user_signup.css">
        
        <!-- Bootstrap CSS 링크. -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
        rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
        crossorigin="anonymous">
    </head>
    <body>
        <div>
<%--             <%@ include file="../fragments/header.jspf" %> --%>
            
            <main class="mt-2 container-fluid">
                <div class="form_content">
                    <div class="form_section">
                        <div class="form_logo">
                            <c:url value="/" var="homePage"/>
                            <a href="${homePage}">
                                <img  id="jspLogo" alt="LOGO_JSP" src="../static/image/sign2_jsp.png">
                            </a>
                        </div>
                        <form id="userSignUpForm">
                            <div class="form_list">
                                <div class="form_user">
                                    <input class="form_itme" id="username" name="username" type="text" placeholder="아이디" autofocus />
                                </div>
                                <div class="form_password">
                                    <input class="form_itme" id="password" name="password" type="password" placeholder="비밀번호" />
                                </div>
                                <div class="form_email me-3" style="display: flex; align-items: center;">

                                    <input class="form_itme" id="email" name="email" type="email" placeholder="이메일" />

                                    <input class="form_itme" value="@itwill.com" id="emaildomain" name="emaildomain" readonly/>
                                </div>
                            </div>
                            <div class="form_list">
                                <div class="form_name">
                                    <input class="form_itme" id="name" name="name" type="text" placeholder="이름" />
                                </div>
                                <div class="form_birthday">
                                    <input class="form_itme" id="birthday" name="birthday" type="text" placeholder="생년월일 8자리" />
                                </div>
                                <div class="form_phone">
                                    <input class="form_itme" id="phone" name="phone" type="text" placeholder="핸드폰번호" />
                                </div>
                            </div>
                                <div class="error_text" id="birthdayMsg" 
                                    style="color: red; display: none;">
                                </div>
                                <div class="error_text" id="phoneMsg" 
                                    style="color: red; display: none;">
                                </div>
                            <div>
                                <div class="form_btn">
                                    <button class="btn btn-outline-success" id="btnInsert">작성완료</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </main>
        </div>
        
        
        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>   
        
        <c:url value="/static/js/user_signup.js" var="userSignUpJS" />   
        <script src="${userSignUpJS}"></script>  
    </body>
</html>