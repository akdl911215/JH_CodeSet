package jung.md.controller;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jung.md.domain.Human;
import jung.md.domain.HumanList;
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
	
	@RequestMapping("/param1")
	public void m05(@RequestParam("na") String name, @RequestParam int age) {
		log.info("m05() name: " + name + ", age: " + age);
		
	}
	
	@RequestMapping("/param2")
	public void m06(Human dto) {
		log.info("m06() name: " + dto.getName() + ", age: " + dto.getAge());
	}
	
	@RequestMapping("/param3")
	public void m07(@RequestParam ArrayList<String> names) {
		log.info("m07() names : " + names);
//		for(String name: names) log.info(name);
	}
	
	@RequestMapping("/param4")
	public void m08(@RequestParam("na") ArrayList<String> names) {
		log.info("m08() names : " + names);
	}
	
	@RequestMapping("/param5")
	public void m09(@RequestParam String[] names) {
		for(String name: names) log.info("m09() name : " + name);
	}
	
	@RequestMapping("/param6")
	public void m10(HumanList list) {
		log.info("m10() list : " + list);
//		ArrayList<Human> li = list.getList();
//		for(Human man : li) {
//			log.info("#name: " + man.getName());
//			log.info("#age: " + man.getAge());
		
	}
	
	@RequestMapping("/param7")
	public String m11(Human dto, int page) { // 선언만 해주면 파라미터에 계속 추가가능
		log.info("m06() name: " + dto.getName() + ", age: " + dto.getAge() 
		 + ", page: " + page);
	
	return "../index.jsp";
	}
	
	@GetMapping("json1") // ResponseEntity 은 json 데이터를 리턴한다.
	public ResponseEntity<String> m12() {
		String msg = "{\"name\": \"홍길동\", \"age\": 109}";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		
		return new ResponseEntity<String>(msg, headers, HttpStatus.OK);
	}
	
	@GetMapping("json2") // 기본값은 jsp인데 json을 찾게해줘야 한다.
	public @ResponseBody Human m13() { // 방법은 @ResponseBody 추가

		return new Human("버핏옹", 99); // Dto를 리턴해준것
	}
	
	@GetMapping("txt") // 기본값은 jsp인데 json을 찾게해줘야 한다.
	public @ResponseBody String m14() { // 방법은 @ResponseBody 추가
		
		
		return ("참치김밥이다 Come on ^^"); // Dto를 리턴해준것
	}
	
	@RequestMapping("self")
	public @ResponseBody Human self01
		(@RequestParam String name, @RequestParam int age) {
		log.info("self01() name: " + name + ", age: " + age);
		return new Human("봐핏옹", 100);
	}
}
