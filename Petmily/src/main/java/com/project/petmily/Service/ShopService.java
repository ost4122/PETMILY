package com.project.petmily.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.project.petmily.DAO.MemberDAO;
import com.project.petmily.DAO.ShopDAO;
import com.project.petmily.DTO.Delivery_DTO;
import com.project.petmily.DTO.Images_DTO;
import com.project.petmily.DTO.Member_DTO;
import com.project.petmily.DTO.Shop_DTO;

@Service
public class ShopService {
	@Autowired
	private ShopDAO sdao;
	private ModelAndView mav;
	@Autowired
	private MemberDAO mdao;

	/* 용품 등록 */
	public ModelAndView Shop_Input(Images_DTO idto, Shop_DTO sdto, HttpServletResponse response) throws IOException {

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
	public ModelAndView Shop_List() {
		mav = new ModelAndView();
		
		/*
		PageDTO pageDTO = new PageDTO();
		int limit = 3;
		pageDTO.setPage(page);
		pageDTO.setLimit(limit);
		
		int startRow = (page-1)*limit+1;
		int endRow = page*limit;
		
		pageDTO.setStartRow(startRow);
		pageDTO.setEndRow(endRow);
		*/
		
		//현재 페이지에 보여줘야 하는 글만 DB에서 가져오기
		
		//전채 글 갯수 DB에서 가져오기
		//int listCount = sdao.listCount();
		
		//int maxPage = (int) ((double)listCount/limit +1);
		//int startPage = (((int)((double)page/10 + 0.9)) -1 ) * 10 + 1;
		
		/*
		int endPage = startPage + 10- 1;
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		
		
		pageDTO.setStartPage(startPage);
		pageDTO.setEndPage(endPage);
		pageDTO.setMaxPage(maxPage);
		*/
		
		List<Shop_DTO> itemList = sdao.Shop_List();
		mav.setViewName("PetShop");
		mav.addObject("ItemList", itemList );
		//mav.addObject("paging",pageDTO);
		
		
		return mav;
	}

	/* 용품 상세보기 */
	public ModelAndView Shop_View(int item_number) {
		mav = new ModelAndView();
		
		Shop_DTO result = sdao.Shop_View(item_number);
		List<String> multyImg = sdao.MultyImg(item_number);
		mav.addObject("multyImg", multyImg);
		mav.addObject("Item", result);
		mav.setViewName("ItemView");
		return mav;
	}

	 /* 강아지 용품목록 보기 */
	public ModelAndView puffyItem(String item_kind) {
		mav = new ModelAndView();
		
		List<Shop_DTO> result = sdao.puffyItem(item_kind);
		
		//System.out.println("★ puffyItem:" + result.get(0).getItem_name());
		
		mav.addObject("ItemList", result);
		mav.setViewName("PetShop");
		return mav;
	}

	/* 강아지 용품 카테고리 검색*/
	public ModelAndView puffyItems(Shop_DTO sdto) {
		
		mav = new ModelAndView();
		
		List<Shop_DTO> result = sdao.puffyItems(sdto);
		
		mav.addObject("ItemList", result);
		mav.setViewName("PetShop");
		return mav;
	}

	/* 용품 구매 */
	public ModelAndView ItemBuy(int item_number,String id) {
		mav = new ModelAndView();
		
		int delivery = 20000; // 배송비
		
		Member_DTO member = mdao.memberView(id);
		Shop_DTO item = sdao.Shop_View(item_number);
		int totalPrice = delivery + item.getItem_price();
		
		String address = 
		member.getAddress_a() + member.getAddress_b() + member.getAddress_c();
		
		member.setAddress(address);
		
		mav.addObject("member", member);
		mav.addObject("item", item);
		
		mav.addObject("totalPrice", totalPrice); // 배송비 + 용품금액 
		
		mav.setViewName("ItemBuy");
		
		return mav;
	}

	/* 배송 신청 */
	public ModelAndView Item_Delivery(Delivery_DTO ddto) {
		mav = new ModelAndView();
		
		int result = sdao.Item_Delivery(ddto);
		
		int totalPrice = ddto.getItem_price()+20000;
		
		if(result > 0) {
			sdao.soldCount(ddto.getItem_number());
			mav.addObject("totalPrice",totalPrice);
			mav.addObject("buy",ddto );
			mav.setViewName("buySuccess");
		}else {
			
		}
		return mav;
	}
	
	// 2019 - 09 - 24 

	/* 구매 내역 */
	public ModelAndView purchase(String id) {
		mav = new ModelAndView();
		
		List<Delivery_DTO> result = sdao.purchase(id);
		
		mav.addObject("purchase", result);
		mav.setViewName("Purchase");
		
		return mav;
	}

}
