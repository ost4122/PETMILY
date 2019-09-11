package com.project.petmily.Controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.petmily.DTO.ShopDTO;
import com.project.petmily.Service.ShopService;

import oracle.sql.DATE;

@Controller
public class ShopController {
	@Autowired
	private ShopService ssvc;
	private ModelAndView mav;
	
	// 용품 등록 페이지
	@RequestMapping(value = "/Shop_InputPage")
	public String Shop_InputPage() {

		
		return "Shop_Input";
	}
	
	// 용품 등록 
	@RequestMapping(value = "/Shop_Input")
	public ModelAndView Shop_Input(@ModelAttribute ShopDTO sdto,HttpServletResponse response) 
			throws IllegalStateException, IOException {
		
		long nowTime = System.currentTimeMillis();
		
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		
		String str = dayTime.format(new Date(nowTime));
		
		String itemName = sdto.getItem_name();
		
		String rename = itemName += str;
		
		mav = new ModelAndView();
	      
		List<MultipartFile> fileList = sdto.getImges();

		String path = "C:\\Users\\user\\git\\PETMILY\\Petmily\\src\\main\\webapp\\resources\\shopIMG";
        
        List<String> fileNames = new ArrayList<String>();

        
        for (MultipartFile mf : fileList) {
        	
        	
        	String originFileName = mf.getOriginalFilename(); // 원본 파일 명
        	
        	//originFileName = rename;
        	
        	long fileSize = ((MultipartFile) mf).getSize(); // 파일 사이즈
            


            String safeFile = originFileName + str;
            try {
                mf.transferTo(new File(safeFile));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            fileNames.add(originFileName);
        }
        
        //상품 프로필 
        MultipartFile img = sdto.getImg();
		
        String fileName = img.getOriginalFilename();
		
		String itemRename = itemName += str;
		
		// fileName = itemRename;
		
		String savePath = "C:\\Users\\user\\git\\PETMILY\\Petmily\\src\\main\\webapp\\resources\\shopIMG\\"+fileName;
		if(!img.isEmpty()) {
			img.transferTo(new File(savePath));
		}
		
		sdto.setItem_Profile(fileName);
        
        mav = ssvc.Shop_Input(fileNames,sdto,response);
        
        
        
		
		return mav;
	}
}
