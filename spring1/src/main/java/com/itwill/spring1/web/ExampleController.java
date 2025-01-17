package com.itwill.spring1.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.spring1.dto.User;

import lombok.extern.slf4j.Slf4j;

//POCO(Plain Old C++(C#) Object)
//POJO(Plain Old Java Object): 간단한 오래된 자바 객체.
//특정 클래스를 상속(extends)하거나, 특정 인터페이스를 구현(implements)할 필요가 없는
//(상위 타입의 특정 메서드들을 반드시 재정의할 필요가 없는) 평범한 자바 객체.
//스프링 MVC 프레임워크에서는 POJO로 작성된 클래스를 컨트롤러로 사용할 수 있음!
//(비교) HttpServlet을 상속받는 클래스에서는 doGet(req, resp) 또는 doPost(req, resp)를
//반드시 재정의(override)해야 웹 서비스(요청 처리)가 가능.

@Slf4j
//-> private static final Logger log = LoggerFactory.getLogger(ExampleController.class);
// 코드를 애너테이션으로 처리.


@Controller
//-> 클래스가 컨트롤러 컴포넌트임을 설정하는 애너테이션.
// servlet-context.xml 파일에서 <context:component-scan ... /> 태그에서 설정된 패키지와 
// 그 하위 패키지에서 @Controller 애너테이션을 사용한 클래스를 찾음.
public class ExampleController {

	@GetMapping("/")
	//-> GET 방식, 요청 주소가 컨텍스트 루트(예: /spring1/)인 요청을 처리하는 메서드임을 설정하는 애너테이션.
	public String home(Model model) {
		log.debug("home()");
		
		LocalDateTime now = LocalDateTime.now(); // 현재 시간
		model.addAttribute("now", now);
		
		return "home";
		//-> 컨트롤러 메서드가 문자열을 리턴하면, 디스패쳐 서블릿이 뷰의 이름을 찾는 데 사용.
		// servlet-context.xml 파일에서 <bean> 태그에서 설정된 ViewResolver 설정을 사용함.
		// 디스패쳐 서블릿이 뷰 리졸버를 이용해서 요청을 포워드할
		// 뷰의 경로(/WEB-INF/views/returnValue.jsp)를 찾을 수 있음.
	}
	
	@GetMapping("/example")
	public void ex() {
		log.debug("ex()");
		// 디스패쳐 서블릿이 뷰의 이름을 ViewResolver를 사용해서 찾는 방법:
		// (1) 컨트롤러의 메서드가 문자열(String)을 리턴하는 경우는 리턴 값이 jsp 파일의 이름.
		// (2) 컨트롤러 메서드의 리턴 타입이 void인 경우, 매핑된 요청 주소가 jsp 파일의 이름.
	}
	
		/*
		 * 이클립스 메뉴 Window -> Preferences -> Java -> Compiler ->
		 * Store information about method parameters (usable via reflection) 항목 체크.
		 * 자바 컴파일러가 컴파일할 때 메서드 파라미터 정보를 저장해서,
		 * 자바 프로그램이 파라미터 이름을 reflection 기능으로 사용할 수 있도록 함.
		 * 
		 * 컨트롤러 메서드의 파라미터를 선언할 때 @RequestParam 애너테이션을 사용하면,
		 * 디스패쳐 서블릿이 컨트롤러 메서드를 호출할 때,
		 * 요청 파라미터 값을 메서드 아규먼트로 전달할 수 있음.
		 * @RequestParam 애너테이션에 name 속성이 설정되지 않은 경우에는 
		 * 메서드 파라미터 이름으로 요청 파라미터 값을 찾음. (예) request.getParameter("age")
		 * @RequestParam 애너테이션에 name 속성이 설정된 경우에는
		 * name 속성으로 요청 파라미터 값을 찾음. (예) request.getParameter("username")
		 * 디스패쳐 서블릿은 컨트롤러 메서드의 아규먼트(들)을 전달하기 위해서 
		 * 가능한 경우 타입 변환도 자동으로 수행함.
		 * (예) Integer.parseInt(request.getParameter("age")) 
		 * 
		 * @RequestParam 애너테이션이 defaultValue 속성 값을 설정하면
		 * 요청 파라미터 값이 없는 경우 기본값으로 사용할 수 있음.
		 */
	
