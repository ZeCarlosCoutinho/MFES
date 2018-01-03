package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Event {
  public VDMSet teams;
  public Date beginning_date;
  public Date end_date;
  public String name;
  public String location;

  public void addTeam(final Team team) {

    teams = SetUtil.union(Utils.copy(teams), SetUtil.set(team));
    team.addEvent(this);
  }

  public Event() {}

  public String toString() {

    return "Event{"
        + "teams := "
        + Utils.toString(teams)
        + ", beginning_date := "
        + Utils.toString(beginning_date)
        + ", end_date := "
        + Utils.toString(end_date)
        + ", name := "
        + Utils.toString(name)
        + ", location := "
        + Utils.toString(location)
        + "}";
  }
}
