package com.project.petmily.Controller;

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
import org.springframework.web.servlet.ModelAndView;
import com.project.petmily.DTO.Member_DTO;
import com.project.petmily.Service.Member_Service;

@Controller
public class MemberController {

	@Autowired
	private Member_Service memberService;

	private ModelAndView mav;

	private static final Logger Logger = LoggerFactory.getLogger(MemberController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	/* �ΰ� Ŭ�� */
	@RequestMapping(value = "go_home")
	public String go_home() {

		return "home";
	}

	/* join Ŭ�� */
	@RequestMapping(value = "/JoinForm")
	public String home1() {
		return "memberjoin";
	}

	// login Ŭ��
	@RequestMapping(value = "/LoginForm", method = RequestMethod.POST)
	public String home2() {
		return "LoginForm";
	}

	// ȸ������ �ϰ���
	@RequestMapping(value = "/memberJoin", method = RequestMethod.POST)
	public ModelAndView memberJoin(@ModelAttribute Member_DTO memberDTO) {
		// ModelAndView ��ü�� �����Ѵ�.
		mav = new ModelAndView();

		mav = memberService.memberJoin(memberDTO);

		return mav;
	}

	@RequestMapping(value = "/navermemberjoin", method = RequestMethod.POST)
	public ModelAndView navermemberjoin(@ModelAttribute Member_DTO memberDTO) {
		// ModelAndView ��ü�� �����Ѵ�.

		mav = memberService.navermemberjoin(memberDTO);

		return mav;
	}

	@RequestMapping(value = "/JoinFile", method = RequestMethod.POST)
	public ModelAndView JoinFile(@ModelAttribute Member_DTO memberDTO)
			throws Exception, IllegalStateException, IOException {
		mav = new ModelAndView();
		String email_1 = memberDTO.getEmail_1();
		String email_2 = memberDTO.getSelectemail();
		String email_3 = "@";
		String email = email_1 + email_3 + email_2;
		memberDTO.setEmail(email);
		mav = memberService.send_mail(memberDTO);
		mav = memberService.memberJoin(memberDTO);
		return mav;
	}

	// ȸ�� ����
	@RequestMapping(value = "/approval_member.do", method = RequestMethod.POST)
	public void approval_member(@ModelAttribute Member_DTO memberDTO, HttpServletResponse response) throws Exception {
		memberService.approval_member(memberDTO, response);
	}

	@RequestMapping(value = "/Login_Login", method = RequestMethod.POST)
	public ModelAndView Login_Login(@ModelAttribute Member_DTO memberDTO, HttpServletResponse response)
			throws IOException {

		// ModelAndView ��ü�� �����Ѵ�.
		mav = new ModelAndView();

		mav = memberService.Login_Login(memberDTO, response);

		return mav;
	}

//	 @RequestMapping("/logout")
//	    public ModelAndView logout(HttpSession session) {
//	        session.invalidate();
//	        ModelAndView mv = new ModelAndView("redirect:/");
//	        return mv;
//	    }
	@RequestMapping(value = "/Join_IdCheck", method = RequestMethod.POST)
	public @ResponseBody String Join_IdCheck(@RequestParam("id") String id) {
		String resultMsg = memberService.Join_IdCheck(id);
		return resultMsg;
	}

	@RequestMapping(value = "/nameOverlap", method = RequestMethod.POST)
	public @ResponseBody String nameOverlap(@RequestParam("name") String name) {
		String resultMsg = memberService.nameOverlap(name);
		return resultMsg;
	}

	@RequestMapping(value = "/memberList", method = RequestMethod.GET)
	public ModelAndView memberList() {

		mav = new ModelAndView();

		mav = memberService.memberList();

		return mav;
	}

	@RequestMapping(value = "/memberListAjax", method = RequestMethod.GET)
	public @ResponseBody List<Member_DTO> memberListAjax() {
		List<Member_DTO> memberList = memberService.memberListAjax();
		return memberList;

	}

	// ��й�ȣ ã�� ��
	@RequestMapping(value = "/find_pw_fromJSP")
	public String find_pw_form() throws Exception {
		return "find_pw_form";
	}

	// ��й�ȣ ã��
	@RequestMapping(value = "/Login_PasswordFind")
	public void Login_PasswordFind(@ModelAttribute Member_DTO memberDTO, HttpServletResponse response,
			@RequestParam("email_2") String email_2) throws Exception {
		String email_1 = memberDTO.getEmail_1();
		String email_3 = "@";
		String email = email_1 + email_3 + email_2;
		memberDTO.setEmail(email);
		
		memberService.Login_PasswordFind(memberDTO, response);
	}

	// ���̵� ã�� ��
	@RequestMapping(value = "/find_id_fromJSP")
	public String find_id_form() throws Exception {
		return "find_id_form";
	}

	// ���̵� ã��
	@RequestMapping(value = "/Login_IdFind")
	public void Login_IdFind(@ModelAttribute Member_DTO memberDTO, HttpServletResponse response,
			@RequestParam("email_2") String email_2) throws Exception {
		System.out.println("�ڡڡ�:"+ memberDTO.getName());
		String email_1 = memberDTO.getEmail_1();
		memberDTO.getEmail_2();
		String email_3 = "@";
		String email = email_1 + email_3 + email_2;
		memberDTO.setEmail(email);
		memberService.find_id(memberDTO, response);
	}

}