	@GetMapping("/ex1")
	public void ex1(@RequestParam(name = "username") String name,@RequestParam(defaultValue = "0") int age 
			, Model model) {
		// 쿼리문자열 받으려면 파라미터를 넣어주면 됨. 리플렉션을 이용해 파라미터 이름 정보를 기능을 추가
		// 유저이름과 나이의 값을 디스패처 서블릿에게 알려줘야함 이것을 애너테이션을 사용해서 알려준다. @RequestParam
		// 디스패처 서블릿에 리퀘스트 파라미터에서 찾으라고 알려준다 
		// 주의 - 요청 파라미터 이름과 메서드 파라미터 이름이 다르면 400 - 잘못된 요청으로
		// 디스패처 서블릿이 찾지 못하기 때문. 컴파일러가 메서드의 파라미터 정보를 (클래스에) 저장 
		// 디스패처 서블릿이 컴파일이 끝난 클래스 안에서 메서드의 이름 지역변수 이름 파라미터 이름을 끄집어 내는게 리플렉션 기능인데
		// 그 설정이 자바 컴파일러 설정. 메서드의 파라미터 정보를 그 클래스에 저장하는 기능
		log.debug("ex1(username={}, age={})", name, age);
		// 요청 파라미터 값들로 User 타입의 객체를 생성.
		User user = User.builder().username(name).age(age).build();
		
		model.addAttribute("user", user);
		// user 객체를 뷰(jsp)에게 전달.
		
		
		// 공백을 넣게 될 경우 에러 400
		// username은 빈 문자열을 리턴하지만, int는 integer.parseInt로 문자열을 변환하는데
		// 빈 문자열을 int로 변환할때 NumberFormatException을 발생함
		// 기본값을 설정을 해서 에러를 발생하는것을 막을 수 있다 @RequestParam(defaultValue = "0")
		// 요청 파라미터, 메서드 파라미터가 다를 경우에도 @RequestParam(name = "username")
		
		// 디스패쳐 서블릿에게 요청이 왔을때 호출 해주는 메서드를 만들어 주는게 개발자가 할 일.
		// 애너테이션을 사용해서.
		
		// POST/ ex2 요청 -> ex1.jsp
	}
	
	@PostMapping("/ex2")
//	public String ex2(@RequestParam String usrename, @RequestParam int age) {
	public String ex2(/* @ModelAttribute */ User user) {
		// 디스패쳐 서블릿은 컨트롤러 메서드를 호출하기 위해서,
		// (1)User 클래스의 기본 생성자를 호출.
		// (2)요청 파라미터 값을 읽어서, 요청 파라미터 이름으로 User 클래스의 setter를 호출.
		// 요청 파라미터 값이 없을 경우, 기본 타입 int는 에러가 발생하지만,
		// wrapper 클래스 타입 Integer는 에러 발생하지 않음.
		
		// (3)User 타입 객체를 컨트롤러 메서드의 아규먼트로 전달.
		// (4)컨트롤러 메서드의 아규먼트를 Model의 속성으로 추가(@ModelAttribute). 3.0대 버전은 필수.
		
		log.debug("ex2(user={})", user);

		return "ex1";
	}
	
	@GetMapping("/test") 
	public void test() {
		
		log.debug("test()");
	}
	
	@GetMapping("/test2") 
	public String test2() {
		
		log.debug("test2()");
		return "forward:/test";
		// 컨트롤러 메서드가 "forward:"으로 시작하는 문자열을 리턴
		// -> 포워드(forward) 방식의 이동. 
		// -> 최초 요청 주소가 변경되지 않음. request, response 객체가 유지.
		
		// 최초 요청 주소가 변하지 않는게 포워드
	} 
	
	@GetMapping("/test3") 
	public String test3() {
		log.debug("test3()");
		
		return "redirect:/test";
		// 컨트롤러 메서드가 "redirect:" 으로 시작하는 문자열을 리턴
		// -> 리다이렉트(redirect) 방식의 이동.
		// HTTP 302(redirect) 응답 이후에 클라이언트가 요청을 다시 보냄.
		// 최초 요청 URL이 redirect 되는 URL로 바뀜. = 주소가 바뀐다는 말.
		// 새로운 request, response 객체가 만들어짐.
	}
	
	@GetMapping("/rest1")
	@ResponseBody // 뷰 이름을 찾는게 아니라 리턴의 값을 response 로 문자열 그대로 내보냄.
	// -> 컨트롤러 메서드가 리턴하는 값이 뷰를 찾기 위한 문자열이 아니라,
	//    클라이언트로 직접 응답으로 전송되는 데이터.
	public String rest1() {
		log.debug("rest1()");
		
		return "Hello, 안녕하세요!";
	}
	
	@GetMapping("/rest2")
	@ResponseBody //-> 메서드의 리턴값이 클라이언트에게 직접 전송되는 데이터.
	public User rest2() {
		log.debug("rest2()");
		
		return User.builder().age(16).username("홍길동").build();
		//-> 컨트롤러가 리턴한 자바 객체를 jackson-databind 라이브러리에서
		// JSON(JavaScript Object Notation) 형식의 문자열로 변환하고
		// 클라이언트에게 JSON 문자열이 응답으로 전송됨.
		// (참고) jackson-databind의 기능: Java 객체 <---> JSON 문자열 변환을 양방향
		// 자바 객체 문자열 뽑아서 잭슨으로 변환하거나 자바 객체로 변환하거나 
		// html이 아니라 데이터 자체를 응답으로 보내주는게 rest 컨트롤러로 해주는데 ResponseBody를 써준다.
		// 다른 방법 = REST 3
	}
	
	
}