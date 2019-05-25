package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dal.MyDbHelper;
import model.Book;
import model.SearchBooks;

public class FuzzyQueryDao {
	public MyDbHelper myDbhelper = new MyDbHelper();

	public Object[][] getBookList(String searchText) throws SQLException {// 获得图书列表显示的obj
		List<SearchBooks> result = FuzzyQuery(searchText);
		Object[][] obj = new Object[result.size()][11];
		for (int i = 0; i < result.size(); i++) {
			obj[i][0] = result.get(i).getBookNumber();
			obj[i][1] = result.get(i).getBookName();
			obj[i][2] = result.get(i).getAuthorName();
			obj[i][3] = result.get(i).getBookType();
			obj[i][4] = result.get(i).getSize();
			obj[i][5] = result.get(i).getPrintNumber();
			obj[i][6] = result.get(i).getPrice();
			obj[i][7] = result.get(i).getPublishTime();
			obj[i][8] = result.get(i).getArticleType();
			obj[i][9] = result.get(i).getBjsName();
			obj[i][10] = result.get(i).getEditorName();
		}
		return obj;

	}

	public void Merge() throws SQLException {// 合并数据库中每一行的元素到最后一列
		String sql = "SELECT * from SearchBooks ";
		ResultSet rs = myDbhelper.executeQuery(sql);
		while (rs.next()) {
			int ID = rs.getInt("ID");
			String bookName = rs.getString("bookName");
			String authorName = rs.getString("authorName");
			String bookNumber = rs.getString("bookNumber");
			String bookType = rs.getString("bookType");
			String size = rs.getString("size");
			String printNumber = rs.getString("printNumber");
			float price = rs.getFloat("price");
			String publishTime = rs.getString("publishTime");
			String articleType = rs.getString("articleType");
			String bjsName = rs.getString("bjsName");
			String editorName = rs.getString("editorName");
			String str = bookName + authorName + bookNumber + bookType + size + printNumber + price + publishTime
					+ articleType + bjsName + editorName;
			String sql1 = "update SearchBooks set [merge]=? where ID=? ";
			Object[] obj1 = new Object[] { str, ID };
			myDbhelper.executeNonQuery(sql1, obj1);
		}
	}

	public List<SearchBooks> FuzzyQuery(String str) throws SQLException {// 模糊查询
		ResultSet rs = null;
		String[] conditions = str.split("\\s+");// 根据空格获得每个查询的字符
		String sql = "SELECT * from SearchBooks WHERE ";
		List<SearchBooks> result = new ArrayList<SearchBooks>();
		Merge();
		int length = conditions.length;// 查询条件的个数
		if (str.equals("")) {// 用户输入的条件为空,显示所有的信息
			String sql1 = "SELECT * from SearchBooks ORDER By bookNumber";
			rs = myDbhelper.executeQuery(sql1);

		} else {// 输入条件字符串不为空，进行查询操作
			for (int i = 0; i < conditions.length; i++) {// 合并查询条件得到sql语句
				if (conditions.length == 1) {// 只有一个查询条件
					sql += "[merge] like '%" + conditions[i] + "%'  ORDER By bookNumber";
					System.out.println(sql);
				} else {// 多于一个查询条件
					if (i == 0) {
						sql += "[merge] like '%" + conditions[i] + "%' AND ";
						System.out.println(sql);
					} else if (i == (conditions.length - 1)) {
						sql += "[merge] like '%" + conditions[i] + "%' ORDER BY bookNumber";
						System.out.println(sql);
					} else {
						sql += "[merge] like '%" + conditions[i] + "%' AND ";
						System.out.println(sql);
					}

				}

			}
			rs = myDbhelper.executeQuery(sql);
		}

		try {
			while (rs.next()) {
				SearchBooks book = new SearchBooks();
				book.setID(rs.getInt("ID"));
				book.setBookName(rs.getString("bookName"));
				book.setAuthorName(rs.getString("authorName"));
				book.setBookNumber(rs.getString("bookNumber"));
				book.setBookType(rs.getString("bookType"));
				book.setSize(rs.getString("Size"));
				book.setPrintNumber(rs.getInt("printNumber"));
				book.setPrice(rs.getFloat("price"));
				book.setPublishTime(rs.getDate("publishTime"));
				book.setArticleType(rs.getString("articleType"));
				book.setBjsName(rs.getString("bjsName"));
				book.setEditorName(rs.getString("editorName"));
				book.setMerge(rs.getString("merge"));
				result.add(book);
			}
			return result;// 返回查询的结果集

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return null;

	}

}
