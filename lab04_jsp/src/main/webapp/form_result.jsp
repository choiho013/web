<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
    <head>
       <meta charset="UTF-8">
       <title>JSP</title>
       <style>
            <% 
            // 요청 파라미터 color의 값을 찾음
            String color = request.getParameter("color");
            String textColor = null;
            switch(color) {
            case "r":
                textColor = "Crimson";
                break;
            case "g":
                textColor = "MediumSeaGreen";
                break;
            case "b":
                textColor = "Dodgerblue";
                break;
                
            default :
                textColor = "black";
            }
            %>
            span#username {
                color: <%= textColor %>;
            }
       </style>
	</head>
	<body>
 	 	<%@ include file="header.jspf" %>
        
        <main>
            <h1>폼 양식 제출 결과</h1>
            
            <%
            // 클라이언트에서 전송한 요청 파라미터의 값을 찾는 방법:
            String username = request.getParameter("username"); // 요청 파라미터의 이름과 같아야함. input에 있는 name
            // pageContext, out, request 는 지역변수로 사용할 수 없음 이미 지역변수로 만들어져 있기 때문에.
            // 요청이 보낼때 마다 파라미터의 값이 달라지는 것 서버에서 이 일이 이루어져 있다는게 중요
            %>
            <h2>안녕하세요, <span id ="username"><%= username %></span>!</h2>
            
            <% if (username.equals("admin")) { %>
                <h3>관리자 페이지</h3>
            <% } else { %>
                <h3>일반 사용자 페이지</h3>
            <% } %>
        </main>
	</body>
</html>

<%--
 JSP 내장 객체: jsp 파일이 java 소스 코드로 변환될 때 만들어지는 
 _jspService(request, response) 메서드 안에서 선언된 지역 변수(와 파라미터)들.
 (주의) scriptlet 안에서 내장 객체와 같은 이름으로 지역 변수를 선언할 수 없음.
  o. request: 클라이언트에서 서버로 보내는 요청에 대한 정보와 관련 메서드들을 가지고 있는 객체.
     - getParameter(), getRequestDispatcher(), getAttributes(), setAttribute(), ...
  o. response: WAS에서 응답을 만들 때 필요한 정보와 관련 메서드들을 가지고 있는 객체.
     - setContentType(), sendRedirect(), ...
  o. out: JSPWriter, HTML 코드 작성 기능을 가지고 있는 객체.
     - write(), print(), println(), ...
  o. pageContext: JSP 페이지가 유지되는 동안 정보를 저장하기 위한 객체.
     - getAttribute(), setAttribute(), ...
  o. session: 세션이 유지되는 동안 정보를 저장하기 위한 객체. (예) 로그인 상태 유지. 
     - getAttribute(), setAttribute(), ...
  o. application: 웹 애플리케이션이 동작 중에(WAS가 종료될 때까지) 유지되는 정보를 저장하기 위한 객체. 
      -getAttribute(), setAttribute(), ...
  o. config: 서블릿 환경 설정 정보를 저장하기 위한 객체.
 내장 객체의 사용 범위:
  pageContact < request < session < application
 
 저장할때는 전부 set
 
 클라이언트에게 넘어온 정보 폼에서 보내준 요청파라미터를 읽는것
 reaust 서버에서 가져온 
 
 페이지 컨택드 정보는 그 페이지에서만
 리퀘스트는 값을 저장 할 수 있는데 두개이상의 페이지가 활용이 될 수 있는데 page보다는 넓은 범위
 다른 페이지로 포워드 됬다 하더라도 그 다른 페이지도 
 세션은 리퀘스트보다 넓은 범위 
   --%>



