package fi.rofl.HomeChore.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Calendar;

public class DateUtils {

	public static Timestamp addDaysToTimestamp(Timestamp ts, BigDecimal days) {

		BigDecimal integerPart = days.setScale(0, RoundingMode.DOWN);
		BigDecimal fractionPart = days.subtract(integerPart);
		BigDecimal hours = Constants.DAILY_HOURS_IN_BD.multiply(fractionPart).setScale(0, RoundingMode.DOWN);

		Calendar cal = Calendar.getInstance();
		cal.setTime(ts);
		cal.add(Calendar.DAY_OF_WEEK, integerPart.intValue());
		cal.add(Calendar.HOUR, hours.intValue());
		ts.setTime(cal.getTime().getTime()); // or
		ts = new Timestamp(cal.getTime().getTime());

		return ts;
	}
	
	public static Timestamp subtractDaysfromTimestamp(Timestamp ts, BigDecimal days) {

		BigDecimal integerPart = days.setScale(0, RoundingMode.DOWN);
		BigDecimal fractionPart = days.subtract(integerPart);
		BigDecimal hours = Constants.DAILY_HOURS_IN_BD.multiply(fractionPart).setScale(0, RoundingMode.DOWN);

		Calendar cal = Calendar.getInstance();
		cal.setTime(ts);
		cal.add(Calendar.DAY_OF_WEEK, integerPart.negate().intValue());
		cal.add(Calendar.HOUR, hours.negate().intValue());
		ts.setTime(cal.getTime().getTime()); // or
		ts = new Timestamp(cal.getTime().getTime());

		return ts;
	}

	public static Timestamp getNowAsTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}

}
