package com.project.petmily.DAO;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.petmily.DTO.ImagesDTO;
import com.project.petmily.DTO.PageDTO;
import com.project.petmily.DTO.ShopDTO;

@Repository
public class ShopDAO {

	@Autowired
	private SqlSessionTemplate sql;

	/* 글 작성 */
	public int Shop_Input(ShopDTO sdto) {
		// TODO Auto-generated method stub
		return sql.insert("Shop.shop_input",sdto);
	}
	/* 다중파일 DB 저장 */ 
	public int shop_imges(ImagesDTO idto) {
		return sql.insert("Shop.shop_images",idto);
	}
	/* 용품 목록 */
	public List<ShopDTO> Shop_List() {
		return sql.selectList("Shop.shop_List");
	}
	/* 용품 목록 갯수 */
	public int listCount() {
		return sql.selectOne("Shop.listCount");
	}
	/* 용품 상세 보기 */
	public ShopDTO Shop_View(int item_number) {
		return sql.selectOne("Shop.shop_View", item_number);
	}
	/* 다중파일 */
	public List<String> MultyImg(int item_number) {
		return sql.selectList("Shop.multyImg",item_number);
	}
	/* 강아지 용품 목록 */
	public List<ShopDTO> puffyItem(String item_kind) {
		return sql.selectList("Shop.puffyItem",item_kind);
	}
	/* 강아지 카테고리 검색 */
	public List<ShopDTO> puffyItems(ShopDTO sdto) {
		return sql.selectList("Shop.puffyItems",sdto);
	}

	
}
