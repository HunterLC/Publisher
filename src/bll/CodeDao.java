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
	 * ��ѯ���е���Ϣ
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
	 * ��������������ѯID
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
	 * ����id��ѯ������
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
	 * ����id��ѯ�����
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
	 * ��ѯ��������Ŀ
	 * @return
	 */
	public int QueryCount(){
		String sql = "select * from bjs_Code";
		return MyDbHelper.getCount(sql);
	}
	
	/**
	 * �ж����������Ƿ����
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
	 * �жϴ�����Ƿ����
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
	 * ����id�޸�������
	 * @param bjsName
	 * @param id
	 * @return
	 */
	public int Update(String code,String bookType,int id){
		String sql = "update bjs_Code set Code = ?,bookType = ? where ID = ?";
		Object[] parameters = {code,bookType,id};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//ִ��Ӱ��������Ϊ0
			return 1;
		return 0;
	}
	
	/**
	 * ����������ɾ��
	 * @param bjsName
	 * @return
	 */
	public int Delete(String bookType){
		String sql = "delete from bjs_Code where bookType = ?";
		Object[] parameters = {bookType};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//ִ��Ӱ��������Ϊ0
			return 1;
		return 0;
	}
	
	/**
	 * ���������
	 * @param bjsName
	 * @return
	 */
	public int Add(String code,String bookType){
		String sql = "insert into bjs_Code values(?,?)";
		Object[] parameters = {code,bookType};
		if( MyDbHelper.executeNonQuery(sql, parameters) != 0)//ִ��Ӱ��������Ϊ0
			return 1;
		return 0;
	}
}