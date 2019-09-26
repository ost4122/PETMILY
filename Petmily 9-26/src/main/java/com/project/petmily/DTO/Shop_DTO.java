package com.project.petmily.DTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Shop_DTO {

	private int item_number;			/* 용품 번호  */
	private String item_name;			/* 용품 이름  */
	private String item_katagorie;		/* 용품 카테고리(강아지용품,고양이용품 ..)  */
	private String item_kind;			/* 용품 종류 (강아지사료,고양이사료..)*/
	private String item_contents;		/* 용품 상세 내용*/
	private int item_price;				/* 용품 가격  */
	private int item_reviewCount;		/* 용품 리뷰 수  */
	private int item_recommend;			/* 용품 추천 수 */
	private int item_soldCount;			/* 용품 판매 수  */
	private double item_grade;			/* 용품 평점  */
	
	private MultipartFile img;	/* 용품 프로필 */
	private String item_Profile;
	
	private List<MultipartFile> inputImages;			/* 첨부이미지  */
	private List<String> item_img;				/* 첨부이미지  */
}
