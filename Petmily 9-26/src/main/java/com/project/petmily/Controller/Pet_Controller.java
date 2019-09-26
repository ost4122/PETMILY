package com.project.petmily.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.petmily.DTO.Pet_DTO;
import com.project.petmily.Service.Pet_Service;

@Controller
public class Pet_Controller {

	@Autowired
	private Pet_Service Pet_service;

	@Autowired
	private HttpSession session;

	private ModelAndView mav;

	/* 회사 소개 클릭 */
	@RequestMapping(value = "Item_puffy")
	public String Item_puffy() {

		return "AboutUs";
	}

	/* 회사 소개 클릭 */
	@RequestMapping(value = "Item_cat")
	public String Item_cat() {

		return "AboutUs";
	}

	// admin만 할수 있는 펫등록하러가기
	@RequestMapping(value = "admin_pet")
	public String admin_pet() {

		return "admin_pet_WriteJSP";
	}

	// admin만 할수 있는 펫등록하러가기
	@RequestMapping(value = "AdoptionprocedureJsp")
	public String AdoptionprocedureJsp() {
		return "Adoptionprocedurejsp";
	}

	// admin만 할수 있는 펫등록
	@RequestMapping(value = "/admin_pet_writ")
	public ModelAndView admin_pet_writ(@ModelAttribute Pet_DTO pet_DTO) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		System.out.println("컨트롤러엔 넘어오나?" + pet_DTO.getPet_name());

		String s = pet_DTO.getPet_name(); // 원본 문자열
		String s2;

		System.out.println("원본:    " + s);
		System.out.println(); // 줄바꿈

		// 문자열에서 모든 똠방각하 다 지우기
		s2 = s.replaceAll(",", "");
		System.out.println("삭제(3): " + s2); // 출력 결과: 가나다라ABCDE 가나다라ABCDE
		pet_DTO.setPet_name(s2);

		MultipartFile pet_profile_file = pet_DTO.getPet_profile_file(); // 펫 프로필 사진
		String fileName = pet_profile_file.getOriginalFilename();// 펫 프로필 사진
		String savePath = "C:\\Users\\user\\Desktop\\Petmily (2)\\src\\main\\webapp\\resources\\petUploadFile\\"
				+ fileName;

		MultipartFile pet_img_file = pet_DTO.getPet_img_file();// 펫 상세보기 이미지
		String pet_img = pet_img_file.getOriginalFilename();// 펫 상세보기 이미지
		String savePath2 = "C:\\Users\\user\\Desktop\\Petmily (2)\\src\\main\\webapp\\resources\\petUploadFile\\"
				+ pet_img;

		if (!pet_profile_file.isEmpty()) {// 펫 프로필 사진
			pet_profile_file.transferTo(new File(savePath));
		}

		pet_DTO.setPet_profile(fileName);// 펫 프로필 사진

		if (!pet_img_file.isEmpty()) {// 펫 상세보기 이미지
			pet_img_file.transferTo(new File(savePath2));
		}

		pet_DTO.setPet_img(pet_img);// 펫 상세보기 이미지

		mav = Pet_service.admin_pet_writ(pet_DTO);

