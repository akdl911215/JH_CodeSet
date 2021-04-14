package jung.uss.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jung.uss.filesetting.Path;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class FileUploadServiceImpl implements FileUploadService{

	@Override
	public String saveStore(MultipartFile file) {

		String ofname = file.getOriginalFilename();
		int idx = ofname.lastIndexOf("."); // .이라는 위치를 가져온다
		String ofheader = ofname.substring(0, idx); 
		String ext = ofname.substring(idx); 
		long ms = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		sb.append(ofheader);
		sb.append("_");
		sb.append(ms);
		sb.append(ext);
		String saveFileName = sb.toString();
		
		
		long fsize = file.getSize();
		log.info("#ofname:" + ofname 
				+ ", saveFileName: " + saveFileName + ", fsize: "+fsize);
		boolean flag = writeFile(file, saveFileName);
		if(flag) {
			log.info("#업로드 성공");
			// dao로 saveFileName, ofname, fsize 정보를 넘기는 메소드 호출하고 dto도 같이넘겨줘야한다
		}else {
			log.info("#업로드 실패");
		}
		return Path.FILE_STORE + "/" +saveFileName;
	}

	@Override
	public boolean writeFile(MultipartFile file, String saveFileName) {
		File dir = new File(Path.FILE_STORE); //저장소 경로 객체 
		if(!dir.exists()) dir.mkdir();
		FileOutputStream fos = null;
		try {
			byte data[] = file.getBytes();
			fos = new FileOutputStream(Path.FILE_STORE +"/"+ saveFileName);
			fos.write(data);
			fos.flush();
			return true;
		}catch(IOException ie) {
			return false;
		}finally {
			try {
				if(fos != null) fos.close();
			}catch(IOException ie) {}
		}
	}


}
