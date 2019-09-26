package com.project.petmily.DAO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.project.petmily.DTO.Adopt_DTO;
import com.project.petmily.DTO.Page_DTO;

@Repository
public class Adopt_DAO {

	@Autowired
	private SqlSessionTemplate sql;
	
	/* 분양 신청 하기 */
	public int a_write(Adopt_DTO aDTO) {
		return sql.insert("Adopt.a_write", aDTO);
	}

	/* 분양 완료 후 분양성공 유무 */
	public int a_adoptConfirm(int a_number) {
		return sql.update("Adopt.a_adoptConfirm", a_number);
	}

	/* 분양신청 목록(페이징처리) 
	public List<Adopt_DTO> a_list(Page_DTO pageDTO) {
		return sql.selectList("Adopt.a_list", pageDTO);
	}
	
	 리스트 카운트(페이징처리) 
	public int listCount() {
		return sql.selectOne("Adopt.listCount");
	}*/


}
