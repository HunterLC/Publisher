package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {
	
	public static String getCurrentTime(){  //��ȡ��ǰ��ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEEE");  // MM�����д��HHΪ24Сʱ�ƣ�hhΪ12Сʱ��
		Date date = new Date();
		return sdf.format(date);
	}
	
}
