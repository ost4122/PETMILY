package com.project.petmily.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.servlet.ModelAndView;

import com.project.petmily.DAO.Notice_DAO;
import com.project.petmily.DTO.Notice_DTO;



@Service
public class Notice_Service {
   
   private ModelAndView mav;
   
   @Autowired
   private Notice_DAO notice_dao;
   
   //공지사항 게시판리스트 (첫페이지 )
   public ModelAndView NoticeList() {
      
      mav = new ModelAndView();
      
        List<Notice_DTO> noticeList = notice_dao.NoticeList();
        
        mav.addObject("noticeList", noticeList);
        mav.setViewName("Notice_ListJsp");
        return mav;
   }
   //공지사항 글쓰기 
   public ModelAndView NoticeWrit(Notice_DTO notice_dto) {
      
      mav = new ModelAndView();
      int writResult = notice_dao.NoticeWrit(notice_dto);
      
      if (writResult>0) {
         mav.setViewName("home"); 
         
      } else {
         mav.setViewName("Notice_WritJSP"); 
      }
      return mav;
   }
   //공지사항 글 상세 보기 
    public ModelAndView NoticeView(int notice_number) {
       
       Notice_DTO noticeView = notice_dao.NoticeView(notice_number); 
       
       //이전글 
        Notice_DTO notice_Previous_View = notice_dao.notice_Previous_View(notice_number); 
        mav.addObject("notice_Previous_View", notice_Previous_View);
        //다음글 
        Notice_DTO notice_next_View = notice_dao.notice_next_View(notice_number); 
         mav.addObject("notice_next_View", notice_next_View); 
        
         
          mav.addObject("noticeView", noticeView);
       
      mav.setViewName("NoticeViewJSP");
   
   return mav;
}




}