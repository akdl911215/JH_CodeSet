package jung.md.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import jung.md.domain.Address;
import jung.md.domain.AddressFile;
import jung.md.mapper.AddressMapper;
import jung.uss.filesetting.Path;
import lombok.extern.log4j.Log4j;
@Log4j
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressMapper addressMapper;
	//private AddressDao addressDao;
	
	@Override
	public List<Address> listS() {
		//return addressDao.list();
		return addressMapper.list();
	}

//	@Override
//	public void insertS(Address address) {
//		//addressDao.insert(address);
//		addressMapper.insert(address);
//	}

	ArrayList<AddressFile> uploadedFileList;
	@Transactional // root.conetxt에서 transactionManager 선안하고 @Transactional 선언하면 절차 한개라도 실패하면 롤백
	@Override
	public ArrayList<AddressFile> insertS(Address address, ArrayList<MultipartFile> files) {
		log.info("실패시에 작동하면 안되지##############");
		// 1. 주소록 데이터를 insert
		addressMapper.insertSelectKey(address);
		// .insertSelectKey(address) 수행하고나면 address에 시퀀스값을 채워준다
		log.info("####address.getSeq(): " + address.getSeq());
		
		// 2. 파일들을 업로딩
		ArrayList<AddressFile> uploadedFileList = new ArrayList<AddressFile>();
		for(MultipartFile file: files) {
			String ofname = file.getOriginalFilename();
			if(ofname != null) ofname = ofname.trim();
			if(ofname.length() != 0) {
				AddressFile addressFile = saveStore(file);
				uploadedFileList.add(addressFile);
				
				if(addressFile != null) {
					// 3. 파일 데이터들을 insert
					addressFile.setSeq(address.getSeq());
					addressMapper.insertF(addressFile);
				}
			}
			
		}
		return uploadedFileList;
	}
	
	private AddressFile saveStore(MultipartFile file) {
		String ofname = file.getOriginalFilename();
		int idx = ofname.lastIndexOf(".");
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
			log.info("#파일 업로드 성공: " + saveFileName);
			return new AddressFile(-1L, ofname, saveFileName, fsize, -1L);
		}else {
			log.info("#파일 업로드 실패: " + saveFileName);
			return null;
		}
	}
	private boolean writeFile(MultipartFile file, String saveFileName) {
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
	
	@Override
	public void removeFiles() {
		for(AddressFile addressFile: uploadedFileList) {
			//log.info("#AddressServiceImpl removeFiles() addressFile: " + addressFile);
			File f = new File(Path.FILE_STORE, addressFile.getSfname());
			if(f.exists()) f.delete();
		}
	}
	
	@Override
	public void removeFiles(long seq){
		List<AddressFile> listFiles = addressMapper.fileListForRemove(seq);
		log.info("#AddressServiceImpl removeFiles("+seq+"): " + listFiles);
		for(AddressFile addressFile: listFiles) {
			File f = new File(Path.FILE_STORE, addressFile.getSfname());
			if(f.exists()) f.delete();
		}
	}

	@Override
	public void deleteS(long seq) {
		// TODO Auto-generated method stub
		addressMapper.delete(seq);
	}
}
