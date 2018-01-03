package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;
import junit.framework.TestCase;

@SuppressWarnings("all")
public class DateTest extends TestCase {
	
	public DateTest(String fnName) {
        super(fnName);
    }
	
  public void testIsEqual() {

    Number year = 2007L;
    Number month = 11L;
    Number day = 21L;
    Number hours = 17L;
    Number minutes = 40L;
    Number seconds = 30L;
    Date date1 = new Date(year, month, day, hours, minutes, seconds);
    Date date2 = new Date(year, month, day, hours, minutes, seconds);
    Date date3 = new Date(year, month, day, hours, 0L, 0L);
    assertTrue(date1.isequal(date2));
    assertFalse(date1.isequal(date3));
  }

  public void testIsBefore() {

    Date date1 = new Date(2011L, 11L, 11L, 11L, 11L, 11L);
    Date date2 = new Date(2012L, 12L, 12L, 12L, 12L, 12L);
    Date date3 = new Date(2012L, 12L, 12L, 12L, 10L, 12L);
    Date date10 = new Date(2010L, 10L, 10L, 10L, 10L, 10L);
    Date date11 = new Date(2010L, 10L, 10L, 10L, 10L, 11L);
    Date date12 = new Date(2010L, 10L, 10L, 10L, 11L, 10L);
    Date date13 = new Date(2010L, 10L, 10L, 11L, 10L, 10L);
    Date date14 = new Date(2010L, 10L, 11L, 10L, 10L, 10L);
    Date date15 = new Date(2010L, 11L, 10L, 10L, 10L, 10L);
    Date date16 = new Date(2011L, 10L, 10L, 10L, 10L, 10L);
    assertTrue(date1.isbefore(date2));
    assertFalse(date2.isbefore(date1));
    assertFalse(date2.isbefore(date1));
    assertTrue(date3.isbefore(date2));
    assertTrue(date1.isbefore(date3));
    assertFalse(date3.isbefore(date1));
    assertTrue(date10.isbefore(date11));
    assertTrue(date10.isbefore(date12));
    assertTrue(date10.isbefore(date13));
    assertTrue(date10.isbefore(date14));
    assertTrue(date10.isbefore(date15));
    assertTrue(date10.isbefore(date16));
    assertFalse(date11.isbefore(date10));
    assertFalse(date12.isbefore(date10));
    assertFalse(date13.isbefore(date10));
    assertFalse(date14.isbefore(date10));
    assertFalse(date15.isbefore(date10));
    assertFalse(date16.isbefore(date10));
  }

  public DateTest() {}

  public String toString() {

    return "DateTest{}";
  }
}
