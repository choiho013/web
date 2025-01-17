<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
    <head>
       <meta charset="UTF-8">
       <title>JSP</title>
	</head>
	<body>
 	 	<%@ include file="header.jspf" %>
        
        <main>
            <h1>폼 양식</h1>
            
                <%-- 메서드는 방법 get인지 post , action은 
                사용자로부터 입력값을 받아서 서버로 양식의 데이터(doget, dopost)를 전송할 때 
                --%>
            <form method="get" action="form_result.jsp">
                <div>
                    <input type="text" name="username" placeholder="사용자 이름 입력"
                        required autofocus />
                </div>
                <div>
                    <select name="color">
                        <option value="r">빨강</option>
                        <option value="b">파랑</option>
                        <option value="g">초록</option>
                    </select>
                </div>
                <div>
                    <input type="submit" value="제출"/>
                </div>
            </form>
        </main>
	</body>
</html>