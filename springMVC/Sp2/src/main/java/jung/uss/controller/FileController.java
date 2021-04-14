package jung.uss.controller;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jung.uss.filesetting.Path;
import jung.uss.service.FileUploadService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Controller
@RequestMapping("/file")
@AllArgsConstructor // @Autowired 대신 사용할수 있음 , AutoInjection 이라고함. Spring 4.3~ 버전부터 해줌
public class FileController {
	
	private FileUploadService service; //Spring 4.3~ : AutoInjection (with @AllArgsConstructor) 
	@GetMapping("/form.do")
	public String form() {
		return "file/form";
	}
	@PostMapping("/upload.do")
	public String upload(@RequestParam String name, MultipartFile file) {

		String ofname = file.getOriginalFilename();
		if(ofname != null) ofname = ofname.trim();
		if(ofname.length() != 0) {
			String url = service.saveStore(file);
			//log.info("#업로드 된  url:" + url);
		}
		return "redirect:list.do";
	}
	@GetMapping("/list.do")
	public ModelAndView fileList() {
		File fStore = new File(Path.FILE_STORE);
		if(!fStore.exists()) fStore.mkdirs();
		File files[] = fStore.listFiles();
		return new ModelAndView("file/list", "files", files);
	}
	
    @GetMapping("/del.do")
    public String del(@RequestParam String fname) {
    	File file = new File(Path.FILE_STORE, fname);
    	if(file.exists()) file.delete();
    	return "redirect:list.do";
    }
    
    @GetMapping("form_mt.do")
    public String formMt() {
    	return "file/form_mt";
    }
    
    @PostMapping("/upload_mt.do")
    public String uploadMt(@RequestParam ArrayList<MultipartFile> files) {
   	
   		for(MultipartFile file: files) {
   			String ofname = file.getOriginalFilename();
   			if(ofname != null) ofname = ofname.trim();
   			if(ofname.length() != 0) {
   				String url = service.saveStore(file);
   				//log.info("#업로드 된  url:" + url);
   			}
   		}
    	
    	return "redirect:list.do";
    }
    
    @GetMapping("download.do")
    public ModelAndView download(@RequestParam String fname) {
    	//다운로드 기능을 구현해보세요 ( 구글링  or 삽질 or .. )
    	log.info("##### 다운로드 작동중 #####");
        File file = new File(Path.FILE_STORE, fname);
        if(file.exists()) {
    	    return new ModelAndView("fileDownloadView", "downloadFile", file);
        }else {
        	return new ModelAndView("redirect:list.do");
        }
    }
}