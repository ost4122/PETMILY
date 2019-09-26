package com.project.petmily.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Delivery_DTO {
	/* 배송관리 DTO */
	
	private String id;								// 구매자 아이디
	private String email;							// 구매자 이메일
	private String phone;							// 구매자 휴대전화
	private String name;							// 구매자 이름
	private String address;							// 구매자 주소지
	private String deliveryRequest;					// 구매자 요청사항
	
	private int item_number;						// 용품 번호
	private String item_name;						// 용품 이름
	private int item_price;							// 용품 가격
	private int deliveryPrice;						// 배송비
	private String paymentOption;					// 결제방법
	private String purchaseDate;
	
	/* 상품 프로필 */
	private String item_profile;
	
	

}
