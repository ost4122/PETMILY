package com.project.petmily.DTO;

import lombok.Data;

@Data
public class Paging_DTO {
	
	private int page;
	private int maxPage;
	private int startPage;
	private int endPage;
	
	private int boardCount;
	private int startRow;
	private int endRow;
	

}
