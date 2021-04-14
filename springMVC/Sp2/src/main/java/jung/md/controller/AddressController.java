package jung.md.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jung.md.domain.Address;
import jung.md.domain.AddressFile;
import jung.md.service.AddressService;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/address/*")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping("list.do")
	public ModelAndView list() {
		List<Address> list = addressService.listS();
		
		/*ModelAndView mv = new ModelAndView();
		mv.setViewName("address/list"); //View
		mv.addObject("list", list); //Model 
		*/
		ModelAndView mv = new ModelAndView("address/list", "list", list);
		
		return mv;
	}
	
	@GetMapping("write.do") // form으로 이동
	public String write() {
		return "address/write";
	}

	@PostMapping("write.do")
	public String write(Address address, @RequestParam ArrayList<MultipartFile> files) {
		ArrayList<AddressFile> uploadedFileList = null;
		try {
			uploadedFileList = addressService.insertS(address, files);
			log.info("#AddressController (1): " + uploadedFileList); //null ?
		}catch(Exception ex) {
			log.info("#AddressController (2): " + uploadedFileList); //null ?
			addressService.removeFiles();
		}
		
		return "redirect:list.do";
	}
	
	@GetMapping("del.do")
	public String delete(long seq) {
		log.info("################long seq: " + seq);
		
		addressService.removeFiles(seq); // 업로드된 파일들 제거
		log.info("###addressService.removeFiles(seq): " + seq);
		addressService.deleteS(seq); // DB 데이터 제거
		log.info("###addressService.deleteS(seq): " + seq);
		
		return "redirect:list.do";
	}
}
