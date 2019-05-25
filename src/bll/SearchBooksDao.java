package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MyDbHelper;
import model.SearchBooks;

public class SearchBooksDao {
	public static SearchBooksDao getInstance() {
		return new SearchBooksDao();
	}
	
	/**
	 * 查询书目
	 * @param condition
	 * @return
	 */
	public List<SearchBooks> QueryAll(String condition){
		List<SearchBooks> list = new ArrayList<SearchBooks>();
		String sql = null;
		ResultSet rs = null;
		//没有条件
		if(condition == null||condition.equals("")) {
			sql = "select * from SearchBooks";
			rs = MyDbHelper.executeQuery(sql);
		}
		//有条件
		else {
			sql = "select * from SearchBooks where"+condition;
			rs = MyDbHelper.executeQuery(sql);
		}
		try {
			while(rs.next()) {
				SearchBooks item = new SearchBooks();
				item.setID(rs.getInt("ID"));
				item.setBookName(rs.getString("bookName"));
				item.setAuthorName(rs.getString("authorName"));
				item.setBookNumber(rs.getString("bookNumber"));
				item.setBookType(rs.getString("bookType"));
				item.setSize(rs.getString("size"));
				item.setPrintNumber(rs.getInt("printNumber"));
				item.setPrice(rs.getFloat("price"));
				item.setPublishTime(rs.getDate("publishTime"));
				item.setArticleType(rs.getString("articleType"));
				item.setBjsName(rs.getString("bjsName"));
				item.setEditorName(rs.getString("editorName"));
				item.setMerge(rs.getString("merge"));
				list.add(item);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 按照书名查id
	 * @param name
	 * @return
	 */
	public int QueryIDByBookName(String name) {
		String sql = "select ID from SearchBooks where bookName = ?";
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
	
	/**
	 * 查询书本数目
	 * @return
	 */
	public int QueryCount(){
		String sql = "select * from SearchBooks";
		return MyDbHelper.getCount(sql);
	}
	
	/**
	 *按照id查询书名
	 * @param ID
	 * @return
	 */
	public String QueryBookNameByID(int ID) {
		String sql = "select bookName from SearchBooks where ID = ?";
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
	
	/**
	 * 按照id查书号
	 * @param ID
	 * @return
	 */
	public String QueryBookNumberByID(int ID) {
		String sql = "select bookNumber from SearchBooks where ID = ?";
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
	
	/**
	 * 书名是否存在
	 * @param bookName
	 * @return
	 */
	public boolean bookNameIsExist(String bookName){
		String sql = "select * from SearchBooks where bookName = ?";
		Object[] parameters = {bookName};
		if( MyDbHelper.getCount(sql, parameters) > 0)
			return true;
		return false;
	}
	
	/**
	 * 书号是否存在
	 * @param bookNumber
	 * @return
	 */
	public boolean bookNumberIsExist(String bookNumber){
		String sql = "select * from SearchBooks where bookNumber = ?";
		Object[] parameters = {bookNumber};
		if( MyDbHelper.getCount(sql, parameters) > 0)
			return true;
		return false;
	}
}
