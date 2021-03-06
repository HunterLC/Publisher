package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MyDbHelper;
import model.Bjs_Identifier;
import model.Reference3;

public class Reference3Dao {
	public static Reference3Dao getInstance() {
		return new Reference3Dao();
	}
	
	/**
	 * 查询方案一，能用的
	 * @return
	 */
	public List<Reference3> QueryAllByMethod1(){
		List<Reference3> list = new ArrayList<Reference3>();
		String sql = "select * from ReferenceTest3 order by bjsID";
		ResultSet rs = MyDbHelper.executeQuery(sql);
		try {
			while(rs.next()) {
				Reference3 item = new Reference3();
				item.setID(rs.getInt("ID"));
				item.setBjsID(rs.getInt("bjsID"));
				item.setBookTypeID(rs.getString("bookTypeID"));
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
		String sql = "select ID from ReferenceTest3 where shum = ?";
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
	
	public int Update(int bjsID,String bookTypeID,String shum,int id){
		String sql = "update ReferenceTest3 set bjsID = ?,bookTypeID = ?,shum = ? where ID = ?";
		Object[] parameters = {bjsID,bookTypeID,shum,id};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//执行影响行数不为0
			return 1;
		return 0;
	}
	

	public int Delete(String shum){
		String sql = "delete from ReferenceTest3 where shum = ?";
		Object[] parameters = {shum};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//执行影响行数不为0
			return 1;
		return 0;
	}
	
	public int Add(int bjsID,String bookTypeID,String shum){
		String sql = "insert into ReferenceTest3 values(?,?,?)";
		Object[] parameters = {bjsID,bookTypeID,shum};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//执行影响行数不为0
			return 1;
		return 0;
	}
	
	public int QueryCount(){
		String sql = "select * from ReferenceTest3";
		return MyDbHelper.getCount(sql);
	}
	
	public String QueryShumByID(int ID) {
		String sql = "select shum from ReferenceTest3 where ID = ?";
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
		String sql = "select * from ReferenceTest3 where shum = ?";
		Object[] parameters = {shum};
		if( MyDbHelper.getCount(sql, parameters) > 0)
			return true;
		return false;
	}

}
