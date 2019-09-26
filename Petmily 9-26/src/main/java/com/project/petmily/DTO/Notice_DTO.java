package com.project.petmily.DTO;


import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Notice_DTO {
   private int notice_number ; //공지사항 번호
   private String notice_title; //공지사항 제목
   private String notice_writer; //공지사항 작성자(관리자)
   private String notice_contents; //공지사항 본문 
   
   private MultipartFile notice_filename; //공지사항 이미지 
   private String notice_FILE; //공지사항 이미지 
   
   
   
}
