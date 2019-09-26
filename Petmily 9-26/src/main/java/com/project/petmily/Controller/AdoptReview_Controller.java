package com.project.petmily.Controller;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.project.petmily.DAO.AdoptReview_DAO;
import com.project.petmily.DTO.AdoptReview_DTO;
import com.project.petmily.Service.AdoptReview_Service;

@Controller
public class AdoptReview_Controller {

	@Autowired
	private AdoptReview_Service arService;
	private ModelAndView mav;

	@Autowired
	private AdoptReview_DAO arDAO;

	/* 후기글 쓰기 */
	@RequestMapping(value = "/ar_write", method = RequestMethod.POST)
	public ModelAndView ar_write(@ModelAttribute AdoptReview_DTO arDTO) throws IllegalStateException, IOException {

		mav = new ModelAndView();

		mav = arService.ar_write(arDTO);
		
		return mav;
	}

	/* 후기글 목록 */
	@RequestMapping(value = "/ar_list", method = RequestMethod.GET)
	public ModelAndView ar_list(@RequestParam("page") int page) {

		if (page == 0)
			page = 1;

		mav = new ModelAndView();

		mav = arService.ar_list(page);

		return mav;
	}

	/* 후기글 상세보기 */
	@RequestMapping(value = "ar_view", method = RequestMethod.GET)
	public ModelAndView ar_view(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("ar_number") int ar_number, @RequestParam("page") int page) {

		mav = new ModelAndView();

		AdoptReview_DTO arView = arService.ar_view(ar_number);

		Cookie[] cookies = request.getCookies();

		// 비교하기 위해 새로운 쿠키
		Cookie viewCookie = null;

		// 쿠키가 있을 경우
		if (cookies != null && cookies.length > 0) {

			for (int i = 0; i < cookies.length; i++) {

				// Cookie의 name이 cookie + reviewNo와 일치하는 쿠키를 viewCookie에 넣어줌
				if (cookies[i].getName().equals("cookie" + ar_number)) {
					System.out.println("처음 쿠키가 생성한 뒤 들어옴.");
					viewCookie = cookies[i];
				}
			}
		}
		if (arView != null) {
			System.out.println("System - 해당 상세 리뷰페이지로 넘어감");
			mav.addObject("arView", arView);
			mav.addObject("page", page);

			// 만일 viewCookie가 null일 경우 쿠키를 생성해서 조회수 증가 로직을 처리함.
			if (viewCookie == null) {
				System.out.println("cookie 없음");

				// 쿠키 생성(이름, 값)
				Cookie newCookie = new Cookie("cookie" + ar_number, "|" + ar_number + "|");

				// 쿠키 추가
				response.addCookie(newCookie);

				arDAO.ar_hitCount(ar_number);
			}
			// viewCookie가 null이 아닐경우 쿠키가 있으므로 조회수 증가 로직을 처리하지 않음.
			else {
				System.out.println("cookie 있음");
				// 쿠키 값 받아옴.
				String value = viewCookie.getValue();

				System.out.println("cookie 값 : " + value);
			}

			mav.setViewName("AdoptReview_View");
			return mav;
		} else {
			// 에러 페이지 설정
			mav.setViewName("error/reviewError");
			return mav;
		}

	}

}
