package com.project.petmily.DTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Purchase_DTO {

	/* 구매후기 */
	
	//작성자
	private String review_writer;
	//상품 번호
	private int item_number;
	//구매 후기 내용
	private String review_contents;
	//상품 디자인 평가
	private String review_design;
	//상품 편리성 평가
	private String review_handiness;
	//구매 후기 작성 날짜
	private String review_wirteD;
	//구매후기 번호
	private int review_number;
	//구매후기 추천
	private int review_hit;
	
	
	
	//구매후기 첨부 파일
	private List<MultipartFile> inputImages;
	private List<String> review_file;
	private String multiImg;
	
	
}
