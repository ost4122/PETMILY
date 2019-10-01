package com.project.petmily.DAO;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.petmily.DTO.Delivery_DTO;
import com.project.petmily.DTO.Images_DTO;
import com.project.petmily.DTO.Purchase_DTO;
import com.project.petmily.DTO.Shop_DTO;

@Repository
public class ShopDAO {

	@Autowired
	private SqlSessionTemplate sql;

	/* 글 작성 */
	public int Shop_Input(Shop_DTO sdto) {
		// TODO Auto-generated method stub
		return sql.insert("Shop.shop_input",sdto);
	}
	/* 다중파일 DB 저장 */ 
	public int shop_imges(Images_DTO idto) {
		return sql.insert("Shop.shop_images",idto);
	}
	/* 용품 목록 */
	public List<Shop_DTO> Shop_List() {
		return sql.selectList("Shop.shop_List");
	}
	/* 용품 목록 갯수 */
	public int listCount() {
		return sql.selectOne("Shop.listCount");
	}
	/* 용품 상세 보기 */
	public Shop_DTO Shop_View(int item_number) {
		return sql.selectOne("Shop.shop_View", item_number);
	}
	/* 다중파일 */
	public List<String> MultyImg(int item_number) {
		return sql.selectList("Shop.multyImg",item_number);
	}
	/* 강아지 용품 목록 */
	public List<Shop_DTO> puffyItem(String item_kind) {
		return sql.selectList("Shop.puffyItem",item_kind);
	}
	/* 강아지 카테고리 검색 */
	public List<Shop_DTO> puffyItems(Shop_DTO sdto) {
		return sql.selectList("Shop.puffyItems",sdto);
	}
	
	/* 배송 신청 */
	public int Item_Delivery(Delivery_DTO ddto) {
		return sql.insert("Shop.delivery",ddto);
	}
	/* 판매순 +1 */
	public void soldCount(int item_number) {
		 sql.update("soldCount", item_number);
	}
	/*구매 내역*/
	public List<Delivery_DTO> purchase(String id) {
		return sql.selectList("Shop.purchase",id);
	}
	/* 리뷰 작성(전) 구매 정보 */
	public Delivery_DTO purchase_select(int delivery_number) {
		return sql.selectOne("Shop.purchase_select", delivery_number);
	}
	/* 구매후기 작성 */
	public int write_review(Purchase_DTO pdto) {
		return sql.insert("Shop.writer_review", pdto);
	}
	/* 구매후기 다중파일 저장 */
	public void review_file(Purchase_DTO pdto) {
		sql.insert("Shop.review_file", pdto);
	}
	/* 상품리뷰 갯수 증가 */
	public void review_count(int item_number) {
		sql.update("Shop.review_count", item_number);
	}
	/* 리뷰 가져오기 */
	public List<Purchase_DTO> getReview(int item_number) {
		return sql.selectList("Shop.getReview", item_number);
	}
	/* 리뷰 첨부파일 가져오기*/
	public List<String> getReviewImg(int review_number) {
		return sql.selectList("Shop.getReviewImg", review_number);
	}

	
}
