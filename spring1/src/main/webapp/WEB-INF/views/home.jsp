<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        
        <!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정. -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        
        <title>Spring 1</title>
        
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
            rel="stylesheet" 
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
            crossorigin="anonymous" />
    </head>
    <body>
        <header>
            <h1>Home Page</h1>
            <h2>${now}</h2>
            <div>
                <c:url value="/images/paris.jpg" var="paris" />
                <%-- 요청주소는 http://localhost:8080/spring1/images/paris.jpg 
                  servlet-context.xml <mvc:resources> 태그 설정에 의해서
                  webapp/static/images/paris.jpg 파일을 응답받게 됨.
                --%>
                <img alt="파리" src="${paris}" />
            </div>
        </header>
        
        <main>
            <h1>목차</h1>
            <nav>
                <ul>
                    <li>
                        <c:url value="/example" var="examplePage" />
                        <a href="${examplePage}">컨트롤러 예제</a>
                    </li>
                    <li>
                        <c:url value="/test" var="testPage"/>
                        <a href="${testPage}">테스트 페이지</a>
                    </li>
                    <li>
                        <c:url value="/test2" var="forwardPage" />
                        <a href="${forwardPage}">포워드</a>
                    </li>
                    <li>
                        <c:url value="/test3" var="redirectPage" />
                        <a href="${redirectPage}">리다이렉트</a>
                    </li>
                    <li>
                        <c:url value="/rest1" var="rest1Page" />
                        <a href="${rest1Page}">REST 1</a>
                    </li>
                    <li>
                        <c:url value="/rest2" var="rest2Page" />
                        <a href="${rest2Page}">REST 2</a>
                    </li>
                    <li>
                        <c:url value="/rest3" var="rest3Page" />
                        <a href="${rest3Page}">REST Controller 3</a>
                    </li>
                    <li>
                        <c:url value="/rest4" var="rest4Page" />
                        <a href="${rest4Page}">REST Controller 4</a>
                    </li>
                </ul>
            </nav>
        </main>
        
        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
            crossorigin="anonymous"></script>
    </body>
</html>
