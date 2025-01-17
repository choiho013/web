package com.itwill.jsp1.listener;

import java.util.Enumeration;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Application Lifecycle Listener implementation class ListenerExample
 *
 */
// WAS가 시작될 때 리스너 객체를 생성하고 관리할 수 있도록 설정: 
// (1) web.xml 파일에서 <listener> 태그로 설정.
// (2) @WebListener 애너테이션으로 설정.
// (주의) 한 개의 리스너 클래스는 web.xml과 애너테이션을 둘 다 동시에 설정할 수는 없음.
public class ListenerExample 
	implements ServletRequestListener, ServletRequestAttributeListener {
	// ServletRequestListener: 요청(request) 객체가 생성/소멸 이벤트를 처리.
	// ServletRequestAttributeListener: 요청 객체의 attribute가 추가/삭제/변경 이벤트를 처리.
		
    /**
     * Default constructor. 
     */
    public ListenerExample() {
    	System.out.println("ListenerExample() 생성자 호출");
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    @Override
    public void requestInitialized(ServletRequestEvent sre)  { 
    	System.out.println("요청이 초기화됨.");
    	ServletRequest req = sre.getServletRequest();
//    	HttpServletRequest httpReq = (HttpServletRequest) req;
    	
    	if(req instanceof HttpServletRequest) { // type checking
    		HttpServletRequest httpReq = (HttpServletRequest) req; // casting
    		String uri = httpReq.getRequestURI();
    		System.out.println("[Request URI] " + uri);
    		System.out.println("[Request URL] " + httpReq.getRequestURL()); 
    	}
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    @Override
    public void requestDestroyed(ServletRequestEvent sre)  { 
    	System.out.println("요청이 소멸됨.");
    }
	
    // ServletRequestAttributeListener 인터페이스의 default 메서드(들)을 재정의.
    /**
     * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
     */
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
    	// 상위 타입에서 구현된(override되기 전의) 메서드를 호출.
    	ServletRequestAttributeListener.super.attributeAdded(srae);
    	// 인터페이스가 다중 상속이 되고 있기 때문에 클래스이름을 구분하기 위해서 적어주는거고
    	// 수퍼클래스의 메서드를 호출한것 
    	
    	Enumeration<String> attrNames = srae.getServletRequest().getAttributeNames();
    	while (attrNames.hasMoreElements()) { //DB result.next , 
    		System.out.println("Request Attr. Name: " + attrNames.nextElement() + " 추가됨.");
    		// 엘리먼트가 있으면 가져오고 없으면 멈추는 
    	}
    	
    }
}
