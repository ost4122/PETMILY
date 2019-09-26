package com.project.petmily.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.project.petmily.DAO.Pet_DAO;
import com.project.petmily.DTO.Pet_DTO;

@Service
public class Pet_Service {
	private ModelAndView mav;

	@Autowired
	private Pet_DAO pet_DAO;

	public ModelAndView admin_pet_writ(Pet_DTO pet_DTO) {

		mav = new ModelAndView();
		int writResult = pet_DAO.admin_pet_writ(pet_DTO);

		if (writResult > 0) {
			mav.setViewName("home"); // 수정

		} else {
			mav.setViewName("admin_pet_WriteJSP"); // 수정
		}
		return mav;
	}

	// 미분양된 강아지 all리스트
	public ModelAndView Pet_Dog_allList(int page) {

		mav = new ModelAndView();

		Pet_DTO paging = new Pet_DTO();
		String pet_kategorie = "강아지";
		paging.setPet_kategorie(pet_kategorie);

		if (page == 0) {
			page = 1;
		} else {
			paging.setPage(page);
		}
		int limit = 12;

		int Pet_Dog_allList_Count = pet_DAO.Pet_Dog_allList_Count(pet_kategorie);

		System.out.println("Pet_Dog_allList_Count값 : " + Pet_Dog_allList_Count);
		// 출력할 범위값(db명령할때 숫자값) 계산하기

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		// 내가 보고자 하는 페이지에 글을 가져오려면
		// List<Pagingdto> boardList = boardDAO.boardListPaging(paging); 에서
		// List<Boarddto> boardList = boardDAO.boardListPaging(paging);로 해도된다.

		List<Pet_DTO> Pet_Dog_allList = pet_DAO.Pet_Dog_allList_Paging(paging);

		int maxPage = (int) ((double) Pet_Dog_allList_Count / limit + 0.9);
		int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		paging.setBoardCount(Pet_Dog_allList_Count);

		mav.addObject("paging", paging);
		mav.addObject("Pet_Dog_allList", Pet_Dog_allList);
		mav.setViewName("dog_ListJsp");

		return mav;
	}

	// 강아지 all 상세보기
	public ModelAndView Pet_Dog_View(int pet_number, int page) {
		mav = new ModelAndView();

		Pet_DTO paging = new Pet_DTO();

		// DTO객체 선언
		// CommentDTO paging =new CommentDTO();
		if (page == 0) {
			page = 1;
		} else {
			paging.setPage(page);
		}
		int limit = 5;

		int commentCount = pet_DAO.commentCount();

		System.out.println("commentCount값 : " + commentCount);
		// 출력할 범위값(db명령할때 숫자값) 계산하기

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setComment_PET_NUMBER(pet_number);

		paging.setNot_smail_commentNumber(pet_number);

		List<Pet_DTO> commentList = pet_DAO.commentList(paging);

		int maxPage = (int) ((double) commentCount / limit + 0.9);
		int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		Pet_DTO view = pet_DAO.Pet_Dog_View(pet_number);

		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);

		paging.setCommentCount(commentCount);

		// List<Pet_DTO> Smail_commentList = pet_DAO.Smail_commentList(paging);

		// BoardDTO boardView = boardDAO.boardView(boardnum);

		// mav.addObject("Smail_commentList", Smail_commentList); //대댓글
		mav.addObject("commentList", commentList); // 댓글
		mav.addObject("view", view);
		mav.addObject("paging", paging);

		mav.setViewName("Pet_Dog_ViewJSP");

