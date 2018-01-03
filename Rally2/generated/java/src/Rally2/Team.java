package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Team {
  public VDMSet team_members;
  public VDMSet cars;
  public Manager manager;
  public VDMSet sponsors;
  public VDMSet events;
  public String name;

  public void cg_init_Team_1(final String nm, final Manager mngr) {

    name = nm;
    events = SetUtil.set();
    sponsors = SetUtil.set();
    cars = SetUtil.set();
    team_members = SetUtil.set(mngr);
    manager = mngr;
  }

  public Team(final String nm, final Manager mngr) {

    cg_init_Team_1(nm, mngr);
  }

  public void addDriver(final String nm, final String nati) {

    Driver driver = new Driver(nm, nati);
    team_members = SetUtil.union(Utils.copy(team_members), SetUtil.set(driver));
  }

  public void addCoDriver(final String nm, final String nati) {

    CoDriver co_driver = new CoDriver(nm, nati);
    team_members = SetUtil.union(Utils.copy(team_members), SetUtil.set(co_driver));
  }

  public void addMechanic(final String nm, final String nati) {

    Mechanic mechanic = new Mechanic(nm, nati);
    team_members = SetUtil.union(Utils.copy(team_members), SetUtil.set(mechanic));
  }

  public void addCar(
      final String make,
      final String model,
      final Number year,
      final Number bhp,
      final String traction,
      final Number weight,
      final Number number) {

    Car car = new Car(make, model, year, bhp, traction, weight, this, number);
    cars = SetUtil.union(Utils.copy(cars), SetUtil.set(car));
  }

  public void assignDriver(final Car car, final Driver driver) {

    car.setDriver(driver);
  }

  public void assignCoDriver(final Car car, final CoDriver co_driver) {

    car.setCoDriver(co_driver);
  }

  public void assignMechanic(final Car car, final Mechanic mechanic) {

    car.addMechanic(mechanic);
  }

  public VDMSeq getEventResults() {

    VDMSeq ret_seq = SeqUtil.seq();
    for (Iterator iterator_7 = events.iterator(); iterator_7.hasNext(); ) {
      Event event = (Event) iterator_7.next();
      for (Iterator iterator_8 = cars.iterator(); iterator_8.hasNext(); ) {
        Car car = (Car) iterator_8.next();
        if (event instanceof SpecialStage) {
          SpecialStage ss = (SpecialStage) event;
          CarResult res = null;
          Number pos = 0L;
          long toVar_5 = ss.getResults().stage_results.size();

          for (Long n = 1L; n <= toVar_5; n++) {
            if (Utils.equals(
                ((StageResult) Utils.get(ss.getResults().stage_results, n)).car, car)) {
              pos = n;
            }
          }
          res = new CarResult(car, event, pos);
          ret_seq = SeqUtil.conc(Utils.copy(ret_seq), SeqUtil.seq(res));
        }

        if (event instanceof AtoB) {
          AtoB atob = (AtoB) event;
          CarResult res = null;
          Number pos = 0L;
          long toVar_6 = atob.getFinalResults().size();

          for (Long n = 1L; n <= toVar_6; n++) {
            if (Utils.equals(((Round) Utils.get(atob.getFinalResults(), n)).car, car)) {
              pos = n;
            }
          }
          res = new CarResult(car, event, pos);
          ret_seq = SeqUtil.conc(Utils.copy(ret_seq), SeqUtil.seq(res));
        }
      }
    }
    return Utils.copy(ret_seq);
  }

  public Car getCar(final Number n) {

    Car ret_car = null;
    for (Iterator iterator_9 = cars.iterator(); iterator_9.hasNext(); ) {
      Car car = (Car) iterator_9.next();
      if (Utils.equals(car.number, n)) {
        return car;
      }
    }
    return ret_car;
  }

  public TeamMember getMember(final String nm) {

    TeamMember ret_member = null;
    for (Iterator iterator_10 = team_members.iterator(); iterator_10.hasNext(); ) {
      TeamMember member = (TeamMember) iterator_10.next();
      if (Utils.equals(member.name, nm)) {
        return member;
      }
    }
    return ret_member;
  }

  public void addSponsor(final Sponsor spsr) {

    sponsors = SetUtil.union(Utils.copy(sponsors), SetUtil.set(spsr));
  }

  public void addEvent(final Event event) {

    events = SetUtil.union(Utils.copy(events), SetUtil.set(event));
  }

  public Team() {}

  public String toString() {

    return "Team{"
        + "team_members := "
        + Utils.toString(team_members)
        + ", cars := "
        + Utils.toString(cars)
        + ", manager := "
        + Utils.toString(manager)
        + ", sponsors := "
        + Utils.toString(sponsors)
        + ", events := "
        + Utils.toString(events)
        + ", name := "
        + Utils.toString(name)
        + "}";
  }
}
