package com.project.petmily.DAO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.petmily.DTO.Notice_DTO;

@Repository
public class Notice_DAO {
   
   @Autowired
   private SqlSessionTemplate sql;
 
   //공지사항 게시판 
   public List<Notice_DTO> NoticeList() {
   
      return sql.selectList("Notice.NoticeList"); 
   }
   //공지사항 글쓰기 
   public int NoticeWrit(Notice_DTO notice_dto) {
      
      return sql.insert("Notice.NoticeWrit",notice_dto); 
   }
   //공지사항 글 상세보기 
   public Notice_DTO NoticeView(int notice_number) {
      
      return sql.selectOne("Notice.NoticeView",notice_number);
   }
   //공지사항 이전 글 상세보기 
   public Notice_DTO notice_Previous_View(int notice_number) {
      return sql.selectOne("Notice.notice_Previous_View",notice_number);
   }
   //공지사항 다음 글 상세보기 
   public Notice_DTO notice_next_View(int notice_number) {
      return sql.selectOne("Notice.notice_next_View",notice_number);
   }

}