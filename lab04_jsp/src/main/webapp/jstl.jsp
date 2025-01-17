<%@ page import="com.itwill.jsp1.model.Contact"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!--prefix는 고칠수 있는 부분 uri는 JSTL의 태그들의 정의가 된 스펙의 이름 자동완성으로 찾으면 됨. 
core 기능이 반복문, 조건문, 변수 ... 가장 기본적인 자바 문법들을 태그로 가지고 있는게 tags.core
jsp가 자체적으로 가지고 있었던 액션태그 종류가 얼마 되지않아서 스크립플릿으로 태그 만들기가 힘들었음 -->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>JSTL</title>
    </head>
    <body>
        <%@ include file="header.jspf"%>
        <%-- 파일을 끼워 넣는것 --%>

        <main>
            <h1>JSTL(JSP/Jakarta Standard Tag Library)</h1>
            <%-- JSTL 사용하기:
                 1. pom.xml 파일에 의존성(dependency)을 추가.
                    o. jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:version
                    o. org.glassfish.web:jakarta.servlet.jsp.jstl:version
                 2. JSTL을 사용하는 jsp 파일에서 지시문 <%@ taglib prefix="" url="" %>을 작성.
                 --%>

            <%
                 String[] sns = { "인*", "얼굴책", "X", "싸이월드" };
                 pageContext.setAttribute("sites", sns);
                 %>

            <h2>scriptlet, expression 사용한 리스트</h2>
            <ul>
    
                <% for(String s : sns) { %>
                <li><%= s %></li>
                <% } %>
            </ul>

            <h2>JSTL, EL 사용한 리스트</h2>
            <ul>
                <c:forEach var="s" items="${ sites }">
                    <%-- pageContext에 저장된 값 sites, EL을 사용 --%>
                    <li>${ s }</li>
                </c:forEach>
            </ul>
            <%
             // 테이블을 작성하기 위한 더미 데이터.
             ArrayList<Contact> contacts = new ArrayList<>();
             for (int i = 0; i < 5; i++){
               Contact c = new Contact(i, "이름-" + i, "전화번호-" + i, "이메일-" + i);
               contacts.add(c);
             }
             
             // EL에서 리스트를 사용할 수 있도록 contacts를 pageContext에 저장.
             // 자바 map<K,V> 와 비슷한 문법 PUT,GET이 아닌 SET, GET 이라고 생각하면 됨
             pageContext.setAttribute("contactList", contacts);
             %>

            <h2>scriptlet, expression 사용한 테이블 작성</h2>
            <table>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>이름</th>
                        <th>전화번호</th>
                        <th>이메일</th>
                    </tr>
                </thead>
                <tbody>
                <%for (Contact c : contacts) { %>
                    <tr>
                        <!-- private이기 때문에 그것을 호출하는 getter메서드 -->
                        <td><%= c.getId() %></td>
                        <td><%= c.getName() %></td>
                        <td><%= c.getPhone() %></td>
                        <td><%= c.getEmail() %></td>
                    </tr>
                <% } %>
                </tbody>
            </table>
            <h2>JSTL, EL 사용한 테이블 작성</h2>
            <table>
                <thead>
                    <%-- ${ contactList }는 <%= pageContext.getAttribute("contactList")%>와 동일
                    var는 변수 선언  --%>
                    <c:forEach var="s" items="${contactList}">
                        <tr>
                            <%-- EL은 프로퍼티 이름으로 getter 메서드를 찾음. 
                                ${c.id}는 <%= c.getId %> 와 동일.
                            --%>
                            <td>${ s.id }</td> 
                            <td>${ s.name }</td>
                            <td>${ s.phone }</td>
                            <td>${ s.email }</td>
                        </tr>
                    </c:forEach>
                </thead>
            </table>
            
            <h2>JSTL URL 태그</h2>
            <div>
                <a href="click_result.jsp?username=gu&est&color=crimson">클릭1</a>
            </div>
            <%-- URL에서 &는 요청자 파라미터를 구분하기 위한 구분자로 사용
                '&'를 포함하는 문자열을 요청 파라미터 값으로 전달하기 위해서는
                '&' 문자에 해당하는 UTF-8 코드값을 URL에 인코딩해야 함. 
                쿼리스트링 특수기호는 퍼센트숫자 16진수로 사용이됨.
                url인코딩해서 넣어주면 서버에서 퍼센트 얼마 숫자를 보고 해당하는 문자를 찾음.
            --%>
            
            <div>
                <c:url value="click_result.jsp" var="resultPage" >
                    <%-- 요청 파라미터의 이름과 값을 설정. --%>
                    <c:param name = "username" value="admin"/>
                    <c:param name="color" value="DodgerBlue" />
                </c:url>    
                <a href=" ${resultPage}">클릭2</a>
            </div>
        </main> 
    </body>
</html>