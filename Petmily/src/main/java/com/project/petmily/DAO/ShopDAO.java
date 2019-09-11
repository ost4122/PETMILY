package com.project.petmily.DAO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.petmily.DTO.ShopDTO;

@Repository
public class ShopDAO {

	@Autowired
	private SqlSessionTemplate sql;

	public int Shop_Input(ShopDTO sdto) {
		// TODO Auto-generated method stub
		return sql.insert("Shop.shop_input",sdto);
	}
}
