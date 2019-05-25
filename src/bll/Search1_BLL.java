package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.MyDbHelper;
import model.SearchBooks;

public class Search1_BLL {
	public List<SearchBooks> getAllBook() throws SQLException
	{
		String sql="select * from searchbooks";
		List<SearchBooks> booklist=new ArrayList<SearchBooks>();
		ResultSet rs=MyDbHelper.executeQuery(sql);
		while(rs.next())
		{
			SearchBooks bk=new SearchBooks();
			bk.setID(rs.getInt(1));
			bk.setBookName(rs.getString(2));
			bk.setAuthorName(rs.getString(3));
			bk.setBookNumber(rs.getString(4));
			bk.setBookType(rs.getString(5));
			bk.setSize(rs.getString(6));
			bk.setPrintNumber(rs.getInt(7));
			bk.setPrice(rs.getFloat(8));
			bk.setPublishTime(rs.getDate(9));
			bk.setArticleType(rs.getString(10));
			bk.setBjsName(rs.getString(11));
			bk.setEditorName(rs.getString(12));
			bk.setMerge(rs.getString(13));
			booklist.add(bk);
		}
		return booklist;
	}
	
	/**
	 * 无日期查询
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<SearchBooks> QueryNoDate(List<String> condition) throws SQLException
	{
		String sqlhead="select * from searchbooks where [merge] like ";
		String sql=sqlhead;
		for(int i=0;i<condition.size();i++)
		{
			String s=condition.get(i);
			if(i==condition.size()-1)//最后一个条件后不加and
			{
				sql=sql+"'%"+s+"%'";
			}
			else
			{
				sql=sql+"'%"+s+"%' and [merge] like ";
			}
		}
		List<SearchBooks> booklist=new ArrayList<SearchBooks>();
		ResultSet rs=MyDbHelper.executeQuery(sql);
		while(rs.next())
		{
			SearchBooks bk=new SearchBooks();
			bk.setID(rs.getInt(1));
			bk.setBookName(rs.getString(2));
			bk.setAuthorName(rs.getString(3));
			bk.setBookNumber(rs.getString(4));
			bk.setBookType(rs.getString(5));
			bk.setSize(rs.getString(6));
			bk.setPrintNumber(rs.getInt(7));
			bk.setPrice(rs.getFloat(8));
			bk.setPublishTime(rs.getDate(9));
			bk.setArticleType(rs.getString(10));
			bk.setBjsName(rs.getString(11));
			bk.setEditorName(rs.getString(12));
			bk.setMerge(rs.getString(13));
			booklist.add(bk);
		}
		return booklist;
	}
	
	/**
	 * 有时间无价格范围查询
	 * @param condition
	 * @param start
	 * @param end
	 * @return
	 * @throws SQLException
	 */
	public List<SearchBooks> QueryWithDate(List<String> condition,String start,String end) throws SQLException
	{
		String sql=null;
		if(condition.isEmpty())
		{
			String sqlhead="select * from searchbooks where publishTime>='"+start
					+"' and publishTime<='"+end+"'";
			sql=sqlhead;
		}
		else
		{
			String sqlhead="select * from searchbooks where publishTime>='"+start
							+"' and publishTime<='"+end+"' and [merge] like ";
			sql=sqlhead;
			for(int i=0;i<condition.size();i++)
			{
				String s=condition.get(i);
				if(i==condition.size()-1)//最后一个条件后不加and
				{
					sql=sql+"'%"+s+"%'";
				}
				else
				{
					sql=sql+"'%"+s+"%' and [merge] like ";
				}
			}
		}
		List<SearchBooks> booklist=new ArrayList<SearchBooks>();
		ResultSet rs=MyDbHelper.executeQuery(sql);
		while(rs.next())
		{
			SearchBooks bk=new SearchBooks();
			bk.setID(rs.getInt(1));
			bk.setBookName(rs.getString(2));
			bk.setAuthorName(rs.getString(3));
			bk.setBookNumber(rs.getString(4));
			bk.setBookType(rs.getString(5));
			bk.setSize(rs.getString(6));
			bk.setPrintNumber(rs.getInt(7));
			bk.setPrice(rs.getFloat(8));
			bk.setPublishTime(rs.getDate(9));
			bk.setArticleType(rs.getString(10));
			bk.setBjsName(rs.getString(11));
			bk.setEditorName(rs.getString(12));
			bk.setMerge(rs.getString(13));
			booklist.add(bk);
		}
		return booklist;
	}

