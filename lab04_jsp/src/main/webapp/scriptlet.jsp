<%@ page import="com.itwill.jsp1.model.Contact"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
    <head>
       <meta charset="UTF-8">
       <title>scriptlet</title>
       <style>
        table, th, td {
            border: 1px solid gray;
            border-collapse: collapse;
        }
       </style>
	</head>
	<body>
 	 	<%@ include file="header.jspf" %>
        
        <main>
            <h1>JSP scriptlet tag</h1>
            
            <%--
                scriptlet:
                - JSP 파일 안에서 자바 코드들을 작성하기 위한 코드.
                - _jspService 메서드의 몸통(body) 안에 삽입되는 코드들.
                - 지역 변수 선언 & 초기화, 객체 생성, 메서드 호출, 조건문, 반복문, ...
             --%>
             
             <%
             // HTML 테이블을 작성할 때 필요한 더미 데이터 생성:
             ArrayList<Contact> data = new ArrayList<>(); //데이터를 저장할 빈 리스트 생성.
             for (int i = 0; i < 10; i++){
                 Contact c = new Contact(i, "이름-" + i, "전화번호-" + i, "이메일-" + i);
                 data.add(c);
             }
             %>
             
             <h2>scriptlet을 사용한 html 테이블 작성</h2>
             <table>
                <thead>
                    <tr>
                        <th>NO.</th>
                        <th>NAME</th>
                        <th>PHONE</th>
                        <th>EAMIL</th>
                    </tr>
                </thead>
                <tbody>
                <%
                for(Contact c : data){
                    out.println("<tr>");
                    out.println("<td>" + c.getId() + "</td>");
                    out.println("<td>" + c.getName() + "</td>");
                    out.println("<td>" + c.getPhone() + "</td>");
                    out.println("<td>" + c.getEmail() + "</td>");
                    out.println("</tr>");
                }
                %>
                </tbody>
             </table>
             
             <h2>scriptlet &amp; expression을 사용한 html 테이블 작성</h2>
             <table>
                <thead>
                    <tr>
                        <th>NO.</th>
                        <th>NAME</th>
                        <th>PHONE</th>
                        <th>EAMIL</th>
                    </tr>
                </thead> 
                <tbody>
                <%-- 조금더 JSP에 가깝게 반복문을 사용하는 방법 --%>
                <% for (Contact c : data) { %> 
                    <tr>
                        <td> <%= c.getId() %>  </td>
                        <td> <%= c.getName() %> </td>
                        <td> <%= c.getPhone() %> </td>
                        <td> <%= c.getEmail() %> </td>
                    </tr>
                <% } %>
                </tbody>
            </table>
                            
             <h2>scriptlet &amp; expression을 사용한 ul</h2>
             <%-- 연락처 data의 이름들을 li으로 작성 --%>
             <ul>
             <% for (Contact c : data) { %>
                    <li> <%= c.getName() %> </li>
             <% } %>
             </ul>
             <h2>scriptlet &amp; expression을 사용한 dl</h2>
             <%-- 연락처 data의 이름을 dt, 전화번호와 이메일을 dd로 작성 --%>
            <dl>
             <% for(Contact c : data) { %>
                    <dt> <%= c.getName() %></dt>
                    <dd> <%= c.getPhone() %></dd>
                    <dd> <%= c.getEmail() %></dd>
             <% } %>
            </dl>
        </main>
	</body>
</html>