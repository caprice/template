package com.gm.infobus.repository.base;

import java.io.Serializable;
import java.util.List;

/**
 * 封装分页信息
 * 
 */
public class Pagination<T> implements Serializable {
	private static final long serialVersionUID = -8244216270218350446L;
	private static final int DEFAULT_PAGE_SIZE = 10;
	private int total; // 总记录数
	private int pageIndex; // 当前页, 从1开始计数
	private int pageSize; // 页大小
	private List<T> items; // 页数据
	private int startRow; // 起始行, 从1开始计数
	private int endRow; // 结束行, 从1开始计数
	private int pageCount;// 总页数

	public Pagination() {
	}

	public Pagination(Pagination<T> page) {
		this.pageIndex = page.getPageIndex();
		this.pageSize = page.pageSize;
		this.startRow = pageSize * (pageIndex - 1) + 1;
		this.endRow = this.startRow + pageSize - 1;
	}

	public Pagination(int pageIndex, int total) {
		this(pageIndex, DEFAULT_PAGE_SIZE, total);
	}

	public Pagination(int pageIndex, int pageSize, int total) {
		this.pageIndex = pageIndex <= 0 ? 1 : pageIndex;
		this.pageSize = pageSize;
		this.startRow = pageSize * (this.pageIndex - 1) + 1;
		this.endRow = this.startRow + this.pageSize - 1;
		setTotal(total);
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		if (total < pageSize) {
			this.pageCount = 1;
		} else {
			if (total % pageSize == 0) {
				this.pageCount = total / pageSize;
			} else {
				this.pageCount = total / pageSize + 1;
			}
		}
		if (pageIndex > pageCount) {
			this.pageIndex = this.pageCount;
			startRow = pageSize * (pageIndex - 1) + 1;
			endRow = startRow + pageSize - 1;
		}
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * 起始行位置, 从1开始计数
	 * 
	 * @return
	 */
	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	/**
	 * 结束行位置, 从1开始计数
	 * 
	 * @return
	 */
	public int getEndRow() {
		if (endRow > getTotal()) {
			return getTotal();
		}
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public final int getPageSize() {
		return pageSize;
	}

	public final void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
