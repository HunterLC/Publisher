package model;

import java.text.DecimalFormat;

public class Books_test8 {
	private String Shuh;
    private String Shum;
    private String Zuozhe;
    private String Tsfl;
    private String Kb;
    private int Cs;
    private float Dj;
    
    public Object get(int i)
    {
        switch (i)
        {
            case 0:
                return Shuh;
            case 1:
                return Shum;
            case 2:
                return Zuozhe;
            case 3:
                return Tsfl;
            case 4:
                return Kb;
            case 5:
                return Cs;
            case 6:
                return new DecimalFormat("##,##0.00").format(Dj);
            case 7:
                return new DecimalFormat("##,##0.00").format(Cs * Dj);
            default:
                break;
        }
        return null;
    }

    public Books_test8(String Shuh, String Shum, String Zuozhe, String Tsfl, String Kb, int Cs, float Dj)
    {
        super();
        this.Shuh = Shuh;
        this.Shum = Shum;
        this.Zuozhe = Zuozhe;
        this.Tsfl = Tsfl;
        this.Kb = Kb;
        this.Cs = Cs;
        this.Dj = Dj;
    }
    
	public String getShuh() {
		return Shuh;
	}
	public void setShuh(String shuh) {
		Shuh = shuh;
	}
	public String getShum() {
		return Shum;
	}
	public void setShum(String shum) {
		Shum = shum;
	}
	public String getZuozhe() {
		return Zuozhe;
	}
	public void setZuozhe(String zuozhe) {
		Zuozhe = zuozhe;
	}
	public String getTsfl() {
		return Tsfl;
	}
	public void setTsfl(String tsfl) {
		Tsfl = tsfl;
	}
	public String getKb() {
		return Kb;
	}
	public void setKb(String kb) {
		Kb = kb;
	}
	public int getCs() {
		return Cs;
	}
	public void setCs(int cs) {
		Cs = cs;
	}
	public float getDj() {
		return Dj;
	}
	public void setDj(float dj) {
		this.Dj = dj;
	}
}
