package com.itwill.jsp1;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// javax로 되어있으면 문구를 바꿔줘야함
// 새로운 클래스를 만들 때에만 서버를 수동으로 재시작.

/*
 *  WAS(Web Application Server): 
 *  - 웹 요청(requset)과 응답(response)을 처리하는 프로그램. (예) Tomcat
 *  - WAS는 시작될 때 web.xml 파일을 읽어서 웹 서비스 준비(초기화)를 진행함. 
 *  web.xml 파일: 배포 설명자(deployment descriptor). 
 *  클라이언트에서 요청이 왔을 때 WAS는 web.xml에 작성된 서블릿 설정을 보고,
 *  요청 주소에 매핑된 서블릿 클래스의 doGet() 또는 doPost() 메서드를 호출함.
 *  Servlet: Server + Applet. 합성어. 서버에서 실행되는 작은 자바 프로그램. 
 *  서블릿 컨테이너(Servlet container):
 *  - 서블릿 객체를 생성/관리, 필요할 때 서블릿 객체의 메서드를 호출하는 프로그램.
 *  서블릿 설정: 서블릿 클래스와 요청 주소를 매핑 설정.
 *  (1) web.xml 파일에서 <servlet>, <servlet-mapping>으로 설정.
 *  (2) 각각의 서블릿 클래스에서 @WebServlet 애너테이션으로 설정.
 *  (주의) 하나의 서블릿 클래스는 web.xml 또는 애너테이션 중 한가지 방법으로만 설정해야 됨.
 *  
 *  서블릿 동작 원리:
 *  (1) 요청 ---> 서블릿 객체 생성 ---> doGet().doPost() 호출
 *  (2) 요청 ---> doGet()/doPost() 호출
 *  
 *  
 *  메인은 와스고 와스가 요청을 처리하기 위해서 html을 만들어야하기 때문에 doGet 호출해서 요청받은걸 넣어주고
 *  작성하는건 우리가 해야하고 그걸 서블릿이라 하는데
 *  doget에 요청을해서 html을 받아서 그것을 클라이언트에 보내주는 일
 *  web.xml 을 잘 작성해야 웹 페이지가 잘 열ㄻ
 *  와스가 서블릿 컨테이너를 갖고있기도 하고 없기도하다. 객체를 생성과 관리를 해줌.
 *  요청주소가 매핑되어져 있지 않으면 처리할 수 있는 프로그램이 아니다.
 *  
 *  
 *  response는 와스가준 객체안에 리스폰에 넣어주고 거기에 html의 문서를 클라이언트에 보내줌
 *  response는 서블릿이 응답의 내용들을 와스에게 알려주기 위해서 와스가 클라이언트에 응답해줌.
 */



/**
 * Servlet implementation class FirstServlet
 */
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // GET 방식의 요청일 때 WAS가 호출하는 메서드.
    // 파라미터 request: 클라이언트에서 서버로 보낸 요청의 정보 등을 저장하고 있는 객체.
    // 파라미터 response: 서버가 클라이언트로 보낼 응답의 데이터, 기능 등을 갖는 객체.
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	System.out.println("FirstServlet::doGET() 호출");
    	
    	response.setContentType("text/html; charset=UTF-8");
    	
    	PrintWriter writer = response.getWriter();
    	writer.append("<!doctype html>")
    			.append("<html>")
    			.append("    <head>")
    			.append("         <meta charset='UTF-8' />")
    			.append("         <title>Servlet</title>")
    			.append("    </head>")
    			.append("    <body>")
    			.append("       <h1>첫번째 서블릿</h1>")
    			.append("		<a href='/jsp1/'>목차</a>")
    			.append("    </body>")
    			.append("</html");
    			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    // POST 방식의 요청일 때 WAS가 호출하는 메서드.
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
