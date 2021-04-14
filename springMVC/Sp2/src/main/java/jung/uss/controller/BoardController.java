package jung.uss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jung.uss.domain.Board;
import jung.uss.service.BoardService;
import lombok.Data;

@Data
@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/list.do")
	public ModelAndView list() { //ModelAndView 데이터를 전송시킬 수 있는 리턴 타입
		 List<Board> list = boardService.listS();
		 
		 ModelAndView mv = new ModelAndView("board/list", "list", list);
		 
		 return mv;
		
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/write.do")
	public String write(Board board) {
		boardService.insertS(board);
		
		return "redirect:list.do";
	}

	
	@GetMapping("/update.do")
	public String getUpdate(Board board) {
		boardService.updateS(board);
		
		return "board/update";		
	}
	
	@PostMapping("/update.do")
	public String doUpdate(Board board) {
		boardService.equals(board);
	
		return "redirect:list.do";		
	}
	
	@GetMapping("content.do")
	public ModelAndView read(long seq) {
		Board board = boardService.contentS(seq);
		
		ModelAndView mv = new ModelAndView("board/content", "board", board);

		return mv;
	}
	
	@GetMapping("/del.do")
	public String delete(long seq) {
		boardService.deleteS(seq);
		
		return "redirect:list.do";
	}
	
	@GetMapping("/form_mt.do")
	public String formMt() {
		
		return "file/form_mt";
	}
}
