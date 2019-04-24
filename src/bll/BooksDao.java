package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MyDbHelper;
import model.Books;

public class BooksDao {
	public static BooksDao getInstance() {
		return new BooksDao();
	}
	
	public List<Books> QueryAll(String searchText,String sortType){
		List<Books> list = new ArrayList<Books>();
		String sql = null;
		if(searchText == null||searchText.equals(""))
			sql = "select * from Books order by "+sortType;
		else
			sql = "select * from Books where  order by "+sortType;
		ResultSet rs = MyDbHelper.executeQuery(sql);
		try {
			while(rs.next()) {
				Books item = new Books();
				item.setID(rs.getInt("ID"));
				item.setBookName(rs.getString("bookName"));
				item.setAuthorName(rs.getString("authorName"));
				item.setBookNumber(rs.getString("bookNumber"));
				item.setBookTypeID(rs.getInt("bookTypeID"));
				item.setSizeID(rs.getInt("sizeID"));
				item.setPrintNumber(rs.getInt("printNumber"));
				item.setPrice(rs.getFloat("price"));
				item.setPublishTime(rs.getString("publishTime"));
				item.setArticleTypeID(rs.getInt("articleTypeID"));
				item.setBjsNameID(rs.getInt("bjsNameID"));
				item.setBjsEditorNameID(rs.getInt("bjsEditorNameID"));
				list.add(item);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int QueryIDByBookName(String name) {
		String sql = "select ID from Books where bookName = ?";
		Object[] parameters = {name};
		ResultSet rs = MyDbHelper.executeQuery(sql,parameters);
		try {
			while(rs.next()) {
				return rs.getInt("ID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int Update(String bookName,String authorName,String bookNumber,int bookTypeID,int sizeID,int printNumber,float price,String publishTime,int articleTypeID,int bjsNameID,int bjsEditorNameID,int id){
		String sql = "update Books set bookName = ?,authorName = ?,bookNumber = ?,bookTypeID = ?,sizeID = ?,printNumber = ?,price = ?,publishTime = ?,articleTypeID = ?,bjsNameID = ?,bjsEditorNameID = ? where ID = ?";
		Object[] parameters = {bookName,authorName,bookNumber,bookTypeID,sizeID,printNumber,price,publishTime,articleTypeID,bjsNameID,bjsEditorNameID,id};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//执行影响行数不为0
			return 1;
		return 0;
	}
	

	public int Delete(String bookName){
		String sql = "delete from Books where bookName = ?";
		Object[] parameters = {bookName};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//执行影响行数不为0
			return 1;
		return 0;
	}
	
	public int Add(String bookName,String authorName,String bookNumber,int bookTypeID,int sizeID,int printNumber,float price,String publishTime,int articleTypeID,int bjsNameID,int bjsEditorNameID){
		String sql = "insert into Books values(?,?,?,?,?,?,?,?,?,?,?)";
		Object[] parameters = {bookName,authorName,bookNumber,bookTypeID,sizeID,printNumber,price,publishTime,articleTypeID,bjsNameID,bjsEditorNameID};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//执行影响行数不为0
			return 1;
		return 0;
	}
	
	public int QueryCount(){
		String sql = "select * from Books";
		return MyDbHelper.getCount(sql);
	}
	
	public String QueryBookNameByID(int ID) {
		String sql = "select bookName from Books where ID = ?";
		Object[] parameters = {ID};
		ResultSet rs = MyDbHelper.executeQuery(sql,parameters);
		try {
			while(rs.next()) {
				return rs.getString("bookName");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String QueryBookNumberByID(int ID) {
		String sql = "select bookNumber from Books where ID = ?";
		Object[] parameters = {ID};
		ResultSet rs = MyDbHelper.executeQuery(sql,parameters);
		try {
			while(rs.next()) {
				return rs.getString("bookNumber");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean bookNameIsExist(String bookName){
		String sql = "select * from Books where bookName = ?";
		Object[] parameters = {bookName};
		if( MyDbHelper.getCount(sql, parameters) > 0)
			return true;
		return false;
	}
	public boolean bookNumberIsExist(String bookNumber){
		String sql = "select * from Books where bookNumber = ?";
		Object[] parameters = {bookNumber};
		if( MyDbHelper.getCount(sql, parameters) > 0)
			return true;
		return false;
	}
}
