package jung.md.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String m() {
		return "index";
	}
	@RequestMapping("index.do")
	public String m_index() {
		return "index";
	}
}