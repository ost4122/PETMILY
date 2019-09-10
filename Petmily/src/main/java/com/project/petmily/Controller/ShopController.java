package com.project.petmily.Controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.petmily.DTO.ShopDTO;

@Controller
public class ShopController {
	
	private ModelAndView mav;
	
	// 용품 등록 페이지
	@RequestMapping(value = "/Shop_InputPage")
	public String Shop_InputPage() {

		
		return "Shop_Input";
	}
	
	// 용품 등록 
	@RequestMapping(value = "/Shop_Input")
	public ModelAndView Shop_Input(@ModelAttribute ShopDTO sdto) {

		
		return mav;
	}
}
