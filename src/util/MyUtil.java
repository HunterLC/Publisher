package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {
	
	public static String getCurrentTime(){  //获取当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEEE");  // MM必须大写，HH为24小时制，hh为12小时制
		Date date = new Date();
		return sdf.format(date);
	}
	
}
