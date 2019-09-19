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

import com.project.petmily.DTO.ImagesDTO;
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
		
		ImagesDTO idto = new ImagesDTO();
		
		
		mav = new ModelAndView();
	    
		// 다중 파일 업로드
		List<MultipartFile> fileList = sdto.getInputImages();

        
        List<String> images = new ArrayList<String>();

        
        for (MultipartFile mf : fileList) {
        	
        	String originFileName = mf.getOriginalFilename();// 원본 파일 명
        	
        	String path = "C:\\Users\\user\\git\\PETMILY\\Petmily\\src\\main\\webapp\\resources\\shopIMG\\multyImg\\"+originFileName;
        	
        	//originFileName = rename;
        	
        	long fileSize = mf.getSize(); // 파일 사이즈
            
            	System.out.println("★★★★fileList : " + originFileName);

           
            	if(!fileList.isEmpty()) {
            		mf.transferTo(new File(path));
            	}
            
            images.add(originFileName);
            // 다중파일 업로드 imagesDTO에 셋팅
            idto.setImg(images);
         
        }
        //
        
        //상품 프로필 
        MultipartFile img = sdto.getImg();
		
        String fileName = img.getOriginalFilename();
		
		
		// fileName = itemRename;
		
		String savePath = "C:\\Users\\user\\git\\PETMILY\\Petmily\\src\\main\\webapp\\resources\\shopIMG\\"+fileName;
		if(!img.isEmpty()) {
			img.transferTo(new File(savePath));
		}
		
		sdto.setItem_Profile(fileName);
        
        mav = ssvc.Shop_Input(idto,sdto,response);
        
        
        
		
		return mav;
	}
	
	/* 용품 목록 */
	@RequestMapping(value = "/Shop_List")
	public ModelAndView Shop_List() {
		mav = new ModelAndView();
			
		
		mav = ssvc.Shop_List();
		
		
	return mav;
	}
	
	/* 용품 상세보기 */
	@RequestMapping(value = "/Shop_View")
	public ModelAndView Shop_View(@RequestParam int item_number) {
		mav = new ModelAndView();
		
		mav = ssvc.Shop_View(item_number);
		
		return mav;
	}
	
	/* 강아지 용품 목록*/
	@RequestMapping(value = "/puffyItem")
	public ModelAndView puffyItem(@RequestParam("item_kind") String item_kind) {
		mav = new ModelAndView();
		
		System.out.println("■ item_kind : " + item_kind);
		
		mav = ssvc.puffyItem(item_kind);
		
		return mav;
	}
	
	/* 강아지 용품 카테고리 검색*/
	@RequestMapping(value = "/puffyItems")
	public ModelAndView puffyItems(@ModelAttribute ShopDTO sdto) {
		mav = new ModelAndView();
		
		System.out.println("■ item_kind : " + sdto.getItem_kind());
		System.out.println("■ item_katagorie : " + sdto.getItem_katagorie());
		
		mav = ssvc.puffyItems(sdto);
		
		return mav;
	}
	
	/* 용품 구매 */
	@RequestMapping(value = "/ItemBuy")
	public ModelAndView ItemBuy(@RequestParam("item_number") int item_number,@RequestParam("id") String id) {
		mav = new ModelAndView();
		
		mav = ssvc.ItemBuy(item_number,id);
		
		
		return mav;
	}
		
		
		
		
		
	
}
