package kr.ac.hansung.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller //@Component(빈도 생성하고)+ 컨트롤러 역할
/*@component 를쓰면 빈을 생성해주는데*/
/*이 클래스가 컨트롤러다*/
public class HomeController {
	

	//사용자가  창에 /를 넣으면 RequestMethod.GET 이 메소드를 호출
	//controller url mapping 작업이 일어난다,
	@RequestMapping(value = "/", method = RequestMethod.GET)
	//locale은 지역.. 모델도 우리가 직접만든게 ㅇ냐
	public String showHome(Locale locale, Model model) {
		
		return "home";
	}
	
}




