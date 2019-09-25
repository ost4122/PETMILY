package com.project.petmily.Controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.petmily.DTO.Notice_DTO;
import com.project.petmily.Service.Notice_Service;

@Controller
public class Notice_Controller {

   private ModelAndView mav;
   
   @Autowired
   private Notice_Service noteiceSerivce ;
   
   
   
   //글쓰러 가는 jsp
   @RequestMapping(value ="/admin_Notice_WritView", method = RequestMethod.GET)
   public String boardWrit() {
      return "Notice_WritJSP";
   }
   
   //공지사항 게시판 리스트 
   @RequestMapping(value = "/NoticeList", method = RequestMethod.GET)
   public ModelAndView NoticeList() {
        mav = new ModelAndView();
      mav = noteiceSerivce.NoticeList();

      return mav;

   }
   //공지사항 작성하기 
    @RequestMapping(value ="/admin_Notice_Writ")
   public ModelAndView NoticeWrit(@ModelAttribute Notice_DTO notice_dto)throws IllegalStateException, IOException{
      mav = new ModelAndView();
      MultipartFile notice_filename = notice_dto.getNotice_filename();//공지사항 이미지 
      String notice_FILE = notice_filename.getOriginalFilename();//공지사항 이미지 
      String savePath = "C:\\Users\\user\\Desktop\\Petmily (2)\\src\\main\\webapp\\resources\\notice\\"+notice_FILE;
      
      if(!notice_filename.isEmpty()) {//공지사항 이미지 
         notice_filename.transferTo(new File(savePath));
         }

      notice_dto.setNotice_FILE(notice_FILE);//공지사항 이미지 
      
       mav = noteiceSerivce.NoticeWrit(notice_dto);
       return mav;
   }
    
    
      //공지사항 글 상세 보기 
      @RequestMapping(value ="/NoticeView", method = RequestMethod.GET)
      public ModelAndView NoticeView(@RequestParam("notice_number") int notice_number) {
      
         mav = new ModelAndView();
         mav = noteiceSerivce.NoticeView(notice_number);
   
      
         return mav;
   }

}