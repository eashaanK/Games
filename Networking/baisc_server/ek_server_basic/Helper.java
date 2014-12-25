package ek_server_basic;

import java.sql.Timestamp;
import java.util.Date;

public class Helper {

	public static String getTimeStamp(){
		Date date = new Date();
		return new Timestamp(date.getTime()).toString();
	}
}
