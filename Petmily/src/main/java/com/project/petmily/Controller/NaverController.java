package com.project.petmily.Controller;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;

/**
 * Handles requests for the application home page.
 */
@Controller
public class NaverController {
	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;

	private ModelAndView mav;

	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	// �α��� ù ȭ�� ��û �޼ҵ�
	@RequestMapping(value = "/Join_Naverlogin")
	public String login(Model model, HttpSession session) {
		/* ���̹����̵�� ���� URL�� �����ϱ� ���Ͽ� naverLoginBOŬ������ getAuthorizationUrl�޼ҵ� ȣ�� */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		// https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		// redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		System.out.println("���̹�:" + naverAuthUrl);
		// ���̹�
		model.addAttribute("url", naverAuthUrl);
		return "login";
	}

	// ���̹� �α��� ������ callbackȣ�� �޼ҵ�
	@RequestMapping(value = "/callback")
	public ModelAndView callback(Model model, @RequestParam String code, @RequestParam String state,
			HttpSession session) throws IOException, ParseException {
		
		System.out.println("����� callback");
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		// 1. �α��� ����� ������ �о�´�.
		apiResult = naverLoginBO.getUserProfile(oauthToken); // String������ json������
		/**
		 * apiResult json ���� {"resultcode":"00", "message":"success",
		 * "response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"shinn0608@naver.com","name":"\uc2e0\ubc94\ud638"}}
		 **/
		// 2. String������ apiResult�� json���·� �ٲ�
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		// 3. ������ �Ľ�
		// Top���� �ܰ� _response �Ľ�
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		// response�� nickname�� �Ľ�
		String nickname = (String) response_obj.get("nickname");
		String email = (String) response_obj.get("email");
		String birthday = (String) response_obj.get("birthday");
		String name = (String) response_obj.get("name");
		System.out.println(nickname);
		System.out.println(email);
		System.out.println(birthday);
		System.out.println(name);
		// 4.�Ľ� �г��� �������� ����
		/*
		 * session.setAttribute("sessionNickname", nickname);
		 * session.setAttribute("sessionName", name);// ���� ����
		 * session.setAttribute("sessionEmail", email);// ���� ����
		 * session.setAttribute("sessionBirthday", birthday);// ���� ����
		 */
		mav = new ModelAndView();
		mav.addObject("naverNickname", nickname);
		mav.addObject("naverEmail", email);
		mav.addObject("naverBirthday", birthday);
		mav.addObject("naverName", name);
		mav.setViewName("navermemberjoin");

		model.addAttribute("result", apiResult);
		return mav;
	}

	// �α׾ƿ�
	@RequestMapping(value = "/Login_LoginOut")
	public String logout(HttpSession session) throws IOException {
		System.out.println("����� logout");
		session.invalidate();
		return "home";
	}
}
