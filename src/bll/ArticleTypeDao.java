package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MyDbHelper;
import model.ArticleType;
import model.Size;

public class ArticleTypeDao {
	public static ArticleTypeDao getInstance() {
		return new ArticleTypeDao();
	}
	
	public List<ArticleType> QueryAll() {
		List<ArticleType> list = new ArrayList<ArticleType>();
		String sql = "select * from ArticleType";
		ResultSet rs = MyDbHelper.executeQuery(sql);
		try {
			while(rs.next()) {
				ArticleType item = new ArticleType();
				item.setID(rs.getInt("ID"));
				item.setArticleTypeName(rs.getString("articleTypeName"));
				list.add(item);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int QueryIDByName(String articleTypeName) {
		String sql = "select ID from ArticleType where articleTypeName = ?";
		Object[] parameters = {articleTypeName};
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
	
	public String QueryNameByID(int ID) {
		String sql = "select articleTypeName from ArticleType where ID = ?";
		Object[] parameters = {ID};
		ResultSet rs = MyDbHelper.executeQuery(sql,parameters);
		try {
			while(rs.next()) {
				return rs.getString("articleTypeName");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int QueryCount(){
		String sql = "select * from ArticleType";
		return MyDbHelper.getCount(sql);
	}
	
	public boolean IsExist(String articleTypeName){
		String sql = "select * from ArticleType where articleTypeName = ?";
		Object[] parameters = {articleTypeName};
		if( MyDbHelper.getCount(sql, parameters) > 0)
			return true;
		return false;
	}
}
