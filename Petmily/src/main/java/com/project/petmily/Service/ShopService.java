package com.project.petmily.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.project.petmily.DAO.ShopDAO;

@Service
public class ShopService {
	@Autowired
	private ShopDAO sdao;
	private ModelAndView mav;
	
}
