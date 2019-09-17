package com.project.petmily.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.project.petmily.DAO.ShopDAO;
import com.project.petmily.DTO.ImagesDTO;
import com.project.petmily.DTO.PageDTO;
import com.project.petmily.DTO.ShopDTO;

@Service
public class ShopService {
	@Autowired
	private ShopDAO sdao;
	private ModelAndView mav;

	/* 용품 등록 */
	public ModelAndView Shop_Input(ImagesDTO idto, ShopDTO sdto, HttpServletResponse response) throws IOException {

		mav = new ModelAndView();

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int result = sdao.Shop_Input(sdto);

		// 다중파일 DataBase 저장

		for (int i = 0; i < idto.getImg().size(); i++) {
			
			idto.setMultiImg(null);
			idto.setMultiImg(idto.getImg().get(i));

			sdao.shop_imges(idto);

		}
		//

		if (result > 0) {
			/*
			 * out.print("<script>alert('등록 성공 !!')");
			 * out.print("location.href='Shop_List?page=0'</script>");
			 */
			mav.setViewName("redirect:/Shop_List?page=0");
		} else {
			out.print("<script>alert('등록 실패 !!')");
			out.print("history.go(-1)</script>");
		}

		return mav;
	}

	
	/* 용품 목록 */
	public ModelAndView Shop_List(int page) {
		mav = new ModelAndView();
		
		PageDTO pageDTO = new PageDTO();
		int limit = 3;
		pageDTO.setPage(page);
		pageDTO.setLimit(limit);
		
		int startRow = (page-1)*limit+1;
		int endRow = page*limit;
		
		pageDTO.setStartRow(startRow);
		pageDTO.setEndRow(endRow);
		
		
		//현재 페이지에 보여줘야 하는 글만 DB에서 가져오기
		List<ShopDTO> itemList = sdao.Shop_List(pageDTO);
		
		//전채 글 갯수 DB에서 가져오기
		int listCount = sdao.listCount();
		
		int maxPage = (int) ((double)listCount/limit +1);
		int startPage = (((int)((double)page/10 + 0.9)) -1 ) * 10 + 1;
		
		int endPage = startPage + 10- 1;
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		
		pageDTO.setStartPage(startPage);
		pageDTO.setEndPage(endPage);
		pageDTO.setMaxPage(maxPage);
		
		mav.setViewName("PetShop");
		mav.addObject("ItemList", itemList );
		mav.addObject("paging",pageDTO);
		
		
		return mav;
	}


	public ModelAndView Shop_View(int item_number) {
		mav = new ModelAndView();
		
		ShopDTO result = sdao.Shop_View(item_number);
		List<String> multyImg = sdao.MultyImg(item_number);
		mav.addObject("multyImg", multyImg);
		mav.addObject("Item", result);
		mav.setViewName("ItemView");
		return mav;
	}

}
