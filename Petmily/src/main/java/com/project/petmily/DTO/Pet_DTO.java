package com.project.petmily.DTO;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Pet_DTO {

	private int pet_number; // 펫번호
	private String pet_size; // 펫 크기
	private String pet_kategorie; // 펫 종류
	private String pet_name; // 펫 이름 (펫 종 ex>포메라니안,진돗개)
	private int pet_live; // 생후 개월수
	private int pet_hit; // 펫 추천수
	private int pet_price; // 분양 가격
	private int pet_adopt; // 분양 예약 유무
	private int adopt_confirm; // 분양 확인 (초기값 0으로 인설트 하기 )
	private String pet_profile; // 펫 프로필 사진
	private MultipartFile pet_profile_file; // 펫 프로필 사진
	private String pet_img; // 펫상세보기 이미지
	private MultipartFile pet_img_file; // 펫 상세보기 이미지
	private String pet_trailer; // 펫 소개 동영상
//	private MultipartFile pet_trailer_file; //펫 소개 동영상  링크 할거여서 필요음슴 
	private String pet_contents; // 펫 소개글
	private String pet_inoculation; // 펫 접종

	// 페이징

	private int page;
	private int maxPage;
	private int startPage;
	private int endPage;
	private int boardCount;
	private int startRow;
	private int endRow;

	// 댓글
	private int commentNumber; // comment를 구분하기위만 ㅎcomment만의 글번호
	private int comment_PET_NUMBER; // 어떤 게시글에 대한 comment냐
	private String commentContents; // 댓내용
	private String commentWriter; // 댓 작성자 (나중엔로그인한 아이디로 써야지 )
	private Date commentsDate; // 작성일자 추가함.ㅣ
	private String commentSecret;// 비밀댓글 추가함

	private int commentCount;

	// 분양큐앤에이 에선 관리자만 쓸수 있는 대댓글 디비설계하깅
	private int smail_commentNumber; // comment를 구분하기위만 ㅎcomment만의 글번호
	private int smail_comment_PET_NUMBER;// 어떤 댓글에 대한 대댓글이냐
	private int not_smail_commentNumber; // 어떤 게시글에 대한 comment냐
	private String smail_commentContents; // 대댓내용
	private String smail_commentWriter; // 댓 작성자 (관리자긴한데써야지 )
	private Date smail_commentsDate; // 작성일자 추가함.ㅣ

}
