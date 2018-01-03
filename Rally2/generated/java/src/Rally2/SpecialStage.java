package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SpecialStage extends Event {
  public VDMSet stages;

  public void cg_init_SpecialStage_1(
      final String nm, final String loc, final Date bg_date, final Date e_date) {

    name = nm;
    location = loc;
    beginning_date = bg_date;
    end_date = e_date;
    stages = SetUtil.set();
    teams = SetUtil.set();
  }

  public void addStage(
      final String nm, final Number number, final Number distance, final Date date) {

    Stage stage = new Stage(nm, distance, number, date, this);
    stages = SetUtil.union(Utils.copy(stages), SetUtil.set(stage));
  }

  public Stage getResults() {

    Stage results = new Stage();
    results.stage_results = SeqUtil.seq();
    for (Iterator iterator_2 = teams.iterator(); iterator_2.hasNext(); ) {
      Team team = (Team) iterator_2.next();
      for (Iterator iterator_3 = team.cars.iterator(); iterator_3.hasNext(); ) {
        Car car = (Car) iterator_3.next();
        StageResult stage_result = new StageResult(car, 0L);
        results.stage_results = SeqUtil.conc(results.stage_results, SeqUtil.seq(stage_result));
      }
    }
    for (Iterator iterator_4 = stages.iterator(); iterator_4.hasNext(); ) {
      Stage stage = (Stage) iterator_4.next();
      long toVar_4 = stage.stage_results.size();

      for (Long n = 1L; n <= toVar_4; n++) {
        StageResult res =
            results.getStageResult(((StageResult) Utils.get(stage.stage_results, n)).car.number);
        res.stage_time =
            res.stage_time.longValue()
                + ((StageResult) Utils.get(stage.stage_results, n)).stage_time.longValue();
      }
    }
    results.sortResults();
    return results;
  }

  public SpecialStage(final String nm, final String loc, final Date bg_date, final Date e_date) {

    cg_init_SpecialStage_1(nm, loc, bg_date, e_date);
  }

  public Stage getStage(final Number n) {

    Stage ret_stage = null;
    for (Iterator iterator_5 = stages.iterator(); iterator_5.hasNext(); ) {
      Stage stage = (Stage) iterator_5.next();
      if (Utils.equals(stage.number, n)) {
        return stage;
      }
    }
    return ret_stage;
  }

  public SpecialStage() {}

  public String toString() {

    return "SpecialStage{" + "stages := " + Utils.toString(stages) + "}";
  }
}
