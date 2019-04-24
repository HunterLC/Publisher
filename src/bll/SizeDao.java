package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MyDbHelper;
import model.Editors;
import model.Size;

public class SizeDao {
	public static SizeDao getInstance() {
		return new SizeDao();
	}
	
	public List<Size> QueryAll() {
		List<Size> list = new ArrayList<Size>();
		String sql = "select * from Size";
		ResultSet rs = MyDbHelper.executeQuery(sql);
		try {
			while(rs.next()) {
				Size item = new Size();
				item.setID(rs.getInt("ID"));
				item.setSizeName(rs.getString("sizeName"));
				list.add(item);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int QueryIDByName(String sizeName) {
		String sql = "select ID from Size where sizeName = ?";
		Object[] parameters = {sizeName};
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
		String sql = "select sizeName from Size where ID = ?";
		Object[] parameters = {ID};
		ResultSet rs = MyDbHelper.executeQuery(sql,parameters);
		try {
			while(rs.next()) {
				return rs.getString("sizeName");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int QueryCount(){
		String sql = "select * from Size";
		return MyDbHelper.getCount(sql);
	}
	
	public boolean IsExist(String sizeName){
		String sql = "select * from Size where sizeName = ?";
		Object[] parameters = {sizeName};
		if( MyDbHelper.getCount(sql, parameters) > 0)
			return true;
		return false;
	}
}
