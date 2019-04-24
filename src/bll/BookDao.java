package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MyDbHelper;
import model.Book;
import model.Books;

public class BookDao {
	public static BookDao getInstance() {
		return new BookDao();
	}
	
	public List<Book> QueryAll(String searchText,String sortType){
		//Book类不同于Books之处为Books对应数据库原始数据，而Book对应表格展示数据
		List<Book> list = new ArrayList<Book>();
		String sql = null;
		ResultSet rs = null;
		if(searchText == null||searchText.equals("")) {
			sql = "select Books.ID,bookName,authorName,bookNumber,bookType,sizeName,printNumber,price,publishTime,articleTypeName,bjsName,editorName"
					+" from Books,bjs_Code,bjs_Identifier,ArticleType,Editors,Size"
					+" where Books.bookTypeID=bjs_Code.ID and Books.sizeID=Size.ID and Books.articleTypeID=ArticleType.ID and Books.bjsNameID=Bjs_Identifier.ID and Books.bjsEditorNameID=Editors.ID"
					+" order by "+sortType;
			rs = MyDbHelper.executeQuery(sql);
		}
			
		else {
			sql = "select Books.ID,bookName,authorName,bookNumber,bookType,sizeName,printNumber,price,publishTime,articleTypeName,bjsName,editorName"
					+" from Books,bjs_Code,bjs_Identifier,ArticleType,Editors,Size"
					+" where Books.bookTypeID=bjs_Code.ID and Books.sizeID=Size.ID and Books.articleTypeID=ArticleType.ID and Books.bjsNameID=Bjs_Identifier.ID and Books.bjsEditorNameID=Editors.ID"
					//+" and Books.ID,bookName,authorName,bookNumber,bookType,sizeName,printNumber,price,publishTime,articleTypeName,bjsName,editorName like %" //增加搜索
					+" and (Books.ID like '%" //增加搜索
					+searchText
					+"%' or"
					+" bookName like '%"
					+searchText
					+"%' or"
					+" authorName like '%"
					+searchText
					+"%' or"
					+" bookNumber like '%" 
					+searchText
					+"%' or"
					+" bookType like '%"
					+searchText
					+"%' or"
					+" sizeName like '%"
					+searchText
					+"%' or"
					+" printNumber like '%" 
					+searchText
					+"%' or"
					+" price like '%"
					+searchText
					+"%' or"
					+" publishTime like '%"
					+searchText
					+"%' or"
					+" articleTypeName like '%" 
					+searchText
					+"%' or"
					+" bjsName like '%"
					+searchText
					+"%' or"
					+" editorName like '%"
					+searchText
					+"%'"
					+") order by "+sortType;
			rs = MyDbHelper.executeQuery(sql);
		}
		try {
			while(rs.next()) {
				Book item = new Book();
				item.setID(rs.getInt("ID"));
				item.setBookName(rs.getString("bookName"));
				item.setAuthorName(rs.getString("authorName"));
				item.setBookNumber(rs.getString("bookNumber"));
				item.setBookType(rs.getString("bookType"));
				item.setSize(rs.getString("sizeName"));
				item.setPrintNumber(rs.getInt("printNumber"));
				item.setPrice(rs.getFloat("price"));
				item.setPublishTime(rs.getString("publishTime"));
				item.setArticleType(rs.getString("articleTypeName"));
				item.setBjsName(rs.getString("bjsName"));
				item.setBjsEditorName(rs.getString("editorName"));
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
