package fi.rofl.HomeChore.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.Test;

import fi.rofl.HomeChore.util.DateUtils;

public class DateUtilsTest {
	
	public static final BigDecimal ONE = new BigDecimal(1.0);
	
	public static final BigDecimal ONE_AND_HALF = new BigDecimal(1.5);
	
	public static final BigDecimal HALF = new BigDecimal(0.5);

	@Test
	public void testAdding() {
		Timestamp originalTs = new Timestamp(System.currentTimeMillis());
		String originalDate = originalTs.toString();
		System.out.println("originalDate: " + originalDate);
		
		String dayPlus1 = DateUtils.addDaysToTimestamp(originalTs, ONE).toString();
		System.out.println("dayPlus1: " + dayPlus1);
		
		
		System.out.println("dayPlus1: " + dayPlus1);
		String dayMinus1 = DateUtils.subtractDaysfromTimestamp(originalTs, ONE).toString();
		System.out.println("dayMinus1: " + dayMinus1);
		assertEquals("plus one minus one is the same", originalDate,  dayMinus1);
		
		String dayMinusHalf = DateUtils.subtractDaysfromTimestamp(originalTs, ONE_AND_HALF).toString();
		System.out.println("dayMinusHalf: " + dayMinusHalf);
		assertEquals("plus one minus one is the same", originalDate,  dayMinus1);
		
		String dayPlusHalf = DateUtils.addDaysToTimestamp(originalTs, ONE_AND_HALF).toString();
		System.out.println("dayPlusHalf: " + dayPlusHalf);
		assertEquals("plus one minus one is the same", originalDate,  dayPlusHalf);
	}

}
