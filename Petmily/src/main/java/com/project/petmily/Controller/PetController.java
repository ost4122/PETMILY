package com.project.petmily.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PetController {
	
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

	



}