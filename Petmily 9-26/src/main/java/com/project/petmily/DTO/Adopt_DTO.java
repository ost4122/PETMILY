package com.project.petmily.DTO;

import java.util.Date;
import lombok.Data;

@Data
public class Adopt_DTO {

	private int a_number;             // 분양신청글 번호
	private String a_name;            // 신청자 이름
	private String a_id;              // 신청자 아이디
	private int a_petnumber;          // 펫 번호
	private String a_petcategorie;    // 펫 카테고리
	private String a_petname;         // 펫 종
	private String a_petprice;        // 펫 분양 가격
	private Date a_Date;              // 펫 분양 신청 날짜
	private int a_adminconfirm;       // 관리자 확인 유무
	
}
