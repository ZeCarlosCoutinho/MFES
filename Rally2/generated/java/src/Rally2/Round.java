package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Round {
  public Car car;
  public Date date;
  public Number round_time;

  public void cg_init_Round_1(final Number r_t, final Date dt, final Car cr) {

    car = cr;
    date = dt;
    round_time = r_t;
    return;
  }

  public Round(final Number r_t, final Date dt, final Car cr) {

    cg_init_Round_1(r_t, dt, cr);
  }

  public Boolean isequal(final Round rnd) {

    Boolean andResult_16 = false;

    if (Utils.equals(this.car, rnd.car)) {
      Boolean andResult_17 = false;

      if (this.date.isequal(rnd.date)) {
        if (Utils.equals(this.round_time, rnd.round_time)) {
          andResult_17 = true;
        }
      }

      if (andResult_17) {
        andResult_16 = true;
      }
    }

    if (andResult_16) {
      return true;

    } else {
      return false;
    }
  }

  public Round() {}

  public String toString() {

    return "Round{"
        + "car := "
        + Utils.toString(car)
        + ", date := "
        + Utils.toString(date)
        + ", round_time := "
        + Utils.toString(round_time)
        + "}";
  }
}
