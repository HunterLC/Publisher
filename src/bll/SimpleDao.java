package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MyDbHelper;
import model.Bjs_Simple;

public class SimpleDao {
	
	public static SimpleDao getInstance() {
		return new SimpleDao();
	}
	
	/**
	 * ��ѯ���еı༭����Ϣ
	 * @return
	 */
	public List<Bjs_Simple> QueryAll() {
		List<Bjs_Simple> list = new ArrayList<Bjs_Simple>();
		String sql = "select * from bjs_simple order by bjsName";
		ResultSet rs = MyDbHelper.executeQuery(sql);
		try {
			while(rs.next()) {
				Bjs_Simple item = new Bjs_Simple();
				item.setID(rs.getInt("ID"));
				item.setBjsName(rs.getString("bjsName"));
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
	 * ���ձ༭������ѯ�༭��ID
	 * @param name
	 * @return
	 */
	public int QueryIDByName(String name) {
		String sql = "select ID from bjs_simple where bjsName = ?";
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
	 * ����id��ѯ�༭����
	 * @param ID
	 * @return
	 */
	public String QueryNameByID(int ID) {
		String sql = "select bjsName from bjs_simple where ID = ?";
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
	 * ��ѯ�༭����Ŀ
	 * @return
	 */
	public int QueryCount(){
		String sql = "select * from bjs_simple";
		return MyDbHelper.getCount(sql);
	}
	
	/**
	 * �жϱ༭���Ƿ����
	 * @return
	 */
	public boolean IsExist(String bjsName){
		String sql = "select * from bjs_simple where bjsName = ?";
		Object[] parameters = {bjsName};
		if( MyDbHelper.getCount(sql, parameters) > 0)
			return true;
		return false;
	}
	
	/**
	 * ����id�޸ı༭����
	 * @param bjsName
	 * @param id
	 * @return
	 */
	public int Update(String bjsName,int id){
		String sql = "update bjs_simple set bjsName = ? where ID = ?";
		Object[] parameters = {bjsName,id};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//ִ��Ӱ��������Ϊ0
			return 1;
		return 0;
	}
	
	/**
	 * ���ݱ༭����ɾ��
	 * @param bjsName
	 * @return
	 */
	public int Delete(String bjsName){
		String sql = "delete from bjs_simple where bjsName = ?";
		Object[] parameters = {bjsName};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//ִ��Ӱ��������Ϊ0
			return 1;
		return 0;
	}
	
	/**
	 * ��ӱ༭��
	 * @param bjsName
	 * @return
	 */
	public int Add(String bjsName){
		String sql = "insert into bjs_simple values(?)";
		Object[] parameters = {bjsName};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//ִ��Ӱ��������Ϊ0
			return 1;
		return 0;
	}
}
