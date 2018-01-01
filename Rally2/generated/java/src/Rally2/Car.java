package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Car {
  public Driver driver;
  public CoDriver co_driver;
  public VDMSet mechanics;
  public Team team;
  public String make;
  public String model;
  public Number year;
  public Number bhp;
  public String traction;
  public Number weight;
  public Number number;

  public void cg_init_Car_1(
      final String mk,
      final String mdl,
      final Number yr,
      final Number hp,
      final String trc,
      final Number wght,
      final Team tm,
      final Number nmb) {

    make = mk;
    model = mdl;
    year = yr;
    bhp = hp;
    traction = trc;
    weight = wght;
    team = tm;
    number = nmb;
    mechanics = SetUtil.set();
    return;
  }

  public void setDriver(final Driver drvr) {

    driver = drvr;
  }

  public void setCoDriver(final CoDriver co_drvr) {

    co_driver = co_drvr;
  }

  public void addMechanic(final Mechanic mechanic) {

    mechanics = SetUtil.union(Utils.copy(mechanics), SetUtil.set(mechanic));
  }

  public Car(
      final String mk,
      final String mdl,
      final Number yr,
      final Number hp,
      final String trc,
      final Number wght,
      final Team tm,
      final Number nmb) {

    cg_init_Car_1(mk, mdl, yr, hp, trc, wght, tm, nmb);
  }

  public Car() {}

  public String toString() {

    return "Car{"
        + "driver := "
        + Utils.toString(driver)
        + ", co_driver := "
        + Utils.toString(co_driver)
        + ", mechanics := "
        + Utils.toString(mechanics)
        + ", team := "
        + Utils.toString(team)
        + ", make := "
        + Utils.toString(make)
        + ", model := "
        + Utils.toString(model)
        + ", year := "
        + Utils.toString(year)
        + ", bhp := "
        + Utils.toString(bhp)
        + ", traction := "
        + Utils.toString(traction)
        + ", weight := "
        + Utils.toString(weight)
        + ", number := "
        + Utils.toString(number)
        + "}";
  }
}
