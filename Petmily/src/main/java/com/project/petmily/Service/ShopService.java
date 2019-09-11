package com.project.petmily.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.project.petmily.DAO.ShopDAO;
import com.project.petmily.DTO.ShopDTO;

@Service
public class ShopService {
	@Autowired
	private ShopDAO sdao;
	private ModelAndView mav;
	
	/* 용품 등록 */
	public ModelAndView Shop_Input(List<String> fileNames, ShopDTO sdto, HttpServletResponse response) throws IOException {
		
		mav = new ModelAndView();
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int result = sdao.Shop_Input(sdto);
		
		if(result > 0) {
			mav.setViewName("go_home");
		}else {
			out.print("<script>alert('등록 실패 !!')");
			out.print("history.go(-1)</script>");
		}
		
		
		return mav;
	}
	
}
