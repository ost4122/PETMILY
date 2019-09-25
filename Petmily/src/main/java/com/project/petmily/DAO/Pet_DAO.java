package com.project.petmily.DAO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.petmily.DTO.Paging_DTO;
import com.project.petmily.DTO.Pet_DTO;

@Repository
public class Pet_DAO {

	@Autowired
	private SqlSessionTemplate sql;

	// 관리자만 쓸수 있는 미분양 애완동물 작성
	public int admin_pet_writ(Pet_DTO pet_DTO) {

		return sql.insert("Pet.admin_pet_writ", pet_DTO);
	}

	// 강아지 다 페이징 카운트
	public int Pet_Dog_allList_Count(String pet_kategorie) {

		return sql.selectOne("Pet.Pet_Dog_allList_Count", pet_kategorie);
	}

	// 강아지리스트 페이징 처리
	public List<Pet_DTO> Pet_Dog_allList_Paging(Pet_DTO paging) {

		return sql.selectList("Pet.Pet_Dog_allList_Paging", paging);
	}

	// 상세보기
	public Pet_DTO Pet_Dog_View(int pet_number) {
		return sql.selectOne("Pet.Pet_Dog_View", pet_number);
	}

	public int Hitto(int pet_number) {
		return sql.update("Pet.like_check", pet_number);
	}

	// 분양글 취소
	public int Hitcancle(int pet_number) {
		return sql.update("Pet.like_check_cancel", pet_number);
	}

	// 대형견 페이징 카운트
	public int Pet_Dog_Big_Count(String pet_size) {

		return sql.selectOne("Pet.Pet_Dog_Big_Count", pet_size);
	}

	// 대형견 리스트 페이징 ㅊ ㅓ리
	public List<Pet_DTO> Pet_Dog_Big(Pet_DTO pet_DTO) {

		return sql.selectList("Pet.Pet_Dog_Big", pet_DTO);
	}

	// 중형견 페이징 카운트
	public int Pet_Dog_Medium_Count(String pet_size) {
		return sql.selectOne("Pet.Pet_Dog_Medium_Count", pet_size);
	}

	// 중형견 리스트
	public List<Pet_DTO> Pet_Dog_Medium(Pet_DTO pet_DTO) {
		return sql.selectList("Pet.Pet_Dog_Medium", pet_DTO);
	}

	// 소형견 페이징 카운트
	public int Pet_Dog_Small_Count(String pet_size) {
		return sql.selectOne("Pet.Pet_Dog_Small_Count", pet_size);
	}

	// 소형견 리스트
	public List<Pet_DTO> Pet_Dog_Small(Pet_DTO pet_DTO) {
		return sql.selectList("Pet.Pet_Dog_Small", pet_DTO);
	}

	// 고양이 페이징 카운트
	public int Pet_Cat_List_Count(String pet_kategorie) {
		return sql.selectOne("Pet.Pet_Cat_List_Count", pet_kategorie);
	}

	// 고양이 리스트
	public List<Pet_DTO> Pet_Cat_List(Pet_DTO pet_DTO) {
		return sql.selectList("Pet.Pet_Cat_List", pet_DTO);
	}

	// 파충류 페이징 카운트
	public int Pet_Reptile_List_Count(String pet_kategorie) {
		return sql.selectOne("Pet.Pet_Reptile_List_Count", pet_kategorie);
	}

	// 파충류 리스트
	public List<Pet_DTO> Pet_Reptile_List(Pet_DTO pet_DTO) {
		return sql.selectList("Pet.Pet_Reptile_List", pet_DTO);
	}

	// 댓글쓰는 메소드
	public int commentWrite(Pet_DTO commentDTO) {

		return sql.insert("Pet.commentWrite", commentDTO);
	}

	// DB에서 댓글 가져오는 메소드
	public List<Pet_DTO> commentList(Pet_DTO paging) {

		return sql.selectList("Pet.commentList", paging);
	}

	// 댓글 수정하는 메소드
	public int commentModifyUpdate(Pet_DTO commentDTO) {
		return sql.update("Pet.commentModifyUpdate", commentDTO);
	}

	// 댓글 삭제
	public int commentDelete(int commentNumber) {
		return sql.delete("Pet.commentDelete", commentNumber);
	}

	// 댓글 페이징 숫자세기
	public int commentCount() {

		return sql.selectOne("Pet.commentListCount");
	}

	// 분양글 삭제하기
	public int pet_Delete(int pet_number) {

		return sql.delete("Pet.pet_Delete", pet_number);
	}

	// 분양수정전 게시글 불러오기
	public Pet_DTO modifyView(int pet_number) {

		return sql.selectOne("Pet.petmodifyView", pet_number);
	}

	// 분양수정
	public int pet_Modify(Pet_DTO pet_DTO) {
		return sql.update("Pet.pet_Modify", pet_DTO);
	}

	// 관리자만 쓸수 있는 대댓글
	public int Smail_commentWrite(Pet_DTO commentDTO) {
		return sql.insert("Pet.Smail_commentWrite", commentDTO);
	}

	// DB에서 대 댓글 가져오는 메소드
	public List<Pet_DTO> Smail_commentList(Pet_DTO pet_DTO) {

		return sql.selectList("Pet.Smail_commentList", pet_DTO);
	}

	// 펫 종별로 검색
	public List<Pet_DTO> Pet_NameList(String pet_name) {
		return sql.selectList("Pet.Pet_NameList", pet_name);
	}

}
