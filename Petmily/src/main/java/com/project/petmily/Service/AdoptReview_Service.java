package com.project.petmily.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.project.petmily.DAO.AdoptReview_DAO;
import com.project.petmily.DTO.AdoptReview_DTO;
import com.project.petmily.DTO.Page_DTO;

@Service
public class AdoptReview_Service {

	@Autowired
	private AdoptReview_DAO ar_DAO;
	private ModelAndView mav;

	/* 후기글 쓰기 */
	public ModelAndView ar_write(AdoptReview_DTO arDTO) {

		mav = new ModelAndView();

		int writeResult = ar_DAO.ar_write(arDTO);

		if (writeResult > 0) {
			mav.setViewName("redirect:/ar_list?page=1");
		} else {
			mav.setViewName("AdoptReview_Write");
		}
		return mav;
	}

	/* 후기글 목록(페이징처리) */
	public ModelAndView ar_list(int page) {

		mav = new ModelAndView();
		Page_DTO pageDTO = new Page_DTO();
		int limit = 10;
		pageDTO.setPage(page);
		pageDTO.setLimit(limit);

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;
		pageDTO.setStartRow(startRow);
		pageDTO.setEndRow(endRow);

		// 현재 페이지에 보여줘야 하는 글만 DB에서 가져오기
		List<AdoptReview_DTO> arList = ar_DAO.ar_list(pageDTO);

		// 전체 글 갯수 DB에서 가져오기
		int listCount = ar_DAO.listCount();

		// 페이지 계산
		int maxPage = (int) ((double) listCount / limit + 0.9);
		int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;// 뒷자리 수가 무조건 1이 나오게끔하는 식
		int endPage = startPage + 10 - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		pageDTO.setStartPage(startPage);
		pageDTO.setEndPage(endPage);
		pageDTO.setMaxPage(maxPage);
		
		mav.addObject("arList", arList);
		mav.addObject("paging", pageDTO);
		mav.setViewName("AdoptReview_List");
   
		return mav;
	}

	/* 후기글 상세보기 */
	public AdoptReview_DTO ar_view(int ar_number) {
		
		mav = new ModelAndView();
		
		AdoptReview_DTO arView = ar_DAO.ar_view(ar_number);

		return arView;
	}


	

}
