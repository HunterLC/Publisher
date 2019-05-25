package model;

public class sqlyuju {
	private String username;
    private String lj;
    private String zd;
    private String ys;

    public sqlyuju(String username, String lj, String zd, String ys, String zh)
    {
        super();
        this.username = username;
        this.lj = lj;
        this.zd = zd;
        this.ys = ys;
        this.zh = zh;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getLj()
    {
        return lj;
    }

    public void setLj(String lj)
    {
        this.lj = lj;
    }

    public String getZd()
    {
        return zd;
    }

    public void setZd(String zd)
    {
        this.zd = zd;
    }

    public String getYs()
    {
        return ys;
    }

    public void setYs(String ys)
    {
        this.ys = ys;
    }

    public String getZh()
    {
        return zh;
    }

    public void setZh(String zh)
    {
        this.zh = zh;
    }

    private String zh;
}
