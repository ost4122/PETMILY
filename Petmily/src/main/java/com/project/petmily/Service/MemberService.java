package com.project.petmily.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.project.petmily.DAO.MemberDAO;
import com.project.petmily.DTO.MemberDTO;
@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private HttpSession session;
	
	private ModelAndView mav;
	
	
   //  private BCryptPasswordEncoder passEncoder;
	 
	
	public ModelAndView memberJoin(MemberDTO memberDTO) {
		mav=new ModelAndView();
		int JoinResult=memberDAO.memberJoin(memberDTO);
		if(JoinResult>0) {
			mav.setViewName("home");
		}else {
			mav.setViewName("JoinForm");
		}
		return mav;
	}
	
	//회원가입시 이메일 인증 
	public ModelAndView send_mail(MemberDTO memberDTO) throws Exception{
			//	int mailz = memberDAO.approval_member(memberDTO);
				// Mail Server 설정
				String charSet = "UTF-8";
				String hostSMTP = "smtp.naver.com";		
				String hostSMTPid = "suhun1008@naver.com"; // 본인의 아이디 입력		
				String hostSMTPpwd = "s10131013"; //비밀번호 입력
				
				// 보내는 사람 EMail, 제목, 내용 
				String fromEmail = "suhun1008@naver.com"; // 보내는 사람 eamil
				String fromName = "프로젝트테스트";  // 보내는 사람 이름
				String subject = "이메일 발송 테스트"; // 제목
				String msg = "";
				
				// 회원가입 메일 내용
					subject = "Petmily 회원가입 인증 메일입니다.";
					msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
					msg += "<h3 style='color: blue;'>";
					msg += memberDTO.getId() + "님 회원가입을 환영합니다.</h3>";
					msg += "<div style='font-size: 130%'>";
					msg += "하단의 인증 버튼 클릭 시 정상적으로 회원가입이 완료됩니다.</div><br/>";
					msg += "<form method='post' action='http://localhost:8090/petmily/approval_member.do'>";
					msg += "<input type='hidden' name='email' value='" + memberDTO.getEmail() + "'>";
					msg += "<input type='hidden' name='appoval_status' value='" + memberDTO.getAppoval_status() +"'>";
					msg += "<input type='submit' value='인증'></form><br/></div>";
				
				// 받는 사람 E-Mail 주소
				String mail = memberDTO.getEmail();  // 받는 사람 email		
				
				try {
					HtmlEmail email = new HtmlEmail();
					email.setDebug(true);
					email.setCharset(charSet);
					email.setSSL(true);
					email.setHostName(hostSMTP);
					email.setSmtpPort(465);	// SMTP 포트 번호 입력

					email.setAuthentication(hostSMTPid, hostSMTPpwd);
					email.setTLS(true);
					email.addTo(mail, charSet);
					email.setFrom(fromEmail, fromName, charSet);
					email.setSubject(subject);
					email.setHtmlMsg(msg); 
					email.send();			
				
				} catch (Exception e) {
					System.out.println("메일발송 실패 :"+e);
				}
				return mav;
			}
			// 회원 인증
				public void approval_member(MemberDTO memberDTO, HttpServletResponse response) throws Exception {
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					if (memberDAO.approval_member(memberDTO) == 0) { // 이메일 인증에 실패하였을 경우
						out.println("<script>");
						out.println("alert('잘못된 접근입니다.');");
						out.println("history.go(-1);");
						out.println("</script>");
						out.close();
					} else { // 이메일 인증을 성공하였을 경우
						out.println("<script>");
						out.println("alert('인증이 완료되었습니다. 로그인 후 이용하세요.');");
						out.println("location.href='go_home';");
						out.println("</script>");
						out.close();
					}
				}

	public ModelAndView memberLogin(MemberDTO memberDTO,HttpServletResponse response) throws IOException {
		response.setContentType("test/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		mav=new ModelAndView();
		MemberDTO LoginResult=memberDAO.memberLogin(memberDTO);
		if(LoginResult!=null) {
			session.setAttribute("sessionId", LoginResult.getId());
			mav.addObject("loginMember",LoginResult);
			mav.setViewName("home");
		}else {
			out.println("<script>");
			out.println("alert('아이디 혹은 비밀번호가 틀립니다.');");
			out.println("</script>");
			out.close();
		}
		return mav;
	}

	public String idOverlap(String id) {
		String overlapId=memberDAO.idOverlap(id);
		String resultMsg;
		if(overlapId==null) {
			resultMsg="OK";
		}else {
			resultMsg="NO";
		}
		return resultMsg;
	}
	public String nameOverlap(String name) {
		String overlapName=memberDAO.nameOverlap(name);
		String resultMsg;
		if(overlapName==null) {
			resultMsg="OK";
		}else {
			resultMsg="NO";
		}
		return resultMsg;
	}

	public ModelAndView joinFile(MemberDTO memberDTO) {
		mav = new ModelAndView();
		int joinResult=memberDAO.joinFile(memberDTO);
		if(joinResult>0) {
			mav.setViewName("redirect:/memberList");
		}
		return mav;
	}
	public ModelAndView memberList()  {
		mav=new ModelAndView();
		List<MemberDTO> listResult=memberDAO.memberList();
			mav.addObject("memberList",listResult);
			mav.setViewName("MemberList");
			return mav;
	}
	public List<MemberDTO> memberListAjax() {
		List<MemberDTO> memberList = memberDAO.memberList();
		return memberList;
	}
	public ModelAndView memberView(String id)  {
		mav=new ModelAndView();
		MemberDTO memberView= memberDAO.memberView(id);
			mav.addObject("memberView",memberView);
			mav.setViewName("MemberView");
			return mav;
	}

	public ModelAndView memberModify(String id) {
		mav=new ModelAndView();
		MemberDTO Result=memberDAO.memberView(id);
		if(Result!=null) {
			mav.addObject("memberModify",Result);
			mav.setViewName("MemberModify");
		}else {
			mav.setViewName("MemberList");
		}
		return mav;
	}

	public ModelAndView memberModify2(MemberDTO memberDTO) {
		mav=new ModelAndView();
		memberDAO.MemberModify(memberDTO);
		
		return mav;
	}

	public ModelAndView memberDelete(String id) {
		mav=new ModelAndView();
		int result=memberDAO.memberDelete(id);
		if(result>0) {
			mav.setViewName("redirect:/memberlogin");
		}
			return mav;
		
	}
	public void logout(HttpSession session) {
		session.invalidate();
	}

	//비밀번호찾기인증메일 입력하기 
				
				public void pwFind(MemberDTO memberDTO) throws Exception {
					
					
					String charSet = "UTF-8";
					String hostSMTP = "smtp.naver.com";		
					String hostSMTPid = "suhun1008@naver.com"; // 본인의 아이디 입력		
					String hostSMTPpwd = "s10131013"; //비밀번호 입력
					
					// 보내는 사람 EMail, 제목, 내용 
					String fromEmail = "suhun1008@naver.com"; // 보내는 사람 eamil
					String fromName = "Petmily 관리자";  // 보내는 사람 이름
					String subject = "비번찾기이메일 발송"; // 제목
					String msg = "";
					String name = memberDTO.getId();
					String sub = "님";
					String n = name+=sub;
					
					
					//비밀번호 발송
					subject = "Petmily 비밀번호 임시 메일입니다.";
					msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
					msg += "<h3 style='color: blue;'>";
					msg += memberDTO.getId() + "님의 임시 비밀번호입니다. 비밀번호를 변경하여 사용하세요!</h3>";
					msg += "<p>임시 비밀번호 : <strong>";
					msg += memberDTO.getPassword()+"</strong></p></div>";
					
					// 받는 사람 E-Mail 주소
					 String mail = memberDTO.getEmail();  // 받는 사람 email
					try {
						HtmlEmail email = new HtmlEmail();
						email.setDebug(true);
						email.setCharset(charSet);
						email.setSSL(true);
						email.setHostName(hostSMTP);
						email.setSmtpPort(465);	// SMTP 포트 번호 입력

						email.setAuthentication(hostSMTPid, hostSMTPpwd);
						email.setTLS(true);
						email.addTo(mail, n);
						email.setFrom(fromEmail, fromName, charSet);
						email.setSubject(subject);
						email.setHtmlMsg(msg); 
						email.send();			
						}catch (Exception e) {
							System.out.println("비밀번호이메일발송 실패 :"+e);
						}
	              // String encPassword = passEncoder.encode(memberDTO.getPassword());
					
					memberDTO.setPassword(memberDTO.getPassword());
					// 비밀번호 변경
					memberDAO.update_pw(memberDTO);
				}
				
				// 비밀번호 찾기
				
				public void find_pw(MemberDTO memberDTO,HttpServletResponse response) throws Exception {
					mav = new ModelAndView();
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					// 아이디가 없으면
					if(memberDAO.idOverlap(memberDTO.getId()) == null) {
						out.print("존재하지않는 아이디입니다.");
						out.close();
					}
		
					else {
						// 임시 비밀번호 생성
						String pw = "";
						for (int i = 0; i < 12; i++) {
							pw += (char) ((Math.random() * 26) + 97);
						}
						memberDTO.setPassword(pw);
			          	// 비밀번호 변경 메일 발송
						pwFind(memberDTO);
						out.print("이메일로 임시 비밀번호를 발송하였습니다.");
						mav.setViewName("go_home");
						out.close();
					}
				}
				public void idFind(MemberDTO memberDTO ,HttpServletResponse response) throws Exception {
					String id = memberDAO.select_id(memberDTO);
					String charSet = "UTF-8";
					String hostSMTP = "smtp.naver.com";		
					String hostSMTPid = "suhun1008@naver.com"; // 본인의 아이디 입력		
					String hostSMTPpwd = "s10131013"; //비밀번호 입력
					// 보내는 사람 EMail, 제목, 내용 
					String fromEmail = "suhun1008@naver.com"; // 보내는 사람 eamil
					String fromName = "Petmily 관리자";  // 보내는 사람 이름
					String subject = "아이디 찾기 이메일 발송"; // 제목
					String msg = "";
					String name = memberDTO.getName();
					String sub = "님";
					String n = name+=sub;
					//비밀번호 발송
					subject = "Petmily 회원님의 아이디 입니다.";
					msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
					msg += "<h3 style='color: blue;'>";
					msg += memberDTO.getName() + "님의 아이디 입니다.</h3>";
					msg += "<p>아이디 : <strong>";
					msg += id +"</strong></p></div>";
					// 받는 사람 E-Mail 주소
					 String mail = memberDTO.getEmail();  // 받는 사람 email
					try {
						HtmlEmail email = new HtmlEmail();
						email.setDebug(true);
						email.setCharset(charSet);
						email.setSSL(true);
						email.setHostName(hostSMTP);
						email.setSmtpPort(465);	// SMTP 포트 번호 입력

						email.setAuthentication(hostSMTPid, hostSMTPpwd);
						email.setTLS(true);
						email.addTo(mail, n);
						email.setFrom(fromEmail, fromName, charSet);
						email.setSubject(subject);
						email.setHtmlMsg(msg); 
						email.send();			
						}catch (Exception e) {
							System.out.println("아이디 이메일발송 실패 :"+e);
						}
				}
					//아이디 찾기
					public void find_id(MemberDTO memberDTO,HttpServletResponse response) throws Exception {
						mav = new ModelAndView();
						response.setContentType("text/html;charset=utf-8");
						PrintWriter out = response.getWriter();
							idFind(memberDTO, response);
							out.print("이메일로 회원님의 아이디를 발송하였습니다.");
							mav.setViewName("go_home");
							out.close();
						
				}
					


}


