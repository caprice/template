package com.gm.infobus.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.gm.infobus.repository.base.Pagination;

public class PageTag extends SimpleTagSupport {
	private Pagination pagination;
	private String baseurl;
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}
	// 分页区页码最大长度
	private final static int MAX_PAGE_HTML_LENGTH = 8;
	// 首部和尾部连续页码的长度
	private final static int PRE_LAST_PAGE_LENGTH = 5;
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		if (pagination == null || pagination.getItems() == null
				|| pagination.getItems().size() == 0) {
			getJspContext().getOut().print("");
			return;
		}
		// 上一页
		if (pagination.getPageCount() > 1) {
			if (pagination.getPageIndex() == 1) {
				sb.append(createPrePage(0, true));
			} else {
				sb.append(createPrePage(pagination.getPageIndex() - 1, false));
			}
		}
		// 当总页数小于8时显示全部页码
		if (pagination.getPageCount() <= MAX_PAGE_HTML_LENGTH) {
			for (int i = 1; i <= pagination.getPageCount(); i++) {
				if (i == pagination.getPageIndex()) {
					sb.append(createPageIndex(i, true));
				} else {
					sb.append(createPageIndex(i, false));
				}
			}
		} else {
			// 当页码小于等于5时
			if (pagination.getPageIndex() <= PRE_LAST_PAGE_LENGTH) {
				sb.append(createHeader(pagination.getPageIndex() + 1,
						pagination));
				sb.append(createMiddle(0, pagination));
				sb.append(createFooter(pagination));
			} else if (pagination.getPageIndex() + 4 >= pagination
					.getPageCount()) {// 当页码为后5页时
				sb.append(createHeader(2, pagination));
				sb.append(createMiddle(0, pagination));
				sb.append(createFooter(pagination));
			} else {
				sb.append(createHeader(2, pagination));
				sb.append(createMiddle(3, pagination));
				sb.append(createFooter(pagination));
			}
		}
		// 下一页
		if (pagination.getPageCount() > 1) {
			if (pagination.getPageIndex() == pagination.getPageCount()) {
				sb.append(createNextPage(0, true));
			} else {
				sb.append(createNextPage(pagination.getPageIndex() + 1, false));
			}
		}
		sb.append(createPageInfo(pagination));
		getJspContext().getOut().print(sb.toString());
	}
	/**
	 * 头部创建
	 * 
	 * @param length
	 * @param pagination
	 * @return
	 */
	private String createHeader(int length, Pagination pagination) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= length; i++) {
			if (i == pagination.getPageIndex()) {
				sb.append(createPageIndex(i, true));
			} else {
				sb.append(createPageIndex(i, false));
			}
		}
		return sb.toString();
	}
	/**
	 * 中部创建
	 * 
	 * @param length
	 * @param pagination
	 * @return
	 */
	private String createMiddle(int length, Pagination pagination) {
		StringBuilder sb = new StringBuilder();
		if (length == 0) {// 创建点...
			sb.append(createPoint());
		} else {
			sb.append(createPoint());
			for (int i = pagination.getPageIndex() - 1; i <= pagination
					.getPageIndex() + 1; i++) {
				if (i == pagination.getPageIndex()) {
					sb.append(createPageIndex(i, true));
				} else {
					sb.append(createPageIndex(i, false));
				}
			}
			sb.append(createPoint());
		}
		return sb.toString();
	}
	/**
	 * 尾部创建
	 * 
	 * @param pagination
	 * @return
	 */
	private String createFooter(Pagination pagination) {
		StringBuilder sb = new StringBuilder();
		if (pagination.getPageIndex() <= 5
				|| pagination.getPageIndex() + 4 < pagination.getPageCount()) {
			for (int i = pagination.getPageCount() - 1; i <= pagination
					.getPageCount(); i++) {
				sb.append(createPageIndex(i, false));
			}
		} else {
			for (int i = pagination.getPageIndex() - 1; i <= pagination
					.getPageCount(); i++) {
				if (i == pagination.getPageIndex()) {
					sb.append(createPageIndex(i, true));
				} else {
					sb.append(createPageIndex(i, false));
				}
			}
		}
		return sb.toString();
	}
	private String createPrePage(int pageIndex, boolean distable) {
		StringBuilder sb = new StringBuilder();
		if (distable) {
			sb.append("<span class='disqp'>上一页</span>");
		} else {
			sb.append("<a href='" + baseurl + getUrlStartChar()+"pageindex=" + pageIndex
					+ "' class='qp'>上一页</a>");
		}
		return sb.toString();
	}
	private String createNextPage(int pageIndex, boolean distable) {
		StringBuilder sb = new StringBuilder();
		if (distable) {
			sb.append("<span class='disqp'>下一页 </span>");
		} else {
			sb.append("<a href='" + baseurl + getUrlStartChar()+"pageindex=" + pageIndex
					+ "' class='qp'>下一页 </a>");
		}
		return sb.toString();
	}
	private String createPageIndex(int pageIndex, boolean cur) {
		StringBuilder sb = new StringBuilder();
		if (!cur) {
			sb.append("<a href='" + baseurl + getUrlStartChar()+"pageindex=" + pageIndex + "'>");
			sb.append(pageIndex);
			sb.append("</a>");
		} else {
			sb.append("<span class='focuspage'>" + pageIndex + "</span>");
		}
		return sb.toString();
	}
	private String createPoint() {
		return " <span class='point'>...</span>";
	}
	private String createPageInfo(Pagination pagination) {
		return "<span class='info'>共<font color=red>" + pagination.getTotal()
				+ "</font>条&nbsp;&nbsp;共<font color=red>"
				+ pagination.getPageCount() + "</font>页</span>";
	}
	
	/**
	 * 返回URL参数首字符
	 * @return
	 */
	private String getUrlStartChar(){
		if(baseurl.indexOf("?")>=0){
			return "&";
		}else{
			return "?";
		}
	}
}