		return mav;

	}

	// 분양 추천
	public String Hitto(int pet_number) {
		int hit = pet_DAO.Hitto(pet_number);
		String hitplus = null;

		if (hit > 0) {
			hitplus = "1";

		} else {
			hitplus = "0";
		}
		return hitplus;
	}

	// 분양 추천 취소
	public String likecancle(int pet_number) {
		int hit = pet_DAO.Hitcancle(pet_number);
		String hitplus = null;

		if (hit == 0) {
			hitplus = "0";

		} else {
			hitplus = "1";
		}
		return hitplus;
	}

	// 대형견 리스트
	public ModelAndView Pet_Dog_Big(int page) {

		mav = new ModelAndView();

		Pet_DTO paging = new Pet_DTO();
		// Pet_DTO pet = new Pet_DTO();
		String pet_size = "대형견";
		paging.setPet_size(pet_size);

		if (page == 0) {
			page = 1;
		} else {
			paging.setPage(page);
		}
		int limit = 12;

		int Pet_Dog_Big_Count = pet_DAO.Pet_Dog_Big_Count(pet_size);

		System.out.println("Pet_Dog_Big_Count값 : " + Pet_Dog_Big_Count);
		// 출력할 범위값(db명령할때 숫자값) 계산하기

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		// 내가 보고자 하는 페이지에 글을 가져오려면
		// List<Pagingdto> boardList = boardDAO.boardListPaging(paging); 에서
		// List<Boarddto> boardList = boardDAO.boardListPaging(paging);로 해도된다.

		// HashMap<String,Object> pp = new HashMap<>();
		// pp.put("ㅋ", pet);
		// pp.put("ㅎ", paging);

		List<Pet_DTO> Pet_Dog_Big = pet_DAO.Pet_Dog_Big(paging);

		int maxPage = (int) ((double) Pet_Dog_Big_Count / limit + 0.9);
		int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		paging.setBoardCount(Pet_Dog_Big_Count);

		mav.addObject("paging", paging);
		mav.addObject("Pet_Dog_Big", Pet_Dog_Big);
		mav.setViewName("dog_bigJsp");

		return mav;
	}

	// 중형견 리스트
	public ModelAndView Pet_Dog_Medium(int page) {

		mav = new ModelAndView();

		// Paging_DTO paging =new Paging_DTO();
		Pet_DTO paging = new Pet_DTO();
		String size = "중형견";
		paging.setPet_size(size);
		if (page == 0) {
			page = 1;
		} else {
			paging.setPage(page);
		}
		int limit = 12;

		int Pet_Dog_Medium_Count = pet_DAO.Pet_Dog_Medium_Count(size);

		System.out.println("Pet_Dog_Medium_Count값 : " + Pet_Dog_Medium_Count);
		// 출력할 범위값(db명령할때 숫자값) 계산하기

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		// 내가 보고자 하는 페이지에 글을 가져오려면
		// List<Pagingdto> boardList = boardDAO.boardListPaging(paging); 에서
		// List<Boarddto> boardList = boardDAO.boardListPaging(paging);로 해도된다.

		List<Pet_DTO> Pet_Dog_Medium = pet_DAO.Pet_Dog_Medium(paging);

		int maxPage = (int) ((double) Pet_Dog_Medium_Count / limit + 0.9);
		int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		paging.setBoardCount(Pet_Dog_Medium_Count);

		mav.addObject("paging", paging);
		mav.addObject("Pet_Dog_Medium", Pet_Dog_Medium);
		mav.setViewName("Pet_Dog_MediumJsp");

		return mav;
	}

	// 소형견 리스트
	public ModelAndView Pet_Dog_Small(int page) {

		mav = new ModelAndView();
		Pet_DTO paging = new Pet_DTO();
		String size = "소형견";
		paging.setPet_size(size);
		// Paging_DTO paging =new Paging_DTO();

		if (page == 0) {
			page = 1;
		} else {
			paging.setPage(page);
		}
		int limit = 12;

		int Pet_Dog_Small_Count = pet_DAO.Pet_Dog_Small_Count(size);

		System.out.println("Pet_Dog_Small_Count값 : " + Pet_Dog_Small_Count);
		// 출력할 범위값(db명령할때 숫자값) 계산하기

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		// 내가 보고자 하는 페이지에 글을 가져오려면
		// List<Pagingdto> boardList = boardDAO.boardListPaging(paging); 에서
		// List<Boarddto> boardList = boardDAO.boardListPaging(paging);로 해도된다.

		List<Pet_DTO> Pet_Dog_Small = pet_DAO.Pet_Dog_Small(paging);

		int maxPage = (int) ((double) Pet_Dog_Small_Count / limit + 0.9);
		int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		paging.setBoardCount(Pet_Dog_Small_Count);

		mav.addObject("paging", paging);
		mav.addObject("Pet_Dog_Small", Pet_Dog_Small);
		mav.setViewName("Pet_Dog_SmallJsp");

		return mav;
	}

	// 고양이리스트
	public ModelAndView Pet_Cat_List(int page) {

		mav = new ModelAndView();
		Pet_DTO paging = new Pet_DTO();
		String pet_kategorie = "고양이";
		paging.setPet_kategorie(pet_kategorie);
		// Paging_DTO paging =new Paging_DTO();

		if (page == 0) {
			page = 1;
		} else {
			paging.setPage(page);
		}
		int limit = 12;

		int Pet_Cat_List_Count = pet_DAO.Pet_Cat_List_Count(pet_kategorie);

		System.out.println("Pet_Dog_Big_Count값 : " + Pet_Cat_List_Count);
		// 출력할 범위값(db명령할때 숫자값) 계산하기

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		// 내가 보고자 하는 페이지에 글을 가져오려면
		// List<Pagingdto> boardList = boardDAO.boardListPaging(paging); 에서
		// List<Boarddto> boardList = boardDAO.boardListPaging(paging);로 해도된다.

		List<Pet_DTO> Pet_Cat_List = pet_DAO.Pet_Cat_List(paging);

		int maxPage = (int) ((double) Pet_Cat_List_Count / limit + 0.9);
		int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		paging.setBoardCount(Pet_Cat_List_Count);

		mav.addObject("paging", paging);
		mav.addObject("Pet_Cat_List", Pet_Cat_List);
		mav.setViewName("Pet_Cat_ListJsp");

		return mav;
	}

	// 파충류 리스트
	public ModelAndView Pet_Reptile_List(int page) {

		mav = new ModelAndView();
		Pet_DTO paging = new Pet_DTO();
		String pet_kategorie = "파충류";
		paging.setPet_kategorie(pet_kategorie);
		// Paging_DTO paging =new Paging_DTO();

		if (page == 0) {
			page = 1;
		} else {
			paging.setPage(page);
		}
		int limit = 12;

		int Pet_Reptile_List_Count = pet_DAO.Pet_Reptile_List_Count(pet_kategorie);

		System.out.println("Pet_Reptile_List_Count값 : " + Pet_Reptile_List_Count);
		// 출력할 범위값(db명령할때 숫자값) 계산하기

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		// 내가 보고자 하는 페이지에 글을 가져오려면
		// List<Pagingdto> boardList = boardDAO.boardListPaging(paging); 에서
		// List<Boarddto> boardList = boardDAO.boardListPaging(paging);로 해도된다.

		List<Pet_DTO> Pet_Reptile_List = pet_DAO.Pet_Reptile_List(paging);

		int maxPage = (int) ((double) Pet_Reptile_List_Count / limit + 0.9);
		int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		paging.setBoardCount(Pet_Reptile_List_Count);

		mav.addObject("paging", paging);
		mav.addObject("Pet_Reptile_List", Pet_Reptile_List);
		mav.setViewName("Pet_Reptile_ListJsp");

		return mav;
	}

	// 댓글 작성 메소드
	public int commentWriter(Pet_DTO commentDTO) {

		return pet_DAO.commentWrite(commentDTO);
	}

	// db에서 댓글 가져오는 메소드 (페이징 처리 해버리장 )
	public List<Pet_DTO> commentList(Pet_DTO paging, int page) {

		if (page == 0) {
			page = 1;
		} else {
			paging.setPage(page);
		}
		int limit = 5;

		int commentCount = pet_DAO.commentCount();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		// paging.setComment_PET_NUMBER(paging.getComment_PET_NUMBER());??

		int maxPage = (int) ((double) commentCount / limit + 0.9);
		int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);

		paging.setCommentCount(commentCount);

		paging.setSmail_comment_PET_NUMBER(paging.getCommentNumber());

		List<Pet_DTO> Smail_commentList = pet_DAO.Smail_commentList(paging);

		return pet_DAO.commentList(paging);
	}

	// 댓글 수정하기
	public String commentModifyUpdate(Pet_DTO commentDTO) {
		int updateResult = pet_DAO.commentModifyUpdate(commentDTO);
		String update = null;
		if (updateResult > 0) {
			update = "OK";
			System.out.println("update값이뭔데:" + update);
		} else {
			update = "NO";
		}
		return update;
	}

	// 댓글 삭제하기
	public String commentDelete(int commentNumber) {

		int result = pet_DAO.commentDelete(commentNumber);
		String deleteResult = null;
		if (result > 0) {
			deleteResult = "OK";
		} else {
			deleteResult = "NO";
		}
		return deleteResult;
	}

	// 분양글 삭제
	public String pet_delete(int pet_number) {

		int result = pet_DAO.pet_Delete(pet_number);
		String deleteResult = null;
		if (result > 0) {
			deleteResult = "OK";
		} else {
			deleteResult = "NO";
		}
		return deleteResult;
	}

	// 분양글 수정전 게시글 불러오기
	public ModelAndView ModifyView(int pet_number) {
		mav = new ModelAndView();
		Pet_DTO modifyView = pet_DAO.modifyView(pet_number);
		mav.addObject("Pet_modifyView", modifyView);
		mav.setViewName("Pet_modifyViewJSP");

		return mav;
	}

	// 분양글 수정
	public ModelAndView pet_Modify(Pet_DTO pet_DTO, int pet_number) {
		mav = new ModelAndView();
		int updateResult = pet_DAO.pet_Modify(pet_DTO);
		Pet_DTO update = null;
		if (updateResult > 0) {
			// mav.setViewName("redirect:/Pet_Dog_ViewJSP?pet_number="+pet_DTO.getPet_number());
			// mav.addObject("update",update);
			update = pet_DAO.Pet_Dog_View(pet_number);
			mav.setViewName("dog_ListJsp");
		} else {
			mav.setViewName("Pet_modifyViewJSP");
		}
		return mav;
	}

	// 대댓글 쓰기
	public int Smail_commentWrite(Pet_DTO commentDTO) {

		return pet_DAO.Smail_commentWrite(commentDTO);
	}

	// 대댓글 리스트
	public List<Pet_DTO> SmailcommentList(Pet_DTO pet_DTO, int smail_comment_PET_NUMBER) {

		pet_DTO.setSmail_comment_PET_NUMBER(smail_comment_PET_NUMBER);

		return pet_DAO.Smail_commentList(pet_DTO);
	}

	// 펫 네임 (종류)별로 검색?

	public ModelAndView Pet_nameList(String pet_name) {

		mav = new ModelAndView();

		Pet_DTO petdto = new Pet_DTO();

		petdto.setPet_name(pet_name);

		List<Pet_DTO> Pet_nameList = pet_DAO.Pet_NameList(pet_name);

		mav.addObject("Pet_nameList", Pet_nameList);

		mav.setViewName("Pet_nameListJSP");

		return mav;
	}

}
