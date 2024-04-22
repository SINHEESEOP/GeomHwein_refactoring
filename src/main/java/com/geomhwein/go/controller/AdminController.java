package com.geomhwein.go.controller;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.geomhwein.go.admin.service.AdminMapper;
import com.geomhwein.go.admin.service.AdminService;


import com.geomhwein.go.command.AdminVO;
import com.geomhwein.go.command.ContentUploadVO;
import com.geomhwein.go.command.ContentVO;
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.securlty.UserAuth;

import net.coobird.thumbnailator.Thumbnailator;




@Controller
@RequestMapping("/admin")
public class AdminController {
	
	
	@Autowired
	@Qualifier("AdminService")
	private AdminService adminService;
	
	@Autowired
	private AdminMapper adminMapper;
	
	
	@Value("${project.upload.path}")
	private String uploadPath;
	
	  public String makeFolder() {
	         
	      String filepath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	      
	      
	      File file = new File(uploadPath + "/" + filepath);
	      
	      if(file.exists() == false) { //해당 파일이 있으면 true, 없으면 false
	         
	         file.mkdirs();
	      
	      }
	      return filepath;
	   }

	
	

	@GetMapping("mttrInsert")
	public String mttrInsert() {
		
		return "admin/mttrInsert";
	}
	
	
	@PostMapping("/insertForm")
	public String insertPage(AdminVO vo) {
		adminService.mttrInsert(vo);
		
		
		return "redirect:/mttrList";
	}
	
	
	
	
	
	
	
	
	
	@GetMapping("/AllUserList")
	public String AllUserList(Model mo) {
		
		ArrayList<UserDetailsVO> AllUserList = adminService.AllUserList();
		mo.addAttribute("AllUserList", AllUserList);
		
		System.out.println(AllUserList.toString()+"!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		return "admin/AllUserList";
	}
	
	
	
	
	
	@GetMapping("/contentPage")
	public String ContentPage() {
		
		return "admin/contentPage";
	}
	
	
	
	
	
	@PostMapping("/uploadForm")
	public String contentInsert(ContentVO vo, 
								Authentication authentication, 
								@RequestParam("file") MultipartFile file
								) {
	
		UserAuth userAuth = (UserAuth)authentication.getPrincipal();

		String userId  = userAuth.getUserId();
		
		
		
		vo.setUserId(userId);		
		
		adminService.contentInsert(vo);
		
		
		
		
		
		String originName = file.getOriginalFilename(); //IE, Edge는 전체경로가 들어오므로 \\기준으로 파일명만 추출
		String filename =  originName.substring( originName.lastIndexOf("\\") + 1); // 서버에서 저장 할 파일 이름
		String filepath = makeFolder(); //날짜별 업로드 폴더명
		String uuid = UUID.randomUUID().toString(); //랜덤이름
		String saveName = uploadPath + "\\" + filepath  + "\\" + uuid + "_" + filename; //업로드경로
		
		
		
		System.out.println(uploadPath);
		
		try {
			File saveFile = new File(saveName);
			file.transferTo(saveFile);
			
			String thumbnailFileName = uuid + "_thumb_" + filename; // 썸네일 파일명
			String thumbnailPath = uploadPath + "\\" + filepath + "\\" + thumbnailFileName; // 썸네일 파일 경로
			Thumbnailator.createThumbnail(new File(saveName), 
				    new File(thumbnailPath), 
				    200, 
				    200);
			
			
		} catch (Exception e) {
			System.out.println("업로드중 에러발생:" + e.getMessage());
		}
		
		//업로드 이후 저장
		
		adminMapper.registFile(ContentUploadVO.builder()
								.fileName(filename)
								.filePath(filepath)
								.uuId(uuid));
		
		
		return "redirect:/contentList";
	}

	
	
	
	
	
	
	
	
	}

