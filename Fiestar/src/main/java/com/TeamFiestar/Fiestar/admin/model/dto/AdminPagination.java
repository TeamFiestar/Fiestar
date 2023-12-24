package com.TeamFiestar.Fiestar.admin.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@ToString
public class AdminPagination {

	private int currentPage;
	private int countList;
	private int limit = 10;
	private int pageSize = 10;
	private int maxPage;
	private int startPage;
	private int nextPage;
	private int endPage;
	private int prevPage;
	
//	public AdminPagination(int countList2, int cp) {
//		// TODO Auto-generated constructor stub
//	}

	public AdminPagination(int countList, int currentPage) {
		this.countList = countList;
		this.currentPage = currentPage;
		
		calcPagination();
	}
	
	public AdminPagination(int currentPage, int countList, int limit, int pageSize) {
		   this.currentPage = currentPage;
		   this.countList = countList;
		   this.limit = limit;
		   this.pageSize = pageSize;
		   
		   calcPagination();
	   }
	   
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		calcPagination();
	}

	public void setListCount(int listCount) {
		this.countList = listCount;
		calcPagination();
	}

	public void setLimit(int limit) {
		this.limit = limit;
		calcPagination();
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		calcPagination();
	}
	
	@Override
	public String toString() {
		return "Pagination [currentPage=" + currentPage + ", countList=" + countList + ", limit=" + limit
				+ ", pageSize=" + pageSize + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage + "]";
	}
	
	
	private void calcPagination() {
		maxPage = (int) Math.ceil((double)countList/limit);
		startPage = (currentPage - 1)/ pageSize * pageSize +1;
		endPage = startPage + pageSize -1;
		if(endPage > maxPage) endPage = maxPage;
		
		if(currentPage <= pageSize) prevPage = 1;
		else prevPage = startPage-1;
		
		if(endPage == maxPage) nextPage = maxPage;
		else nextPage = endPage+1;
	}
}
