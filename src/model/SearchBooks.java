package model;

import java.util.Date;

public class SearchBooks {
	private int ID;  //表主键
	private String bookName;//书名
	private String authorName;//作者名
	private String bookNumber;//书号
	private String bookType;//书类别
	private String size;//开本
	private int printNumber;//印数
	private float price;//价格
	private Date publishTime;//出版日期
	private String articleType;//文种
	private String bjsName;//编辑室名
	private String editorName;//责任编辑
	private String merge;//所有数据合并成串
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getBookNumber() {
		return bookNumber;
	}
	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getPrintNumber() {
		return printNumber;
	}
	public void setPrintNumber(int printNumber) {
		this.printNumber = printNumber;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public String getArticleType() {
		return articleType;
	}
	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}
	public String getBjsName() {
		return bjsName;
	}
	public void setBjsName(String bjsName) {
		this.bjsName = bjsName;
	}
	public String getEditorName() {
		return editorName;
	}
	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}
	public String getMerge() {
		return merge;
	}
	public void setMerge(String merge) {
		this.merge = merge;
	}
}
