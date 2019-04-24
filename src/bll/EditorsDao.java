package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MyDbHelper;
import model.Bjs_Identifier;
import model.Editors;

public class EditorsDao {
	public static EditorsDao getInstance() {
		return new EditorsDao();
	}
	
	public List<Editors> QueryAll() {
		List<Editors> list = new ArrayList<Editors>();
		String sql = "select * from Editors";
		ResultSet rs = MyDbHelper.executeQuery(sql);
		try {
			while(rs.next()) {
				Editors item = new Editors();
				item.setID(rs.getInt("ID"));
				item.setEditorName(rs.getString("editorName"));
				item.setEditorBjsID(rs.getInt("editorBjsID"));
				list.add(item);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int QueryIDByName(String editorName) {
		String sql = "select ID from Editors where editorName = ?";
		Object[] parameters = {editorName};
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
		String sql = "select editorName from Editors where ID = ?";
		Object[] parameters = {ID};
		ResultSet rs = MyDbHelper.executeQuery(sql,parameters);
		try {
			while(rs.next()) {
				return rs.getString("editorName");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int QueryCount(){
		String sql = "select * from Editors";
		return MyDbHelper.getCount(sql);
	}
	
	public boolean IsExist(String editorName){
		String sql = "select * from Editors where editorName = ?";
		Object[] parameters = {editorName};
		if( MyDbHelper.getCount(sql, parameters) > 0)
			return true;
		return false;
	}
}
