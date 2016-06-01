package statPerson;

import java.sql.Date;
import java.util.Calendar;

public class Utils {
	
	final public static Date getCurrentTime(){
		return new Date(Calendar.getInstance().getTime().getTime());
	}
	
}
