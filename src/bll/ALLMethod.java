package bll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dal.SQLHelper;
import model.Books_test8;
import model.bookin;
import model.sqlyuju;
import model.zhifang;

public class ALLMethod
{

    public List<Books_test8> getbookinfo(String sql, String par[]) throws ParseException
    {
        List<Books_test8> info = new ArrayList<Books_test8>();
        List<Object[]> objectList = SQLHelper.executeQuery(sql, par);
        for (Object[] object : objectList)
        {
            info.add(new Books_test8(String.valueOf(object[1]), String.valueOf(object[2]), String.valueOf(object[3]),
                    String.valueOf(object[4]), String.valueOf(object[5]), (Integer) object[6],
                    Float.parseFloat(String.valueOf(object[7]))));
        }
        return info;
    }

    public List<sqlyuju> getsql(String username)
    {
        String sql = "select * from sqlyuju where username=?";
        String par[] = new String[]
        { username };
        List<sqlyuju> info = new ArrayList<sqlyuju>();
        List<Object[]> objectList = SQLHelper.executeQuery(sql, par);
        for (Object[] object : objectList)
        {
            info.add(new sqlyuju(String.valueOf(object[1]), String.valueOf(object[2]), String.valueOf(object[3]),
                    String.valueOf(object[4]), String.valueOf(object[5])));
        }
        return info;
    }

    public void insql(String username, String lj, String zd, String ys, String zh)
    {

        String sql1 = "insert into sqlyuju (username,lj,zd,ys,zh) values (?,?,?,?,?)";
        String par1[] = new String[]
        { username, lj, zd, ys, zh };
        SQLHelper.executeUpdate(sql1, par1);
    }

    public void desql(String username, String zd, String ys, String zh)
    {

        String sql1 = "delete from sqlyuju where username=?  and zd=? and ys=? and zh=?";
        String par1[] = new String[]
        { username, zd, ys, zh };
        SQLHelper.executeUpdate(sql1, par1);
    }

    public List<String> getnian()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String sql = "select distinct YEAR(rkrq)  from rk_test8";
        String par[] = new String[] {};
        List<String> info = new ArrayList<String>();
        List<Object[]> objectList = SQLHelper.executeQuery(sql, par);
        for (Object[] object : objectList)
        {
            info.add(new String(String.valueOf(object[0])));
        }
        return info;
    }

    public List<bookin> getbookin(String nian) throws ParseException
    {
        nian = "%" + nian + "%";
        String sql = "select  r.shuh,b.Shum,b.Zuozhe,sum(rkcs),b.Dj\r\n" + " from rk_test8 r,books_test8 b\r\n"
                + "  where r.shuh = b.Shuh and rkrq like ?\r\n" + "  group by r.shuh,b.Shum,b.Zuozhe,b.Dj";
        String par[] = new String[]
        { nian };
        List<bookin> info = new ArrayList<bookin>();
        List<Object[]> objectList = SQLHelper.executeQuery(sql, par);
        for (Object[] object : objectList)
        {
            info.add(new bookin(String.valueOf(object[0]), String.valueOf(object[1]), String.valueOf(object[2]),
                    (Integer) object[3], Float.parseFloat(object[4].toString())));
        }
        return info;
    }

    public List<zhifang> getyuefen(String nian)
    {
        String sql = "  select distinct MONTH(rkrq),sum(rkcs)  from rk_test8 where  rkrq like ?\r\n"
                + "  group by MONTH(rkrq)";
        String par[] = new String[]
        { nian };
        List<zhifang> info = new ArrayList<zhifang>();
        List<Object[]> objectList = SQLHelper.executeQuery(sql, par);
        for (Object[] object : objectList)
        {
            info.add(new zhifang(String.valueOf(object[0]), (Integer) object[1]));
        }
        return info;
    }
}
