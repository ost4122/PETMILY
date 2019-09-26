package com.project.petmily.DTO;

import lombok.Data;

@Data
public class Page_DTO {

	private int page;             // 현재 페이지
	private int maxPage;          // 최대 페이지
	private int startPage;        // 시작페이지
	private int endPage;          // 마지막페이지
	private int listCount;        // 
	private int limit;            // 최대 몇페이지까지 보여줄건지
	private int startRow;         // 
	private int endRow;           //
	
	private int boardCount;
	
}
