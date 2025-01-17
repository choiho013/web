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
        <link rel="stylesheet" href="../static/css/post_list.css">
        
        <!-- Bootstrap CSS 링크. -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
        rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
        crossorigin="anonymous">
    </head>
    <body>
        <div>
            <%@ include file="../fragments/header.jspf" %>
            <%@ include file="../fragments/pointMessage.jspf" %>
        </div>
        
        <main class="container-fluid mt-5">
        <div class="card">
            <div class="card-header mt-3">
                <div class="form_search d-flex align-items-center justify-content-end">
                <c:url value="/post/search" var="postSearchPage"/>
                    <form action="${postSearchPage}" method="get">
                        <div class="row">
                            <div class="col-3">
                                <select class="form-select" name="category">
                                    <option ${param.category == "t" ? "selected" : ""} value="t">제목</option>
                                    <option ${param.category == "c" ? "selected" : ""} value="c">내용</option>
                                    <option ${param.category == "tc" ? "selected" : ""} value="tc">제목+내용</option>
                                    <option ${param.category == "a" ? "selected" : ""} value="a">작성자</option>
                                </select>
                            </div>
                            <div class=col-7>
                                <input class="form-control" type="text"
                                    name="keyword" placeholder="검색어" value="${param.keyword}" required />
                            </div>
                            <div class=col-2>
                                <input class="form-control btn btn-outline-secondary" 
                                    type="submit" value="검색"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="mt-2">
                <table class="table table-hover">
                    <thead class="text-center">
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>수정시간</th>
                        </tr>
                    </thead>
                    <tbody class="text-center">
                        <c:forEach items="${posts}" var="p">
                            <tr>
                                <td>${p.id}</td>
                                <c:url value="/post/details"
                                    var="postDetailsPage">
                                    <c:param name="id" value="${p.id}" />
                                </c:url>
                                <td><a href="${postDetailsPage}">${p.title}</a>
                                </td>
                                <td>${p.author}</td>
                                <td>${p.formattedModifiedTime}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="card-footer">
                    <nav aria-label="Page navigation example" class="pagination-container">
                        <ul class="pagination">
                        <c:set var="page" value="${param.p != null ? param.p : 1}"/>
                        <c:set var="startNum" value="${page-(page-1)%5}"/>
                        <c:set var="lastNum" value="8"/>
                            
                            <!-- 이전 페이지 -->
                            <li class="page-item">
                                
                                <c:if test="${startNum>1}">
                                <a class="page-link" href="?category=${param.category}&keyword=${param.keyword}&p=${startNum-1}" 
                                    aria-label="Previous">&laquo;
                                </a>
                                </c:if>
                                <c:if test="${startNum<=1}">
                                    <span class="page-link" aria-hidden="true" onclick="alert('다음 페이지가 없습니다.')">&laquo;</span>
                                </c:if>
                            </li>
                            
                            <!-- 페이지 번호 -->
                            <c:forEach var="i" begin="0" end="4">
                            <li class="page-item">
                                <a class="page-link ${param.p == (startNum + i) ? 'active' : ''}"  href="?category=${param.category}&keyword=${param.keyword}&p=${startNum + i}">
                                ${startNum + i}
                                </a>
                            </li>
                            </c:forEach>
                            
                            <!-- 다음 페이지 -->
                            <li class="page-item">
                                <c:if test="${startNum+5<lastNum}">
                                    <a class="page-link" href="?category=${param.category}&keyword=${param.keyword}&p=${startNum+5}" 
                                        aria-label="Next">&raquo;
                                    </a>
                                </c:if>
                                
                                <c:if test="${startNum+5>=lastNum}">
                                    <span class="page-link" aria-hidden="true" onclick="alert('다음 페이지가 없습니다.')">&raquo;</span>
                                </c:if>
                                
                            </li>
                        </ul>
                    </nav>
                    <c:set var="totalPages" value="10" />
                    <div>
                        <div class="indexer">
                             <span class="page-list">${(empty param.p) ? 1 : param.p}</span> / ${totalPages} pages
                        </div>
                    </div>
            </div>
        </div>
    </main>
        
        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>   
    </body>
</html>