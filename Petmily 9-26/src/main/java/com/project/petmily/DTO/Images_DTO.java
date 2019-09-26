package com.project.petmily.DTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Images_DTO {

	private List<MultipartFile> imges;			/* 첨부이미지  */
	private List<String> img;			/* 첨부이미지  */
	private int item_Number;
	private String multiImg;
}
