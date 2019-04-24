package model;

public class Books {
	private int ID;
	private String bookName;
	private String authorName;
	private String bookNumber;
	private int bookTypeID;
	private int sizeID;
	private int printNumber;
	private float price;
	private String publishTime;
	private int articleTypeID;
	private int bjsNameID;
	private int bjsEditorNameID;
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
	public int getBookTypeID() {
		return bookTypeID;
	}
	public void setBookTypeID(int bookTypeID) {
		this.bookTypeID = bookTypeID;
	}
	public int getSizeID() {
		return sizeID;
	}
	public void setSizeID(int sizeID) {
		this.sizeID = sizeID;
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
	public int getArticleTypeID() {
		return articleTypeID;
	}
	public void setArticleTypeID(int articleTypeID) {
		this.articleTypeID = articleTypeID;
	}
	public int getBjsNameID() {
		return bjsNameID;
	}
	public void setBjsNameID(int bjsNameID) {
		this.bjsNameID = bjsNameID;
	}
	public int getBjsEditorNameID() {
		return bjsEditorNameID;
	}
	public void setBjsEditorNameID(int bjsEditorNameID) {
		this.bjsEditorNameID = bjsEditorNameID;
	}
}
