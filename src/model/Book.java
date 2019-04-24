package model;

public class Book {
	private int ID;
	private String bookName;
	private String authorName;
	private String bookNumber;
	private String bookType;
	private String size;
	private int printNumber;
	private float price;
	private String publishTime;
	private String articleType;
	private String bjsName;
	private String bjsEditorName;
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
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
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
	public String getBjsEditorName() {
		return bjsEditorName;
	}
	public void setBjsEditorName(String bjsEditorName) {
		this.bjsEditorName = bjsEditorName;
	}
}
