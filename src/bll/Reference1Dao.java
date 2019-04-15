package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MyDbHelper;
import model.Bjs_Identifier;
import model.Reference1;

public class Reference1Dao {
	public static Reference1Dao getInstance() {
		return new Reference1Dao();
	}
	
	/**
	 * 查询方案一，能用的
	 * @return
	 */
	public List<Reference1> QueryAllByMethod1(){
		List<Reference1> list = new ArrayList<Reference1>();
		String sql = "select * from ReferenceTest1 order by bjsName";
		ResultSet rs = MyDbHelper.executeQuery(sql);
		try {
			while(rs.next()) {
				Reference1 item = new Reference1();
				item.setID(rs.getInt("ID"));
				item.setBjsName(rs.getString("bjsName"));
				item.setBookType(rs.getString("bookType"));
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
		String sql = "select ID from ReferenceTest1 where shum = ?";
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
	
	public int Update(String bjsName,String bookType,String shum,int id){
		String sql = "update ReferenceTest1 set bjsName = ?,bookType = ?,shum = ? where ID = ?";
		Object[] parameters = {bjsName ,bookType,shum,id};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//执行影响行数不为0
			return 1;
		return 0;
	}
	

	public int Delete(String shum){
		String sql = "delete from ReferenceTest1 where shum = ?";
		Object[] parameters = {shum};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//执行影响行数不为0
			return 1;
		return 0;
	}
	
	public int Add(String bjsName,String bookType,String shum){
		String sql = "insert into ReferenceTest1 values(?,?,?)";
		Object[] parameters = {bjsName ,bookType,shum};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//执行影响行数不为0
			return 1;
		return 0;
	}
	
	public int QueryCount(){
		String sql = "select * from ReferenceTest1";
		return MyDbHelper.getCount(sql);
	}
	
	public String QueryShumByID(int ID) {
		String sql = "select shum from ReferenceTest1 where ID = ?";
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
		String sql = "select * from ReferenceTest1 where shum = ?";
		Object[] parameters = {shum};
		if( MyDbHelper.getCount(sql, parameters) > 0)
			return true;
		return false;
	}

}
