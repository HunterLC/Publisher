package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MyDbHelper;
import model.Bjs_Identifier;
import model.Bjs_Num;

public class IdentifierDao {
	
	public static IdentifierDao getInstance() {
		return new IdentifierDao();
	}
	
	/**
	 * 查询所有的编辑室信息
	 * @return
	 */
	public List<Bjs_Identifier> QueryAll() {
		List<Bjs_Identifier> list = new ArrayList<Bjs_Identifier>();
		String sql = "select * from bjs_Identifier order by Num";
		ResultSet rs = MyDbHelper.executeQuery(sql);
		try {
			while(rs.next()) {
				Bjs_Identifier item = new Bjs_Identifier();
				item.setID(rs.getInt("ID"));
				item.setNum(rs.getInt("Num"));
				item.setBjsName(rs.getString("bjsName"));
				item.setIsUse(rs.getString("isUse"));
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
	 * 按照编辑室名查询编辑室ID
	 * @param name
	 * @return
	 */
	public int QueryIDByName(String name) {
		String sql = "select ID from bjs_Identifier where bjsName = ?";
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
	 * 按照id查询编辑室名
	 * @param ID
	 * @return
	 */
	public String QueryNameByID(int ID) {
		String sql = "select bjsName from bjs_Identifier where ID = ?";
		Object[] parameters = {ID};
		ResultSet rs = MyDbHelper.executeQuery(sql,parameters);
		try {
			while(rs.next()) {
				return rs.getString("bjsName");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 按照id查询排序号
	 * @param ID
	 * @return
	 */
	public int QueryNumByID(int ID) {
		String sql = "select Num from bjs_Identifier where ID = ?";
		Object[] parameters = {ID};
		ResultSet rs = MyDbHelper.executeQuery(sql,parameters);
		try {
			while(rs.next()) {
				return rs.getInt("Num");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 查询编辑室数目
	 * @return
	 */
	public int QueryCount(){
		String sql = "select * from bjs_Identifier";
		return MyDbHelper.getCount(sql);
	}
	
	/**
	 * 判断编辑室是否存在
	 * @return
	 */
	public boolean nameIsExist(String bjsName){
		String sql = "select * from bjs_Identifier where bjsName = ?";
		Object[] parameters = {bjsName};
		if( MyDbHelper.getCount(sql, parameters) > 0)
			return true;
		return false;
	}
	
	/**
	 * 判断排序号是否存在
	 * @param num
	 * @return
	 */
	public boolean numIsExist(int num){
		String sql = "select * from bjs_Identifier where Num = ?";
		Object[] parameters = {num};
		if( MyDbHelper.getCount(sql, parameters) > 0)
			return true;
		return false;
	}
	
	/**
	 * 根据id修改编辑室名
	 * @param bjsName
	 * @param id
	 * @return
	 */
	public int Update(int num,String bjsName,String isUse,int id){
		String sql = "update bjs_Identifier set Num = ?,bjsName = ?,isUse = ? where ID = ?";
		Object[] parameters = {num,bjsName,isUse,id};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//执行影响行数不为0
			return 1;
		return 0;
	}
	
	/**
	 * 根据编辑室名删除
	 * @param bjsName
	 * @return
	 */
	public int Delete(String bjsName){
		String sql = "delete from bjs_Identifier where bjsName = ?";
		Object[] parameters = {bjsName};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//执行影响行数不为0
			return 1;
		return 0;
	}
	
	/**
	 * 添加编辑室
	 * @param bjsName
	 * @return
	 */
	public int Add(int num,String bjsName,String isUse){
		String sql = "insert into bjs_Identifier values(?,?,?)";
		Object[] parameters = {num,bjsName,isUse};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//执行影响行数不为0
			return 1;
		return 0;
	}
}