		return mav;
	}

	// 미분양된 강아지 all리스트 (이미지 띄워줘야함 )
	@RequestMapping(value = "/Pet_Dog_List", method = RequestMethod.GET)
	public ModelAndView Pet_Dog_allList(@RequestParam("page") int page) {

		mav = new ModelAndView();
		mav = Pet_service.Pet_Dog_allList(page);

		return mav;

	}

	// 강아지 상세 보기
	@RequestMapping(value = "/Pet_Dog_View", method = RequestMethod.GET)
	public ModelAndView Pet_Dog_View(@RequestParam("pet_number") int pet_number, @RequestParam("page") int page) {
		// @RequestParam("page") int page 넣기
		mav = new ModelAndView();
		mav = Pet_service.Pet_Dog_View(pet_number, page);

		return mav;
	}

	// 분양 추천 (강아지)
	@RequestMapping(value = "liketo")
	public @ResponseBody String liketo(@RequestParam("pet_number") int pet_number) {

		// Pet_DTO petdto = new Pet_DTO();
		String UpdateResult = Pet_service.Hitto(pet_number);

		if (UpdateResult.equals("1")) {
			session.setAttribute("sessionHit", UpdateResult);
		}

//				 int pet_hit = Pet_service.Hitto(pet_number);
//				// pet_hit = Pet_service.Hitto(pet_number);
		//
//				if(pet_hit ==1) { //좋아요 
//					session.setAttribute("sessionHit",pet_hit);
//					
//				}else { //좋아요 안됨.
//					
//					
//				}
		return UpdateResult;
	}

	// 분양 추천취소 (강아지)
	@RequestMapping(value = "likecancle")
	public @ResponseBody String likecancle(@RequestParam("pet_number") int pet_number) {

		// Pet_DTO petdto = new Pet_DTO();
		String UpdateResult = Pet_service.likecancle(pet_number);
		if (UpdateResult.equals("0")) {
			session.setAttribute("sessionHit", UpdateResult);
		}

		return UpdateResult;
	}

	// 대형견 리스트
	@RequestMapping(value = "/Pet_Dog_Big", method = RequestMethod.GET)
	public ModelAndView Pet_Dog_Big(@RequestParam("page") int page) {

		mav = new ModelAndView();
		mav = Pet_service.Pet_Dog_Big(page);

		return mav;

	}

	// 중형견 리스트
	@RequestMapping(value = "/Pet_Dog_Medium", method = RequestMethod.GET)
	public ModelAndView Pet_Dog_Medium(@RequestParam("page") int page) {

		mav = new ModelAndView();
		mav = Pet_service.Pet_Dog_Medium(page);

		return mav;

	}

	// 소형견 리스트
	@RequestMapping(value = "/Pet_Dog_Small", method = RequestMethod.GET)
	public ModelAndView Pet_Dog_Small(@RequestParam("page") int page) {

		mav = new ModelAndView();
		mav = Pet_service.Pet_Dog_Small(page);

		return mav;

	}

	// 고양이 리스트
	@RequestMapping(value = "/Pet_Cat_List", method = RequestMethod.GET)
	public ModelAndView Pet_Cat_List(@RequestParam("page") int page) {

		mav = new ModelAndView();
		mav = Pet_service.Pet_Cat_List(page);

		return mav;

	}

	// 파충류 리스트
	@RequestMapping(value = "/Pet_Reptile_List", method = RequestMethod.GET)
	public ModelAndView Pet_Reptile_List(@RequestParam("page") int page) {

		mav = new ModelAndView();
		mav = Pet_service.Pet_Reptile_List(page);

		return mav;

	}

	// 분양Q&A 댓글 쓰기
	@RequestMapping(value = "/commentWrite")
	public @ResponseBody List<Pet_DTO> commentWrite(@ModelAttribute Pet_DTO commentDTO,
			@RequestParam("page") int page) {
		// 먼저쓰고 db로 부터 갖고오는게 들어가야함.
		System.out.println("commentBoardNumber : " + commentDTO.getComment_PET_NUMBER());
		if (commentDTO.getCommentSecret().equals("true")) { // 비밀댓글
			commentDTO.setCommentSecret("secret");
			// 댓글 db쓰기
			int writeResult = Pet_service.commentWriter(commentDTO);
		} else {
			// 댓글 db쓰기
			int writeResult = Pet_service.commentWriter(commentDTO);
		}

		page = 1;
		// DB에서 댓글 가져오기
		List<Pet_DTO> commentList = Pet_service.commentList(commentDTO, page);
		// List<CommentDTO> commentList = BoardService.commentList(commentDTO);

		return commentList;
	}

	// 댓글 수정 하기
	@RequestMapping(value = "/commentModifyUpdate")
	public @ResponseBody String commentModifyUpdate(@ModelAttribute Pet_DTO pet_DTO) {
		String UpdateResult = Pet_service.commentModifyUpdate(pet_DTO);
		// System.out.println("수정commentNumber값 :"+commentNumber);
		return UpdateResult;
	}

	// 댓글 삭제하기
	@RequestMapping(value = "/commentDelete", method = RequestMethod.GET)
	public @ResponseBody String commentDelete(@RequestParam("commentNumber") int commentNumber) {

		String deleteResult = Pet_service.commentDelete(commentNumber);
		System.out.println("commentNumber값 :" + commentNumber);
		return deleteResult;
	}

	// 댓글 리스트 만들기
	@RequestMapping(value = "/commentList2", method = RequestMethod.POST)
	public @ResponseBody List<Pet_DTO> commentList(@ModelAttribute Pet_DTO paging, @RequestParam("page") int page) {
		System.out.println();
		// System.out.println("댓글리스트불러지냐?"+commentDTO.getCommentBoardNumber());
		List<Pet_DTO> commentList = Pet_service.commentList(paging, page);

		return commentList;
	}

	// 펫분양글 삭제 (ajax로 할거얌)
	@RequestMapping(value = "/Pet_Delete", method = RequestMethod.GET)
	public @ResponseBody String Pet_Delete(@RequestParam("pet_number") int pet_number) {
		System.out.println("pet_number가 있냐?" + pet_number);
		String deleteResult = Pet_service.pet_delete(pet_number);

		return deleteResult;
	}

	// 펫 분양글 수정전 글 불러오기
	@RequestMapping(value = "/Pet_ModifyView", method = RequestMethod.GET)
	public ModelAndView boardModifyView(@RequestParam("pet_number") int pet_number) {

		mav = new ModelAndView();
		mav = Pet_service.ModifyView(pet_number);

		return mav;
	}

	// 게시글 수정
	@RequestMapping(value = "/Pet_Modify")
	public ModelAndView boardModify(@ModelAttribute Pet_DTO pet_DTO, int pet_number)
			throws IllegalStateException, IOException {
		System.out.println("게시글번호?:" + pet_DTO.getPet_number());
		mav = new ModelAndView();
		String s = pet_DTO.getPet_name(); // 원본 문자열
		String s2;

		System.out.println("원본:    " + s);
		System.out.println(); // 줄바꿈
		// 문자열에서 모든 똠방각하 다 지우기
		s2 = s.replaceAll(",", "");
		System.out.println("삭제(3): " + s2); // 출력 결과: 가나다라ABCDE 가나다라ABCDE
		pet_DTO.setPet_name(s2);

		MultipartFile pet_profile_file = pet_DTO.getPet_profile_file(); // 펫 프로필 사진
		String fileName = pet_profile_file.getOriginalFilename();// 펫 프로필 사진
		String savePath = "C:\\Users\\user\\Desktop\\Petmily (2)\\src\\main\\webapp\\resources\\petUploadFile\\"
				+ fileName;

		MultipartFile pet_img_file = pet_DTO.getPet_profile_file(); // 펫 상세보기 이미지
		String pet_img = pet_img_file.getOriginalFilename();// 펫 상세보기 이미지
		String savePath2 = "C:\\Users\\user\\Desktop\\Petmily (2)\\src\\main\\webapp\\resources\\petUploadFile\\"
				+ pet_img;
//						
		// 수정하기
		if (!pet_profile_file.isEmpty()) {// 펫 프로필 사진
			pet_profile_file.transferTo(new File(savePath));
		}

		pet_DTO.setPet_profile(fileName);// 프로필사진

		if (!pet_img_file.isEmpty()) {// 펫 상세보기 이미지
			pet_img_file.transferTo(new File(savePath2));
		}

		pet_DTO.setPet_img(pet_img);// 펫 상세보기 이미지

		mav = Pet_service.pet_Modify(pet_DTO, pet_number);

		return mav;
	}

	// 관리자만 쓸수 있는 분양Q&A 대댓글 쓰기
	@RequestMapping(value = "/SmailcommentWrite")
	public @ResponseBody int SmailcommentWrite(@ModelAttribute Pet_DTO commentDTO,
			@RequestParam("smail_comment_PET_NUMBER") int smail_comment_PET_NUMBER) {

		System.out.println("smail_comment_PET_NUMBER은용? : " + commentDTO.getSmail_comment_PET_NUMBER());
		System.out.println("작성자는?" + commentDTO.getSmail_commentWriter());

		commentDTO.setSmail_comment_PET_NUMBER(smail_comment_PET_NUMBER);

		// 대댓글 db쓰기
		int writeResult = Pet_service.Smail_commentWrite(commentDTO);

		// List<Pet_DTO> SmailcommentList = Pet_service.SmailcommentList(commentDTO);

		return writeResult;
	}

	// 댓글 리스트 만들기
	@RequestMapping(value = "smail_commentList", method = RequestMethod.POST)
	public @ResponseBody List<Pet_DTO> smail_commentList(@ModelAttribute Pet_DTO commentDTO,
			@RequestParam("smail_comment_PET_NUMBER") int smail_comment_PET_NUMBER) {

		System.out.println("smail_comment_PET_NUMBER넘어와?" + commentDTO.getSmail_comment_PET_NUMBER());

		// commentDTO.setSmail_comment_PET_NUMBER(smail_comment_PET_NUMBER);
		// commentDTO.setNot_smail_commentNumber(not_smail_commentNumber);

		List<Pet_DTO> SmailcommentList = Pet_service.SmailcommentList(commentDTO, smail_comment_PET_NUMBER);

		return SmailcommentList;
	}

	// 펫 네임 (종류)별로 검색?
	@RequestMapping(value = "/Pet_name", method = RequestMethod.GET)
	public ModelAndView pet_name(@RequestParam("pet_name") String pet_name) {

		mav = new ModelAndView();
		mav = Pet_service.Pet_nameList(pet_name);

		return mav;

	}

}