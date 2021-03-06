package jung.ct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/test/*")
public class TestController {
	
	@RequestMapping("/")
	public void m01() {
		System.out.println("m01()");
	}
	
	@RequestMapping("/base1")
	public void m02() {
		System.out.println("m02() - Get방식, Post방식, ..");
	}
	
	//@RequestMapping(value = "/base2", method=RequestMethod.GET)
	@GetMapping("/base2")
	public void m03() {
		System.out.println("m03() - only Get방식");
	}
	
	@RequestMapping(value = "/base3", method= {RequestMethod.GET, RequestMethod.POST})
	public void m04() {
		System.out.println("m04() - Get방식, Post방식");
	}
	
	@RequestMapping(value = "/form", method= RequestMethod.GET)
	public void form() {
		System.out.println("test/form");
	}
}
