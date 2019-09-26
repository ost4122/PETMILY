package com.project.petmily.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.petmily.DTO.Member_DTO;
import com.project.petmily.Service.Member_Service;

@Controller
public class MypageController {
	
	@Autowired
	private Member_Service memberService;
	
	private ModelAndView mav;
	//���������� �̵�
	@RequestMapping(value = "/Mypage")
	public String mypage(@ModelAttribute Member_DTO memberDTO,HttpServletResponse response) {
		
		
		return "mypage";
	}
	@RequestMapping(value = "/memberdelete")
	public String memberdelete(@ModelAttribute Member_DTO memberDTO,HttpServletResponse response) {
		
		
		return "memberDelete";
	}
	//ȸ�� Ż��
	@RequestMapping(value="/Mypage_Withdrawal",method=RequestMethod.POST)
	public ModelAndView memberDelete(@RequestParam("id")String id){
		
		mav=new ModelAndView();
		mav=memberService.memberDelete(id);
		return mav;
	}
	//ȸ�� ���� ����
	@RequestMapping(value="/Mypage_MyData")
	public ModelAndView memberView(@RequestParam("id")String id){
		
		mav=new ModelAndView();
		System.out.println("���Ǿ��̵�"+id);
		mav=memberService.memberView(id);
		return mav;
	}
	//ȸ�� ���� ����
	@RequestMapping(value="/Mypage_MyDataModify")
	public ModelAndView memberModify(@ModelAttribute Member_DTO memberDTO,@RequestParam("id")String id){
		
		mav=new ModelAndView();
		mav=memberService.memberModify(id);
		return mav;
	}
	@RequestMapping(value="/memberModify2",method=RequestMethod.POST)
	public String memberModify2(@ModelAttribute Member_DTO memberDTO,HttpServletResponse response) throws IllegalStateException, IOException{
		
		mav=new ModelAndView();
		String email_1 = memberDTO.getEmail_1();
		String email_2 = memberDTO.getSelectemail();
		String email_3 = "@";
		String email = email_1 + email_3 + email_2;
		memberDTO.setEmail(email);
		mav=memberService.memberModify2(memberDTO);
		return "redirect:/Mypage_MyData?id="+memberDTO.getId();
	}
}
