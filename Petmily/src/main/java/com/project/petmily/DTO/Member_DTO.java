package com.project.petmily.DTO;


import lombok.Data;

@Data
public class Member_DTO {
	
	private String id;
	private String password;
	private String name;
	private String phone;
	private String birth;
	private String address_number; //�����ȣ
	private String address_a; //���θ� �ּ�
	private String address_b; //�����ּ�
	private String address_c; //���ּ�
	private String address_d; //�����׸�
	private String email;
	private String email_1;
	private String email_2;
	private String email_3;
	private String selectemail;
	private String appoval_status; //���̵� ���� ���� 
	private String address;
	
}