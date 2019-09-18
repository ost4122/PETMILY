package com.project.petmily.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PetmilyController {
	
	/* 회사 소개 클릭 */
	@RequestMapping(value = "aboutUs")
	public String uboutUs() {
		
		return "AboutUs";
	}
	
	
	/* 결제 성공 */ 
	@RequestMapping(value = "paySucess")
	public String paySucess() {
		
		return "paySucess";
	}
	
	/* 홈으로 가기 */ 
	@RequestMapping(value = "goHome")
	public String go_home() {
		
		return "home";
	}
	
	/*맵 테스트*/
	@RequestMapping(value = "kakaoMap")
	public String kakaoMap() {
		
		return "kakaoMap";
	}
}
