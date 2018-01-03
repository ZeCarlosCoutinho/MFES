package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class CarResult {
  public Event event;
  public Car car;
  public Number position;

  public void cg_init_CarResult_1(final Car cr, final Event evnt, final Number pos) {

    event = evnt;
    car = cr;
    position = pos;
  }

  public CarResult(final Car cr, final Event evnt, final Number pos) {

    cg_init_CarResult_1(cr, evnt, pos);
  }

  public CarResult() {}

  public String toString() {

    return "CarResult{"
        + "event := "
        + Utils.toString(event)
        + ", car := "
        + Utils.toString(car)
        + ", position := "
        + Utils.toString(position)
        + "}";
  }
}
