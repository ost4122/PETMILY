package com.project.petmily.DTO;

import lombok.Data;

@Data
public class PageDTO {

	private int page;
	private int maxPage;
	private int startPage;
	private int endPage;
	private int listCount;
	private int boardCount;
	private int limit;
	private int startRow;
	private int endRow;
}
