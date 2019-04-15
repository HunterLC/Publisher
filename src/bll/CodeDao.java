package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MyDbHelper;
import model.Bjs_Code;

public class CodeDao {
	
	public static CodeDao getInstance() {
		return new CodeDao();
	}
	
	/**
	 * 查询所有的信息
	 * @return
	 */
	public List<Bjs_Code> QueryAll() {
		List<Bjs_Code> list = new ArrayList<Bjs_Code>();
		String sql = "select * from bjs_Code";
		ResultSet rs = MyDbHelper.executeQuery(sql);
		try {
			while(rs.next()) {
				Bjs_Code item = new Bjs_Code();
				item.setID(rs.getInt("ID"));
				item.setCode(rs.getString("Code"));
				item.setBookType(rs.getString("bookType"));
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
	 * 按照书类型名查询ID
	 * @param name
	 * @return
	 */
	public int QueryIDByName(String name) {
		String sql = "select ID from bjs_Code where booktype = ?";
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
	 * 按照id查询书类型
	 * @param ID
	 * @return
	 */
	public String QueryNameByID(int ID) {
		String sql = "select bookType from bjs_Code where ID = ?";
		Object[] parameters = {ID};
		ResultSet rs = MyDbHelper.executeQuery(sql,parameters);
		try {
			while(rs.next()) {
				return rs.getString("bookType");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 按照id查询代码号
	 * @param ID
	 * @return
	 */
	public String QueryCodeByID(int ID) {
		String sql = "select Code from bjs_Code where ID = ?";
		Object[] parameters = {ID};
		ResultSet rs = MyDbHelper.executeQuery(sql,parameters);
		try {
			while(rs.next()) {
				return rs.getString("Code");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询书类型数目
	 * @return
	 */
	public int QueryCount(){
		String sql = "select * from bjs_Code";
		return MyDbHelper.getCount(sql);
	}
	
	/**
	 * 判断书类型名是否存在
	 * @return
	 */
	public boolean nameIsExist(String bjsName){
		String sql = "select * from bjs_Code where bookType = ?";
		Object[] parameters = {bjsName};
		if( MyDbHelper.getCount(sql, parameters) > 0)
			return true;
		return false;
	}
	
	/**
	 * 判断代码号是否存在
	 * @param num
	 * @return
	 */
	public boolean codeIsExist(String code){
		String sql = "select * from bjs_Code where Code = ?";
		Object[] parameters = {code};
		if( MyDbHelper.getCount(sql, parameters) > 0)
			return true;
		return false;
	}
	
	/**
	 * 根据id修改类型名
	 * @param bjsName
	 * @param id
	 * @return
	 */
	public int Update(String code,String bookType,int id){
		String sql = "update bjs_Code set Code = ?,bookType = ? where ID = ?";
		Object[] parameters = {code,bookType,id};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//执行影响行数不为0
			return 1;
		return 0;
	}
	
	/**
	 * 根据类型名删除
	 * @param bjsName
	 * @return
	 */
	public int Delete(String bookType){
		String sql = "delete from bjs_Code where bookType = ?";
		Object[] parameters = {bookType};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//执行影响行数不为0
			return 1;
		return 0;
	}
	
	/**
	 * 添加类型名
	 * @param bjsName
	 * @return
	 */
	public int Add(String code,String bookType){
		String sql = "insert into bjs_Code values(?,?)";
		Object[] parameters = {code,bookType};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//执行影响行数不为0
			return 1;
		return 0;
	}
}