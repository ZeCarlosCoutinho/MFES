package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Date {
  private CalendarDate calendardate = new CalendarDate(0L, 1L, 1L);
  private TimeStamp timestamp = new TimeStamp(0L, 0L, 0L);

  public void cg_init_Date_1(
      final Number yr,
      final Number mnth,
      final Number d,
      final Number hrs,
      final Number mnts,
      final Number scnds) {

    calendardate.year = yr;
    calendardate.month = mnth;
    calendardate.day = d;
    timestamp.hours = hrs;
    timestamp.minutes = mnts;
    timestamp.seconds = scnds;
  }

  public Date(
      final Number yr,
      final Number mnth,
      final Number d,
      final Number hrs,
      final Number mnts,
      final Number scnds) {

    cg_init_Date_1(yr, mnth, d, hrs, mnts, scnds);
  }

  public Boolean isequal(final Date dt) {

    Boolean andResult_5 = false;

    if (Utils.equals(this.calendardate.year, dt.calendardate.year)) {
      Boolean andResult_6 = false;

      if (Utils.equals(this.calendardate.month, dt.calendardate.month)) {
        Boolean andResult_7 = false;

        if (Utils.equals(this.calendardate.day, dt.calendardate.day)) {
          Boolean andResult_8 = false;

          if (Utils.equals(this.timestamp.hours, dt.timestamp.hours)) {
            Boolean andResult_9 = false;

            if (Utils.equals(this.timestamp.minutes, dt.timestamp.minutes)) {
              if (Utils.equals(this.timestamp.seconds, dt.timestamp.seconds)) {
                andResult_9 = true;
              }
            }

            if (andResult_9) {
              andResult_8 = true;
            }
          }

          if (andResult_8) {
            andResult_7 = true;
          }
        }

        if (andResult_7) {
          andResult_6 = true;
        }
      }

      if (andResult_6) {
        andResult_5 = true;
      }
    }

    if (andResult_5) {
      return true;

    } else {
      return false;
    }
  }

  public Boolean isbefore(final Date dt) {

    if (this.calendardate.year.longValue() < dt.calendardate.year.longValue()) {
      return true;

    } else {
      if (this.calendardate.year.longValue() > dt.calendardate.year.longValue()) {
        return false;
      }
    }

    if (this.calendardate.month.longValue() < dt.calendardate.month.longValue()) {
      return true;

    } else {
      if (this.calendardate.month.longValue() > dt.calendardate.month.longValue()) {
        return false;
      }
    }

    if (this.calendardate.day.longValue() < dt.calendardate.day.longValue()) {
      return true;

    } else {
      if (this.calendardate.day.longValue() > dt.calendardate.day.longValue()) {
        return false;
      }
    }

    if (this.timestamp.hours.longValue() < dt.timestamp.hours.longValue()) {
      return true;

    } else {
      if (this.timestamp.hours.longValue() > dt.timestamp.hours.longValue()) {
        return false;
      }
    }

    if (this.timestamp.minutes.longValue() < dt.timestamp.minutes.longValue()) {
      return true;

    } else {
      if (this.timestamp.minutes.longValue() > dt.timestamp.minutes.longValue()) {
        return false;
      }
    }

    if (this.timestamp.seconds.longValue() < dt.timestamp.seconds.longValue()) {
      return true;

    } else {
      if (this.timestamp.seconds.longValue() > dt.timestamp.seconds.longValue()) {
        return false;
      }
    }

    return false;
  }

  public Date() {}

  public String toString() {

    return "Date{"
        + "calendardate := "
        + Utils.toString(calendardate)
        + ", timestamp := "
        + Utils.toString(timestamp)
        + "}";
  }

  public static class CalendarDate implements Record {
    public Number year;
    public Number month;
    public Number day;

    public CalendarDate(final Number _year, final Number _month, final Number _day) {

      year = _year;
      month = _month;
      day = _day;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof CalendarDate)) {
        return false;
      }

      CalendarDate other = ((CalendarDate) obj);

      return (Utils.equals(year, other.year))
          && (Utils.equals(month, other.month))
          && (Utils.equals(day, other.day));
    }

    public int hashCode() {

      return Utils.hashCode(year, month, day);
    }

    public CalendarDate copy() {

      return new CalendarDate(year, month, day);
    }

    public String toString() {

      return "mk_Date`CalendarDate" + Utils.formatFields(year, month, day);
    }
  }

  public static Boolean inv_CalendarDate(final CalendarDate cd) {

    Boolean andResult_10 = false;

    if (cd.month.longValue() <= 12L) {
      if (cd.day.longValue() <= 31L) {
        andResult_10 = true;
      }
    }

    return andResult_10;
  }

  public static class TimeStamp implements Record {
    public Number hours;
    public Number minutes;
    public Number seconds;

    public TimeStamp(final Number _hours, final Number _minutes, final Number _seconds) {

      hours = _hours;
      minutes = _minutes;
      seconds = _seconds;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof TimeStamp)) {
        return false;
      }

      TimeStamp other = ((TimeStamp) obj);

      return (Utils.equals(hours, other.hours))
          && (Utils.equals(minutes, other.minutes))
          && (Utils.equals(seconds, other.seconds));
    }

    public int hashCode() {

      return Utils.hashCode(hours, minutes, seconds);
    }

    public TimeStamp copy() {

      return new TimeStamp(hours, minutes, seconds);
    }

    public String toString() {

      return "mk_Date`TimeStamp" + Utils.formatFields(hours, minutes, seconds);
    }
  }

  public static Boolean inv_TimeStamp(final TimeStamp ts) {

    Boolean andResult_12 = false;

    if (ts.hours.longValue() <= 23L) {
      Boolean andResult_13 = false;

      if (ts.minutes.longValue() <= 59L) {
        if (ts.seconds.longValue() <= 59L) {
          andResult_13 = true;
        }
      }

      if (andResult_13) {
        andResult_12 = true;
      }
    }

    return andResult_12;
  }
}
