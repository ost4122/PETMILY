package com.project.petmily.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MemberDTO {
	
	private String id;
	private String password;
	private String name;
	private String phone;
	private String birth;
	private String address_number; //우편번호
	private String address_a; //도로명 주소
	private String address_b; //지번주소
	private String address_c; //상세주소
	private String address_d; //참고항목
	private String email;
	private String email_1;
	private String email_2;
	private String email_3;
	private String selectemail;
	private MultipartFile joinFile;
	private String profileimage;
	private String appoval_status; //아이디 인증 상태 
	
}