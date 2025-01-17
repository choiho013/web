<%@ page import="com.itwill.jsp1.model.Contact"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Action Tag</title>
        <style>
            div.card{
                border: 1px solid gray;
                border-radius: 8px;
                margin: 8px;
                padding: 8px;
            }
        </style>
    </head>
    <body>
        <%@ include file ="header.jspf" %>
    <main>
        <H1> JSP Action Tag </H1>
        <%--
        JSP Action Tag: scriptlet에서 사용되는 일부 자바 코드들을 HTML 또는 XML과
        비슷하게 태그, 태그 속성(attribute), 태그 컨텐트로 작성해서 대체하기 위한 문법.
        액션 태그는 대/소문자를 구분! (HTML 태그는 대/소문자를 구분하지 않음.)
        액션 태그는 시작 태그와 종료 태그가 반드시 있어야 함.
          o. <jsp:forward>: request.getRequestDispatcher("").forward(req, resp)
          o. <jsp:include>: <%@ include file="" %> 비슷.
          o. <jsp:useBean>: (기본) 생성자 호출.
          o. <jsp:getProperty>: getter 메서드 호출.
          o. <jsp:setProperty>: setter 메서드 호출.
         --%>
         
         <h2> 액션 태그를 사용하지 않은 자바 객체 생성 </h2>
         <% 
         Contact contact1 = new Contact();
         // setter 메서드 호출
         contact1.setId(1);
         contact1.setName("홍길동");
         contact1.setPhone("010-1234-5678");
         contact1.setEmail("hgd@test.com");
         %>
         <div class="card">
            ID: <%= contact1.getId() %> <br/>
            Name: <%= contact1.getName() %> <br/>
            Phone: <%= contact1.getPhone() %> <br/>
            Email: <%= contact1.getEmail() %> 
         </div>
         
         <h2> 액션 태그 자바 빈을 사용한 객체 생성 </h2>
         <jsp:useBean id="contact2" class="com.itwill.jsp1.model.Contact"></jsp:useBean>
         <%-- Contact contact2 = new Contact();과 같은 기능.
           <jsp:useBean>은 기본 생성자를 가지고 있는 클래스만 사용할 수 있음. 
           아규먼트를 가지고있는 생성자를 불러주는 기능이 없음.
           빈 = 객체 . 빈을 이용하겠다라는 의미
         --%>
         
         <%-- contact.setId();와 같은 기능 --%>
         <jsp:setProperty name="contact2" property="id" value="2" />
         <%-- contact2.setId(2); 와 같은 기능.
            name 속성: (useBean에서 설정한)자바 빈의 아이디.
            property 속성: 프로퍼티 이름. 클래스의 필드 (변수) 이름.
            value 속성: 프로퍼티에 설정할 값.
          객체의 이름, 객체가 갖고있는 필드, 그것의 값을 set 하겠다 --%>
         <jsp:setProperty name="contact2" property="name" value="오쌤" />
         <jsp:setProperty name="contact2" property="phone" value="010-0000-0000" />
         <jsp:setProperty name="contact2" property="email" value= "jake@itwill.com" />
         <div class = "card">
            <%-- contact2.getId();과 같은 기능 --%>
            ID: <jsp:getProperty property="id" name="contact2"/> <br/>
            이름: <jsp:getProperty property="name" name="contact2"/> <br/>
            전화번호: <jsp:getProperty property="phone" name="contact2"/> <br/>
            이메일: <jsp:getProperty property="email" name="contact2"/>
         </div>
         
        
    </main>
    
   <%--  <%@ include file = "footer.jsp" %> --%>
   <jsp:include page="footer.jsp" />
   <%--
     o. <%@ include file="" %>는 하나의 JSP 파일로 합쳐진 후에,
        하나의 java 파일로 변환되고 컴파일 됨.
     o. <jsp:include page="">는 각각의 JSP 파일들이 java 파일로 변환되고 컴파일 된 후에,
        하나의 HTML 문서가 작성됨.
    --%>
</body>
</html>