	/**
	 * 无价格无时间范围查询
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<SearchBooks> QueryWithNothing(List<String> condition) throws SQLException
	{
		String sqlhead="select * from searchbooks where [merge] like ";
		String sql=sqlhead;
		for(int i=0;i<condition.size();i++)
		{
			String s=condition.get(i);
			
			if(i==condition.size()-1)//最后一个条件后不加and
			{
				sql=sql+"'%"+s+"%'";
			}
			else
			{
				sql=sql+"'%"+s+"%' and [merge] like ";
			}
		}
		List<SearchBooks> booklist=new ArrayList<SearchBooks>();
		ResultSet rs=MyDbHelper.executeQuery(sql);
		while(rs.next())
		{
			SearchBooks bk=new SearchBooks();
			bk.setID(rs.getInt(1));
			bk.setBookName(rs.getString(2));
			bk.setAuthorName(rs.getString(3));
			bk.setBookNumber(rs.getString(4));
			bk.setBookType(rs.getString(5));
			bk.setSize(rs.getString(6));
			bk.setPrintNumber(rs.getInt(7));
			bk.setPrice(rs.getFloat(8));
			bk.setPublishTime(rs.getDate(9));
			bk.setArticleType(rs.getString(10));
			bk.setBjsName(rs.getString(11));
			bk.setEditorName(rs.getString(12));
			bk.setMerge(rs.getString(13));
			booklist.add(bk);
		}
		return booklist;
	}
	
	/**
	 * 有价格无时间范围查询
	 * @param condition
	 * @param minprice
	 * @param maxprice
	 * @return
	 * @throws SQLException
	 */
	public List<SearchBooks> QueryWithPrice(List<String> condition,String minprice,String maxprice) throws SQLException
	{
		String sqlhead=null;
		if(minprice.length()<=0&&maxprice.length()>0)//只有最高价
		{
			sqlhead="select * from searchbooks where price<="+Float.valueOf(maxprice);
		}
		else if(maxprice.length()<=0&&minprice.length()>0)//只有最低价
		{
			sqlhead="select * from searchbooks where price>="+Float.valueOf(minprice);
		}
		else//最高价和最低价都限制
		{
			sqlhead="select * from searchbooks where price>="+Float.valueOf(minprice)+" and price<="+Float.valueOf(maxprice);
		}
		String sql=null;
		if(condition.isEmpty())
		{
			sql=sqlhead;
		}
		else
		{
			sql=sqlhead+" and [merge] like";
			for(int i=0;i<condition.size();i++)
			{
				String s=condition.get(i);
				if(i==condition.size()-1)//最后一个条件后不加and
				{
					sql=sql+"'%"+s+"%'";
				}
				else
				{
					sql=sql+"'%"+s+"%' and [merge] like ";
				}
			}
		}
		List<SearchBooks> booklist=new ArrayList<SearchBooks>();
		ResultSet rs=MyDbHelper.executeQuery(sql);
		while(rs.next())
		{
			SearchBooks bk=new SearchBooks();
			bk.setID(rs.getInt(1));
			bk.setBookName(rs.getString(2));
			bk.setAuthorName(rs.getString(3));
			bk.setBookNumber(rs.getString(4));
			bk.setBookType(rs.getString(5));
			bk.setSize(rs.getString(6));
			bk.setPrintNumber(rs.getInt(7));
			bk.setPrice(rs.getFloat(8));
			bk.setPublishTime(rs.getDate(9));
			bk.setArticleType(rs.getString(10));
			bk.setBjsName(rs.getString(11));
			bk.setEditorName(rs.getString(12));
			bk.setMerge(rs.getString(13));
			booklist.add(bk);
		}
		return booklist;
	}
	
/**
	 * 有时间有价格范围的查询
	 * @param condition
	 * @param start
	 * @param end
	 * @param minprice
	 * @param maxprice
	 * @return
	 * @throws SQLException
	 */
	public List<SearchBooks> QueryDateAndPrice(List<String> condition, String start, String end,
			String minprice, String maxprice) throws SQLException {
		String sqlhead=null;
		String sql=null;
		if(condition.isEmpty())
		{
			sqlhead="select * from searchbooks where publishTime>='"+start
					+"' and publishTime<='"+end+"' ";
			if(minprice.length()<=0&&maxprice.length()>0)//只有最高价
			{
				sqlhead=sqlhead+"and price<="+Float.valueOf(maxprice);
			}
			else if(maxprice.length()<=0&&minprice.length()>0)//只有最低价
			{
				sqlhead=sqlhead+"and price>="+Float.valueOf(minprice);
			}
			else//最高价和最低价都限制
			{
				sqlhead=sqlhead+"and price>="+Float.valueOf(minprice)+" and price<="+Float.valueOf(maxprice);
			}
			sql=sqlhead;
		}
		else
		{
			sqlhead="select * from searchbooks where publishTime>='"+start
					+"' and publishTime<='"+end+"' ";
			if(minprice.length()<=0&&maxprice.length()>0)//只有最高价
			{
				sqlhead=sqlhead+"and price<="+Float.valueOf(maxprice)+" and [merge] like";
			}
			else if(maxprice.length()<=0&&minprice.length()>0)//只有最低价
			{
				sqlhead=sqlhead+"and price>="+Float.valueOf(minprice)+" and [merge] like";
			}
			else//最高价和最低价都限制
			{
				sqlhead=sqlhead+"and price>="+Float.valueOf(minprice)+" and price<="+Float.valueOf(maxprice)+" and [merge] like";
			}
			sql=sqlhead;
			for(int i=0;i<condition.size();i++)
			{
				String s=condition.get(i);
				if(i==condition.size()-1)//最后一个条件后不加and
				{
					sql=sql+"'%"+s+"%'";
				}
				else
				{
					sql=sql+"'%"+s+"%' and [merge] like ";
				}
			}
		}
		List<SearchBooks> booklist=new ArrayList<SearchBooks>();
		ResultSet rs=MyDbHelper.executeQuery(sql);
		while(rs.next())
		{
			SearchBooks bk=new SearchBooks();
			bk.setID(rs.getInt(1));
			bk.setBookName(rs.getString(2));
			bk.setAuthorName(rs.getString(3));
			bk.setBookNumber(rs.getString(4));
			bk.setBookType(rs.getString(5));
			bk.setSize(rs.getString(6));
			bk.setPrintNumber(rs.getInt(7));
			bk.setPrice(rs.getFloat(8));
			bk.setPublishTime(rs.getDate(9));
			bk.setArticleType(rs.getString(10));
			bk.setBjsName(rs.getString(11));
			bk.setEditorName(rs.getString(12));
			bk.setMerge(rs.getString(13));
			booklist.add(bk);
		}
		return booklist;
	}



}
