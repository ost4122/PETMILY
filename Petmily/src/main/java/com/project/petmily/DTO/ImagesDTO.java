package com.project.petmily.DTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ImagesDTO {

	private List<MultipartFile> imges;			/* 첨부이미지  */
	private List<String> item_img;			/* 첨부이미지  */
	private int item_Number;
	private String multiImg;
}
