package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import model.BookInfor;
import model.SearchFactor;

public class GetData {

	public Connection connection() throws Exception {// 连接数据库
		String user = "root";
		String password = "123456";
		String url = "jdbc:mysql://localhost:3306/db7?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		Connection conn = (Connection) DriverManager.getConnection(url, user, password);
		return conn;
	}

	public ArrayList<BookInfor> getBook(String sql) throws Exception {
		ArrayList<BookInfor> list = new ArrayList<BookInfor>();
		Connection conn = connection();
		Statement stat = (Statement) conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		while (rs.next()) {
			BookInfor book = new BookInfor();
			book.setID(rs.getInt("id"));
			book.setBookNO(rs.getString("bookNO"));
			book.setBookName(rs.getString("bookName"));
			book.setBjsName(rs.getString("bjsName"));
			book.setAuthor(rs.getString("author"));
			book.setPrice(rs.getString("price"));
			book.setBjName(rs.getString("bjName"));
			book.setNum(rs.getString("num"));
			book.setFormat(rs.getString("format"));
			book.setPublishtime(rs.getString("publishtime"));
			book.setCategorize(rs.getString("categorize"));
			book.setPublisher(rs.getString("publisher"));
			book.setBookLanguage(rs.getString("bookLanguage"));
			book.setPages(rs.getString("pages"));
			book.setNote(rs.getString("note"));
			list.add(book);
		}
		conn.close();
		return list;
	}

	public void addFactor(int id, String logicaloperator, String field, String operator, String value)
			throws Exception {
		Connection conn = connection();
		String sql = "insert into SearchFactor values(?, ?, ?, ?, ?)";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setString(2, logicaloperator);
		ps.setString(3, field);
		ps.setString(4, operator);
		ps.setString(5, value);
		ps.executeUpdate();
		conn.close();
	}

	public void deleteFactor(int id, int n) throws Exception {
		Connection conn = connection();
		String sql = "delete from SearchFactor where id = ?";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		if (n != 0) {
			sql = "update SearchFactor set logicaloperator = '' where id = ?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, n);
			ps.executeUpdate();
		}
		conn.close();
	}

	public ArrayList<SearchFactor> getFactor() throws Exception {// 获取带有序号的基本信息
		ArrayList<SearchFactor> list = new ArrayList<SearchFactor>();
		Connection conn = connection();
		String sql = "select *from SearchFactor ORDER BY ID";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			SearchFactor s = new SearchFactor();
			s.setID(rs.getInt("id"));
			s.setLogicaloperator(rs.getString("logicaloperator"));
			s.setField(rs.getString("field"));
			s.setOperator(rs.getString("operator"));
			s.setValue(rs.getString("value"));
			list.add(s);
		}
		conn.close();
		return list;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// ArrayList<BookInfor> list = new GetData().getBook("select *from BooksInfor");
		// System.out.println(list.get(2).getNote());
	}

}
