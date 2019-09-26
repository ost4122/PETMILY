package com.project.petmily.DAO;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.project.petmily.DTO.Member_DTO;

@Repository
public class Member_DAO {
	
	@Autowired
	private SqlSessionTemplate sql;
	
	public int memberJoin(Member_DTO memberDTO) {
		return sql.insert("Member.memberJoin",memberDTO);
	}
	public int navermemberjoin(Member_DTO memberDTO) {
		return sql.insert("Member.navermemberjoin",memberDTO);
	}
	public Member_DTO Login_Login(Member_DTO memberDTO) {
		return sql.selectOne("Member.Login_Login",memberDTO);
	}
	public String Join_IdCheck(String id) {
		return sql.selectOne("Member.Join_IdCheck",id);
	}
	public String nameOverlap(String name) {
		return sql.selectOne("Member.nameOverlap",name);
	}
	public int joinFile(Member_DTO memberDTO) {
		return sql.insert("Member.joinFile",memberDTO);
	}
	public List<Member_DTO> memberList() {
		return sql.selectList("Member.memberList");
	}
	public  Member_DTO memberView(String sessionId) {
		return sql.selectOne("Member.memberView",sessionId);
	}
	public int MemberModify(Member_DTO id) {
		return sql.update("Member.memberModify",id);		
	}
	public int memberDelete(String id) {
		return sql.delete("Member.memberDelete",id);
	}
	public void logout(HttpSession session) {
	}
	public String appoval(Member_DTO id) {
		return sql.selectOne("Member.appoval",id);
	}
	// �̸��� ����
		public int approval_member(Member_DTO memberDTO) throws Exception{
		return sql.update("Member.approval_member", memberDTO);
		}	
		// ��й�ȣ ����
		
	public int update_pw(Member_DTO memberDTO) throws Exception{
		return sql.update("Member.update_pw", memberDTO);
				}
	//���̵� ã��
	public String select_id(Member_DTO memberDTO) {
		return sql.selectOne("Member.select_id", memberDTO);
	}
	
	

}
