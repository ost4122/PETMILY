package com.project.petmily.DAO;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.petmily.DTO.MemberDTO;
@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sql;
	
	public int memberJoin(MemberDTO memberDTO) {
		return sql.insert("Member.memberJoin",memberDTO);
	}
	public MemberDTO memberLogin(MemberDTO memberDTO) {
		return sql.selectOne("Member.memberlogin",memberDTO);
	}
	public String idOverlap(String id) {
		return sql.selectOne("Member.idOverlap",id);
	}
	public String nameOverlap(String name) {
		return sql.selectOne("Member.nameOverlap",name);
	}
	public int joinFile(MemberDTO memberDTO) {
		return sql.insert("Member.joinFile",memberDTO);
	}
	public List<MemberDTO> memberList() {
		return sql.selectList("Member.memberList");
	}
	public  MemberDTO memberView(String id) {
		return sql.selectOne("Member.memberView",id);
	}
	public int MemberModify(MemberDTO memberDTO) {
		return sql.update("Member.memberModify",memberDTO);		
	}
	public int memberDelete(String id) {
		return sql.delete("Member.memberDelete",id);
	}
	public void logout(HttpSession session) {
		
	}
	// 이메일 인증
		public int approval_member(MemberDTO memberDTO) throws Exception{
		return sql.update("Member.approval_member", memberDTO);
		}	
		// 비밀번호 변경
		
	public int update_pw(MemberDTO memberDTO) throws Exception{
		return sql.update("Member.update_pw", memberDTO);
				}
	//아이디 찾기
	public String select_id(MemberDTO memberDTO) {
		return sql.selectOne("Member.select_id", memberDTO);
	}	

}
