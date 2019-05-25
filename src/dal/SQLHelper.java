package dal;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class SQLHelper
{

    // 定义变量
    private static Connection ct = null;
    // 大多数情况下用preparedstatement替代statement
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    // 连接数据库的参数
    private static String url = null;
    private static String user = null;
    private static String driver = null;
    private static String password = null;

    private static CallableStatement cs = null;

    public static CallableStatement getCs()
    {
        return cs;
    }

    private static Properties pp = null;
    private static InputStream fis = null;
    // 加载驱动，只需要一次，用静态代码块
    static
    {
        try
        {
            // 从dbinfo.properties
            pp = new Properties();
            fis = SQLHelper.class.getClassLoader().getResourceAsStream("config.properties");
            pp.load(fis);
            url = pp.getProperty("url");
            driver = pp.getProperty("driver");
            user = pp.getProperty("user");
            password = pp.getProperty("password");
            Class.forName(driver);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                fis.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            fis = null;// 垃圾回收站上收拾
        }

    }

    // 得到连接
    public static Connection getConnection()
    {
        try
        {
            ct = DriverManager.getConnection(url, user, password);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return ct;
    }

    public static Connection getCt()
    {
        return ct;
    }

    public static PreparedStatement getPs()
    {
        return ps;
    }

    public static ResultSet getRs()
    {
        return rs;
    }

    public static ArrayList<Object[]> executeQuery(String sql, String[] parameters)
    {
        ArrayList<Object[]> list = null;
        try
        {
            ct = getConnection();
            ps = ct.prepareStatement(sql);
            if (parameters != null)
            {
                for (int i = 0; i < parameters.length; i++)
                {
                    ps.setString(i + 1, parameters[i]);
                }
            }
            rs = ps.executeQuery();

            // 得到结果集（rs）的结构
            ResultSetMetaData rsmd = rs.getMetaData();

//        if(rs.getRow()!=0)
            list = new ArrayList<Object[]>();

            // 通过rsmd可以得到该结果集有多少列
            int columnNum = rsmd.getColumnCount();

            // 从rs中取出数据，并且封装到ArrayList中
            while (rs.next())
            {

                Object[] objects = new Object[columnNum];
                for (int i = 0; i < objects.length; i++)
                {
                    objects[i] = rs.getObject(i + 1);
                }

                list.add(objects);
            }
            return list;

        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally
        {
            close(rs, ps, ct);
        }
    }

    // update
    // sql格式：update 表名 set 字段名 =？where 字段=？
    // parameter（”abc“,23）
    public static void executeUpdate(String sql, String[] parameters)
    {
        try
        {
            ct = getConnection();
            ps = ct.prepareStatement(sql);
            if (parameters != null)
            {
                for (int i = 0; i < parameters.length; i++)
                {
                    ps.setString(i + 1, parameters[i]);
                }

            }
            ps.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();// 开发阶段
            // 抛出异常
            // 可以处理，也可以不处理
            throw new RuntimeException(e.getMessage());
        } finally
        {
            close(rs, ps, ct);
        }
    }

    public static void close(ResultSet rs, Statement ps, Connection ct)
    {
        // 关闭资源(先开后关)
        if (rs != null)
        {
            try
            {
                rs.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
            rs = null;
        }
        if (ps != null)
        {
            try
            {
                ps.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
            ps = null;
        }
        if (null != ct)
        {
            try
            {
                ct.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
            ct = null;
        }
    }

}
