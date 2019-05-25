package bll;

import java.util.ArrayList;

import dal.GetData;
import model.BookInfor;
import model.SearchFactor;

public class SearchBLL {

	public ArrayList<BookInfor> searchBook(BookInfor bookInfor, String startTime, String endTime) throws Exception {
		String bookLanguage = bookInfor.getBookLanguage();
		String bjsName = bookInfor.getBjsName();
		String categorize = bookInfor.getCategorize();
		String sql = "select *from BooksInfor where bookLanguage = '" + bookLanguage + "' and bjsName = '" + bjsName
				+ "' and categorize = '" + categorize + "' and publishtime >= '" + startTime + "' and publishtime <= '"
				+ endTime + "'";
		ArrayList<BookInfor> list = new GetData().getBook(sql);
		return list;
	}

	public ArrayList<BookInfor> searchBook2(BookInfor bookInfor, String startTime, String endTime) throws Exception {
		int flag = 0;
		String bookLanguage = bookInfor.getBookLanguage();
		String bjsName = bookInfor.getBjsName();
		String categorize = bookInfor.getCategorize();
		String sql = "select *from BooksInfor where ";
		if (bookLanguage != null) {
			sql = sql + "bookLanguage = '" + bookLanguage + "'";
			flag = 1;
		}
		if (bjsName != null) {
			if (flag == 1) {
				sql = sql + " and ";
			} else {
				flag = 1;
			}
			sql = sql + "bjsName = '" + bjsName + "'";
		}
		if (categorize != null) {
			if (flag == 1) {
				sql = sql + " and ";
			} else {
				flag = 1;
			}
			sql = sql + "categorize = '" + categorize + "'";
		}
		if (flag == 1) {
			sql = sql + " and ";
		}
		sql = sql + "publishtime >= '" + startTime + "' and publishtime <= '" + endTime + "'";
		ArrayList<BookInfor> list = new GetData().getBook(sql);
		return list;
	}

	public ArrayList<BookInfor> searchBook3(String str) throws Exception {
		String s[] = str.split("\\s+");
		String sql = "select *from BooksInfor where ";
		for (int i = 0; i < s.length; i++) {
			sql = sql + "note like '%" + s[i] + "%'";
			if (i != s.length - 1) {
				sql = sql + " and ";
			}
		}
		ArrayList<BookInfor> list = new GetData().getBook(sql);
		return list;
	}

	public ArrayList<BookInfor> searchBook4() throws Exception {
		ArrayList<SearchFactor> list = new GetData().getFactor();
		String sql = "";
		String str;
		if (list.size() == 0) {
			sql = "select *from BooksInfor";
		} else {
			sql = "select *from BooksInfor where ";
			for (int i = 0; i < list.size() - 1; i++) {
				sql = sql + "(";
			}
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals("书名")) {
					str = "bookname";
				} else if (list.get(i).getField().equals("书号")) {
					str = "bookNO";
				} else if (list.get(i).getField().equals("作者")) {
					str = "author";
				} else if (list.get(i).getField().equals("图书分类")) {
					str = "categorize";
				} else if (list.get(i).getField().equals("编辑室")) {
					str = "bjsName";
				} else if (list.get(i).getField().equals("责任编辑")) {
					str = "bjName";
				} else if (list.get(i).getField().equals("出版时间")) {
					str = "publishtime";
				} else if (list.get(i).getField().equals("定价")) {
					str = "price";
				} else if (list.get(i).getField().equals("文种")) {
					str = "bookLanguage";
				} else {
					str = "num";
				}
				if (list.get(i).getOperator().equals("like")) {
					sql = sql + str + " " + list.get(i).getOperator() + " '%" + list.get(i).getValue() + "%' ";
				} else {
					sql = sql + str + " " + list.get(i).getOperator() + " '" + list.get(i).getValue() + "' ";
				}
				if (i != list.size() - 1) {
					sql = sql + " ) " + list.get(i + 1).getLogicaloperator() + " ";
				}
			}
		}
		ArrayList<BookInfor> list2 = new GetData().getBook(sql);
		return list2;
	}

	public ArrayList<BookInfor> searchBook5() throws Exception {
		String sql = "select *from BooksInfor";
		ArrayList<BookInfor> list = new GetData().getBook(sql);
		return list;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SearchBLL s = new SearchBLL();
	}

}
