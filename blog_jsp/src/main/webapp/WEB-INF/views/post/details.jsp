<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        
        <!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정. -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        
        <title>Blog_Jsp : 게시글 정보</title>
        
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
                <div class="card mt-5">
                    <div class="card-header">
                        <div>
                            <h2>게시글 정보</h2>
                        </div>
                    </div>
                    <div class="card-body">
                       <form>
                            <div class="mt-2">
                                <label class="form-label" for="id">번호</label>
                                <input class="form-control" id="id" name="id"
                                    type="text" value="${ post.id }" readonly/> <!-- 읽기만 가능한 -->
                            </div>
                            <div class="mt-2">
                                <label class="form-label" for="title">제목</label>
                                <input class="form-control" id="title" name="title" 
                                    type="text" value="${ post.title }" readonly/> <!-- 읽기만 가능한 -->
                            </div>                     
                            <div class="mt-2">
                                <label class="form-label" for="content">내용</label>
                                <textarea class="form-control" rows="5" id="content" name="content"
                                     readonly > ${post.content}  </textarea>
                                <!-- input은 values에 값을 넣지만 textarea는 사이에 넣어주면됨 -->
                            </div> 
                            <div class="mt-2" >
                                <label class="form-label" for="files">파일</label>
                                <div style="text-align:left; text-indent:10px;">
                                <c:forTokens var="fileName" items="${post.files}" delims=",">

                                    <a href="${pageContext.request.contextPath}/post/details?action=download&file=${fileName}">
                                         ${fileName} 다운로드</a>
                                    <c:if test="${fileName.toLowerCase().endsWith('.jpg') 
                                         or fileName.toLowerCase().endsWith('.jpeg') 
                                         or fileName.toLowerCase().endsWith('.png') 
                                         or fileName.toLowerCase().endsWith('.gif')}" >
                                <img src="${pageContext.request.contextPath}/static/file/${fileName}" 
                                    alt="${fileName}" style="display:block;"/> 
                                    </c:if>
                                </c:forTokens>
                                </div>
                            </div> 
                            <div class="mt-2">
                                <label class="form-label" for="author">작성자</label>
                                <input class="form-control" id="author" name="author" 
                                    type="text" value="${ post.author }" readonly/>
                            </div>    
                            <div class="mt-2">
                                <label class="form-label" for="createdTime">작성시간</label>
                                <input class="form-control" id="createdTime" name="createdTime" 
                                    type="text" value="${ post.formattedCreatedTime }" readonly/>
                            </div>     
                            <div class="mt-2">
                                <label class="form-label" for="modifiedTime">최종수정시간</label>
                                <input class="form-control" id="modifiedTime" name="modifiedTime" 
                                    type="text" value="${ post.formattedModifiedTime }" readonly/>
                            </div>   
                        </form>  
                    </div>
                    
                    <c:if test="${post.author eq signedInUser}">
                        <div class="card-footer">
                            <div class="d-flex justify-content-center">
                                <c:url value="/post/modify" var="postModifyPage">
                                    <c:param name="id" value="${post.id}" />
                                </c:url>
                                <a class="btn btn-outline-success" href="${postModifyPage}">수정하기</a>
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
    </body>
</html>