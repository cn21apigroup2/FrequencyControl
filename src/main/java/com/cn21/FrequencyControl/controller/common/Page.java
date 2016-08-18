/**
 *  @Title: Page.java 
 *  @Package com.cn21.FrequencyControl.controller.common 
 *  @Description: TODO(用一句话描述该文件做什么) 
 *  @author chenxiaofeng
 *  @date 2016年8月17日 下午4:41:06 
 *  @version V1.0 
 */
package com.cn21.FrequencyControl.controller.common;

import java.util.List;

/**
 * @author chenxiaofeng
 * @date 2016年8月17日
 */
public class Page<T> {
	public static final int DEFAULT_PAGE_SIZE=10;
	public static final int DEFAULT_PAGE_NO=1;
	private int pageNo;
	private int totalSize;
	private int pageSize;
	private int maxPage;
	List<T> records;
	/**
	 * @return the maxPage
	 */
	public int getMaxPage() {
		return maxPage;
	}
	/**
	 * @param maxPage the maxPage to set
	 */
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	/**
	 * 获取首页
	 * @return
	 */
	public int getTopPageNo(){
		return 1;
	}
	/**
	 * 获取总页数
	 * @return
	 */
	public int getTotalPages(){
		return totalSize/pageSize+1;
	}
	/**
	 * 获取最后一页
	 * @return
	 */
	public int getBottomPageNo(){
		return totalSize/pageSize+1;
	}
	/**
	 * 获取下一页
	 * @return
	 */
	public int getNextPage(){
		if(pageNo>=getTotalPages())
			return getTotalPages();
		return pageNo+1;
	}
	/**
	 * 获取前一页
	 * @return
	 */
	public int getPreviousPage(){
		if(pageNo<=1)
			return 1;
		return pageNo-1;
	}
	/**
	 * 获取记录
	 * @return
	 */
	public List<T> getRecords(){
		return records;
	}
	public void setRecords(List<T> records){
		 this.records=records;
	}
	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}
	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		if(pageNo<=0  || pageNo >getTotalPages())
			pageNo=getTotalPages();
		this.pageNo = pageNo;
	}
	/**
	 * @return the totalSize
	 */
	public int getTotalSize() {
		return totalSize;
	}
	/**
	 * @param totalSize the totalSize to set
	 */
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		if(pageSize<=0) this.pageSize = DEFAULT_PAGE_SIZE;
		else this.pageSize = pageSize;
	}
	

}
