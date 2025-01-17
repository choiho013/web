<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정. -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>JSP2</title>
        
        
        <!-- Bootstrap CSS 링크. -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
        rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
        crossorigin="anonymous">
	</head>
	<body>
		<div class="container-fluid">
            <c:set value="회원 가입" var="pageTitle"/>
            <%@ include file="../fragments/header.jspf" %>
        </div>
        
        <main class="m-2">
            <div class="card">
                <div class="card-body">
                    <form method="post" >
                        <div class="mt-2">
                            <label class="form-label" for="username">아이디</label>
                            <input class="form-control" id="username" type="text" name="username" required autofocus/>
                        </div>
                        <div class="mt-2">
                            <label class="form-label" for="password">비밀번호</label>
                            <input class="form-control" id="password" type="password" name="password" required/>
                        </div>
                        <div class="mt-2">
                            <label class="form-label" for="email">이메일</label>
                            <input class="form-control" id="email" type="email" name="email" required/>
                        </div>
                        <div class="mt-2">
                            <input class="form-control btn btn-outline-success" 
                                    type="submit" value="작성완료" />
                        </div>
                        
                    </form>
                </div>
            </div>
        </main>
        
        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>   
        
        <c:url value="/static/js/post_modify.js" var="postModifyJS"/>
        <script src="${ postModifyJS }"></script>
	</body>
</html>