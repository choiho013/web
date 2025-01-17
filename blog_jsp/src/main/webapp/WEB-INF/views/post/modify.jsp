<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"  %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정. -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        
		<title>Blog_Jsp : 게시글 수정</title>
		
		<!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
            rel="stylesheet" 
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
            crossorigin="anonymous" />
	</head>
	<body>
		<div>
            <%@ include file="../fragments/header.jspf" %>
            <%@ include file="../fragments/pointMessage.jspf" %>
            
            <main class="mt-2 container-fluid">
                <div class="card">
                    <div class="card-header">
                        <div>
                            <h2>게시글 수정</h2>
                        </div>
                    </div>
                    <div class="card-body">
                       <form id="modifyForm">
                            <div class="mt-2 d-none">
                                <label class="form-label" for="id">번호</label>
                                <input class="form-control" id="id" name="id" 
                                        type="text" value="${ post.id }" readonly/> <!-- 읽기만 가능한 -->
                            </div>
                            <div class="mt-2">
                                <label class="form-label" for="title">제목</label>
                                <input class="form-control" id="title" name="title" 
                                        type="text" value="${ post.title }" required/>
                            </div>                     
                            <div class="mt-2">
                                <label class="form-label" for="content">내용</label>
                                <textarea class="form-control" rows="5" id="content" name="content" 
                                        required >${ post.content }</textarea>
                                <!-- input은 values에 값을 넣지만 textarea는 사이에 넣어주면됨 -->
                            </div> 
                            <div class="mt-2 d-none">
                                <label class="form-label" for="author">작성자</label>
                                <input class="form-control" id="author" name="author" 
                                        type="text" value="${ post.author }" readonly/>
                            </div>
                            <div>
                                <input class="d-none" name="existingFiles" value="${post.files}">
                            </div>
                            <div class="mt-2">
                                <lable class="form-label" for="content">파일</lable>
                                <small class="text-muted ms-2">현재 파일: ${post.files}</small>
                                <input class="form-control" id="files" name="files"
                                    type="file"/>
                            </div>    
                        </form>  
                    </div>
                    
                    <div class="card-footer">
                        <div class="d-flex justify-content-center">
                            <button class="btn btn-outline-success me-2" id="btnUpdate">업데이트</button>
                            <button class="btn btn-outline-danger me-2" id="btnDelete">삭제</button>
                        </div>
                    </div>
                </div>
            </main>
        </div>
        
		
		<!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
            crossorigin="anonymous"></script>
        
        <c:url value="/static/js/post_modify.js" var="postModifyJS" />   
        <script src="${postModifyJS}"></script> 
	</body>
</html>