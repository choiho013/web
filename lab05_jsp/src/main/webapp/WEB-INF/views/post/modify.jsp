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
            <c:set value="포스트 수정하기" var="pageTitle" />
            <%@ include file="../fragments/header.jspf" %>
            
            
            <main class="mt-2"> <!-- mt, mb, ms(start-left), me(end-right) -->
                <div class="card">
                    <div class="card-header">
                        <h1>글 수정하기</h1>
                    </div>
                    <div class="card-body">
                        <form id="modifyForm">
                            <div class="mt-2 d-none">
                                <label class="form-label" for="id">번호</label>
                                <input class="form-control" id="id" 
                                       name="id" type="text" value="${ post.id }" readonly/> <!-- 읽기만 가능한 -->
                            </div>
                            <div class="mt-2">
                                <label class="form-label" for="title">제목</label>
                                <input class="form-control" id="title" 
                                       name="title" type="text" value="${ post.title }" autofocus/> 
                            </div>                      
                            <div class="mt-2">
                                <label class="form-label" for="content">내용</label>
                                <textarea class="form-control" 
                                        name="content" rows="5" id="content" >${ post.content }</textarea>
                                <!-- input은 values에 값을 넣지만 textarea는 사이에 넣어주면됨 
                                 id는 엘리먼트를 찾아야하기 때문 name은 submit으로 넘기기 위해 class는 부트스트랩-->
                            </div> 
                            <div class="mt-2 d-none">
                                <label class="form-label" for="author">작성자</label>
                                <input class="form-control" id="author" type="text" value="${ post.author }" readonly/>
                            </div>    
                        </form>
                    </div>
                    
                    <%-- 로그인한 사용자에 따라서 삭제와 업데이트가 보여야하거나 보이지 않아야 할 경우 --%>
                    <c:if test="${post.author eq signedInUser}">
                        <div class="card-footer">
                            <div class="d-flex justify-content-center">
                                <!-- form안에 있다면 submit이고 바깥이라면 form하고 상관이 없기 때문에 기능을 줘야함 -->
                                <button class="btn btn-outline-danger me-2" id="btnDelete">삭제</button>
                                <button class="btn btn-outline-success" id="btnUpdate">업데이트</button>
                            </div>
                        </div>
                    </c:if>
                </div>
            </main>
        </div>
        
        
        
        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>   
        
        <%-- 정적 리소스(CSS, JS, 이미지, 동영상, ...) 들은 WEB_INF 폴더 밑에 저장하면 안됨. --%>
        <%-- src/main/webapp/static/js/post_modify.js 파일을 포함. --%>
        <c:url value="/static/js/post_modify.js" var="postModifyJS"/>
        <script src="${ postModifyJS }"></script>
        
        <%-- web-inf안에 파일은 클라이언트가 응답으로 볼 수 있었다 서블릿이 포워드를 해줘서 
          클라이언트가 접근못하는 폴더는 web-inf 
          요청은 서블릿이 처리하기 때문에 서블릿은 web-inf에 포워드를 해서 jsp를 찾는것이고 
          jsp는 html을 만들고 응답으로 내보내는 구조
          
          와스안에있는 프로세스는 바로 web-inf에 접근할 수 있지만 클라이언트는 직접 요청을 불가능하다.
          서버나 보안관련된것은 web-inf에 넣어뒀다 
          그런데 html에 img에 있다면 최초 요청이 있고 html 태그에 작성된 문서가 응답으로 가고 브라우저는
          이미지가 있다면 이미지를 다시 요청하게된다 만약 web-inf에 있다면 접근할 수 없기 때문에 not found 404
          정적인 리소스들은 webapp밑에 넣어둬야한다. 자바스크립트도 마찮가지로 webapp 
          
          이미지나 css js 등 그냥 webapp 밑에 넣어두면 되고 
          굳이 static폴더를 만든 이유가 정적인 리소스라는 의미..
          
          c url은 컨택스트 패스를 붙혀서 들어가는 역할을 하고 있다.
          컨텍스트패스 정보 까지를 붙혀서 src에 넣어주기 때문에 c:url을 적여줘야함! 안전하게 만들어 주기 때문--%>
	</body>
</html>