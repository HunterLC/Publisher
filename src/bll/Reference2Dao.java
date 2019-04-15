package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MyDbHelper;
import model.Bjs_Identifier;
import model.Reference2;

public class Reference2Dao {
	public static Reference2Dao getInstance() {
		return new Reference2Dao();
	}
	
	/**
	 * 查询方案一，能用的
	 * @return
	 */
	public List<Reference2> QueryAllByMethod1(){
		List<Reference2> list = new ArrayList<Reference2>();
		String sql = "select * from ReferenceTest2 order by bjsID";
		ResultSet rs = MyDbHelper.executeQuery(sql);
		try {
			while(rs.next()) {
				Reference2 item = new Reference2();
				item.setID(rs.getInt("ID"));
				item.setBjsID(rs.getInt("bjsID"));
				item.setBookTypeID(rs.getInt("bookTypeID"));
				item.setShum(rs.getString("shum"));
				list.add(item);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int QueryIDByName(String name) {
		String sql = "select ID from ReferenceTest2 where shum = ?";
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
	
	public int Update(int bjsID,int bookTypeID,String shum,int id){
		String sql = "update ReferenceTest2 set bjsID = ?,bookTypeID = ?,shum = ? where ID = ?";
		Object[] parameters = {bjsID,bookTypeID,shum,id};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//执行影响行数不为0
			return 1;
		return 0;
	}
	

	public int Delete(String shum){
		String sql = "delete from ReferenceTest2 where shum = ?";
		Object[] parameters = {shum};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//执行影响行数不为0
			return 1;
		return 0;
	}
	
	public int Add(int bjsID,int bookTypeID,String shum){
		String sql = "insert into ReferenceTest2 values(?,?,?)";
		Object[] parameters = {bjsID,bookTypeID,shum};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//执行影响行数不为0
			return 1;
		return 0;
	}
	
	public int QueryCount(){
		String sql = "select * from ReferenceTest2";
		return MyDbHelper.getCount(sql);
	}
	
	public String QueryShumByID(int ID) {
		String sql = "select shum from ReferenceTest2 where ID = ?";
		Object[] parameters = {ID};
		ResultSet rs = MyDbHelper.executeQuery(sql,parameters);
		try {
			while(rs.next()) {
				return rs.getString("shum");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean shumIsExist(String shum){
		String sql = "select * from ReferenceTest2 where shum = ?";
		Object[] parameters = {shum};
		if( MyDbHelper.getCount(sql, parameters) > 0)
			return true;
		return false;
	}

}
