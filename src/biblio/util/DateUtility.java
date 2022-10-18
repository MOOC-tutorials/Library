package biblio.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import biblio.BiblioSystem;

/**
 * Utility class for operations related to dates
 * 
 */
public abstract class DateUtility {

	public final static long MILLISECS_PER_DAY = 86400000; //1000 milliseconds *60 seconds *60 minutes *24 hours
	
	public static Date trunc(Date date) {
		String s = BiblioSystem.DATE_FORMAT.format(date);
		try {
			return BiblioSystem.DATE_FORMAT.parse(s);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Avance une date avec un nombre de jours donn�.
	 * 
	 * @return la nouvelle date.
	 * 
	 *  (M�thode auxiliaire.)
	 */
	public static Date addToDate(Date date, int jours) {
		GregorianCalendar cal = new GregorianCalendar();
		
		cal.setTime(date);
		cal.add(Calendar.DATE, jours);
		return cal.getTime();
	}
	
	public static int differenceInDays(Date start, Date end) {
		int days = 0;
		GregorianCalendar calStart = new GregorianCalendar();
		calStart.setTime(start);
		GregorianCalendar calEnd = new GregorianCalendar();
		calEnd.setTime(end);
		days = (int) ( ( calEnd.getTimeInMillis() - calStart.getTimeInMillis() ) / MILLISECS_PER_DAY );
		return days;
	}

}
