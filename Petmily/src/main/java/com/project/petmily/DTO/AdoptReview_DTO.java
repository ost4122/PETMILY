package com.project.petmily.DTO;

import java.util.Date;
import lombok.Data;

@Data
public class AdoptReview_DTO {

	private int ar_number;          // 게시글 번호
	private int pet_number;         // 펫 번호
	private String pet_kategorie;   // 펫 종류
	private String pet_name;        // 펫 종
	private String ar_title;        // 후기글 제목
	private String ar_writer;       // 후기글 작성자(로그인한 아이디)
	private Date ar_writedate;      // 작성날짜
	private int ar_viewhit;         // 후기글 조회수
	private String ar_contents;     // 후기글 내용
	
}
