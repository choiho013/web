<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정. -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Spring2</title>
        
        
        <!-- Bootstrap CSS 링크. -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
        rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
        crossorigin="anonymous">
	</head>
	<body>
		<div class="container-fluid">
            <c:set value="포스트 수정하기" var="pageTitle"/>
            <%@ include file="../fragments/header.jspf" %>
            
            <main>
                <div class="card mt-2">
                    <div class="card-header">
                        <h1>${post.id} ${post.title}</h1>
                    </div>
                    <div class="card-body">
                        <form id="modifyForm">
                            <div class="d-none">
                                <label class="form-label" for="id">번호</label>
                                <input class="form-control" id="id" name="id" 
                                   value="${post.id}" type="text" readonly/>
                            </div>
                            <div class="mt-2">
                                <label class="form-label" for="title">제목</label>
                                <input class="form-control" id="title" name="title" 
                                   value="${post.title}" type="text" required/>
                            </div>
                            <div class="mt-2">
                                <label class="form-label" for="content">내용</label>
                                <textarea rows="5" class="form-control" id="content" 
                                     name="content" >${post.content}"</textarea>
                            </div>
                        </form>
                    </div>
                    
                    <%-- 로그인 사용자와 포스트 작성자가 같은 경우에만 삭제/업데이트 가능 
                        주소를 아는 사람이 있다면 강제로 접근할 수 있기 때문에 조건문을 준다.--%>
                    <c:if test="${signedInUser eq post.author}">
                        <div class="card-footer d-flex justify-content-end">
                            <button id="btnDelete" class="me-2 btn btn-outline-danger">삭제</button>
                            <button id="btnUpdate" class="btn btn-outline-success">업데이트</button>
                        </div>
                    </c:if>
                </div>
            </main>
        </div>
        
        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>   
        
        <%-- 자바스크립트 요청 주소 /spring2/js/post-modify.js
            Servlet-context.xml 설정에 의해서 static 폴더 아래에서 js폴더를  찾음. --%> 
        <c:url var="postModifyJS" value="/js/post-modify.js" />
        <script src="${postModifyJS}"></script>
	</body>
</html>