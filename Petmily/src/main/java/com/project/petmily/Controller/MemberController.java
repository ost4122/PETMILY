package com.project.petmily.Controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.project.petmily.DTO.MemberDTO;
import com.project.petmily.Service.MemberService;
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	private ModelAndView mav;
	
	private static final Logger Logger = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	   public String home(Locale locale, Model model) {
		Logger.info("Welcome home! The client locale is {}.", locale);
	      
	      Date date = new Date();
	      DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
	      
	      String formattedDate = dateFormat.format(date);
	      
	      model.addAttribute("serverTime", formattedDate );
	      
	      return "home";
	   }
	   /* 로고 클릭 */ 
	   @RequestMapping(value = "go_home")
	   public String go_home() {
	      
	      return "home";
	   }
	    /*join 클릭*/
	@RequestMapping(value="/JoinForm")
	public String home1(){
		return "memberjoin";
	}
	//login 클릭
	@RequestMapping(value="/LoginForm",method=RequestMethod.POST)
	public String home2(){
		return "LoginForm";
	}
	//회원가입 하고나서
	@RequestMapping(value="/memberJoin",method=RequestMethod.POST)
	public ModelAndView memberJoin(@ModelAttribute MemberDTO memberDTO) {
		//ModelAndView 객체를 생성한다.
		mav=new ModelAndView();
		
		mav=memberService.memberJoin(memberDTO);
		
		return mav;
	}
	@RequestMapping(value="/JoinFile",method=RequestMethod.POST)
	public ModelAndView JoinFile(@ModelAttribute MemberDTO memberDTO) throws Exception ,IllegalStateException, IOException {
		mav=new ModelAndView();
	MultipartFile joinFile = memberDTO.getJoinFile();
	String fileName = joinFile.getOriginalFilename();
	String savePath = "C://Users//user//Downloads//Petmily//src//main//webapp//resources//profile//"+fileName;
	if(!joinFile.isEmpty()) {
		joinFile.transferTo(new File(savePath));
	}
	memberDTO.setProfileimage(fileName);
	String email_1 = memberDTO.getEmail_1();
	String email_2 = memberDTO.getSelectemail();
	String email_3 = "@";
	String email=email_1 +email_3+email_2;
	memberDTO.setEmail(email);
	mav= memberService.send_mail(memberDTO);
	mav=memberService.memberJoin(memberDTO);
		return mav;
	}
	// 회원 인증
	@RequestMapping(value = "/approval_member.do", method = RequestMethod.POST)
	public void approval_member(@ModelAttribute MemberDTO memberDTO, HttpServletResponse response) throws Exception{
	memberService.approval_member(memberDTO, response);
			}

	@RequestMapping(value="/memberlogin",method=RequestMethod.POST)
	public ModelAndView memberLogin(@ModelAttribute MemberDTO memberDTO,HttpServletResponse response) throws IOException {
		
		//ModelAndView 객체를 생성한다.
		mav=new ModelAndView();
		
		mav=memberService.memberLogin(memberDTO, response);
		
		return mav;
	}
//	 @RequestMapping("/logout")
//	    public ModelAndView logout(HttpSession session) {
//	        session.invalidate();
//	        ModelAndView mv = new ModelAndView("redirect:/");
//	        return mv;
//	    }
	@RequestMapping(value="/idOverlap",method=RequestMethod.POST)
	public @ResponseBody String idOverlap(@RequestParam("id")String id){
		String resultMsg=memberService.idOverlap(id);
		return resultMsg;
	}
	@RequestMapping(value="/nameOverlap",method=RequestMethod.POST)
	public @ResponseBody String nameOverlap(@RequestParam("name")String name){
		String resultMsg=memberService.nameOverlap(name);
		return resultMsg;
	}
	@RequestMapping(value="/memberList",method=RequestMethod.GET)
	public ModelAndView memberList() {
		
		mav=new ModelAndView();
		
		mav=memberService.memberList();
		
		return mav;
	}
	@RequestMapping(value="/memberListAjax",method=RequestMethod.GET)
	public @ResponseBody List<MemberDTO> memberListAjax(){
		List<MemberDTO> memberList =memberService.memberListAjax();
		return memberList;
	
	}
	@RequestMapping(value="/memberView",method=RequestMethod.GET)
	public ModelAndView memberView(@RequestParam("id")String id){
		
		mav=new ModelAndView();
		
		mav=memberService.memberView(id);
		
		return mav;
	}
	@RequestMapping(value="/memberModify",method=RequestMethod.GET)
	public ModelAndView memberModify(@RequestParam("id")String id){
		
		mav=new ModelAndView();
		mav=memberService.memberModify(id);
		return mav;
	}
	@RequestMapping(value="/memberModify2",method=RequestMethod.POST)
	public String memberModify2(@ModelAttribute MemberDTO memberDTO,HttpServletResponse response) throws IllegalStateException, IOException{
		
		mav=new ModelAndView();
		MultipartFile joinFile = memberDTO.getJoinFile();
		String fileName = joinFile.getOriginalFilename();
		String savePath = "D://dev//spring//Memberboard//src//main//webapp//resources//profile//"+fileName;
		if(!joinFile.isEmpty()) {
			joinFile.transferTo(new File(savePath));
		}
		memberDTO.setProfileimage(fileName);
		mav=memberService.memberModify2(memberDTO);
		return "redirect:/memberView?id="+memberDTO.getId();
	}
	@RequestMapping(value="/memberDelete",method=RequestMethod.GET)
	public ModelAndView memberDelete(@RequestParam("id")String id){
		
		mav=new ModelAndView();
		mav=memberService.memberDelete(id);
		return mav;
	}
	
	
	// 비밀번호 찾기 폼
			@RequestMapping(value = "/find_pw_fromJSP")
			public String find_pw_form() throws Exception{
				return "find_pw_form";
			}
			
			// 비밀번호 찾기
			@RequestMapping(value = "/find_pw.do")
			public void find_pw(@ModelAttribute MemberDTO memberDTO, HttpServletResponse response, @RequestParam("email_2") String email_2) throws Exception{
				String email_1 = memberDTO.getEmail_1();
				String email_3 = "@";
				String email=email_1 +email_3+email_2;
				memberDTO.setEmail(email);
			memberService.find_pw(memberDTO,response);
			}
			//아이디 찾기 폼 
			@RequestMapping(value = "/find_id_fromJSP")
			public String find_id_form() throws Exception{
				return "find_id_form";
			}
			
			// 아이디 찾기
			@RequestMapping(value = "/find_id.do")
			public void find_id(@ModelAttribute MemberDTO memberDTO, HttpServletResponse response,@RequestParam("email_2") String email_2) throws Exception{
				String email_1 = memberDTO.getEmail_1();
				String email_3 = "@";
				String email=email_1 +email_3+email_2;
				memberDTO.setEmail(email);
				memberDTO.getId();
			memberService.find_id(memberDTO,response);
			}
				
}
