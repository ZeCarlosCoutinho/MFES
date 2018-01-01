package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Stage {
  public Event event;
  public VDMSeq stage_results;
  public Date date;
  public String name;
  public Number distance;
  public Number number;

  public void cg_init_Stage_1(
      final String n, final Number d, final Number nmbr, final Date dt, final Event evnt) {

    name = n;
    number = nmbr;
    date = dt;
    distance = d;
    stage_results = SeqUtil.seq();
    event = evnt;
  }

  public Stage(final String n, final Number d, final Number nmbr, final Date dt, final Event evnt) {

    cg_init_Stage_1(n, d, nmbr, dt, evnt);
  }

  public void addStageResult(final Car c, final Number s_t) {

    StageResult result = new StageResult(c, s_t);
    stage_results = SeqUtil.conc(Utils.copy(stage_results), SeqUtil.seq(result));
  }

  public void sortResults() {

    SelectionSortResults(1L);
  }

  public StageResult getStageResult(final Number c_number) {

    StageResult ret_result = null;
    for (Iterator iterator_6 = SeqUtil.elems(Utils.copy(stage_results)).iterator();
        iterator_6.hasNext();
        ) {
      StageResult result = (StageResult) iterator_6.next();
      if (Utils.equals(result.car.number, c_number)) {
        return result;
      }
    }
    return ret_result;
  }

  private void SelectionSortResults(final Number i) {

    if (i.longValue() < stage_results.size()) {
      StageResult temp = null;
      Number mi =
          min_index(SeqUtil.subSeq(Utils.copy(stage_results), i, stage_results.size())).longValue()
              + i.longValue()
              - 1L;
      temp = ((StageResult) Utils.get(stage_results, mi));
      Utils.mapSeqUpdate(stage_results, mi, ((StageResult) Utils.get(stage_results, i)));
      Utils.mapSeqUpdate(stage_results, i, temp);
      SelectionSortResults(i.longValue() + 1L);
    }
  }

  private Number min_index(final VDMSeq l) {

    if (Utils.equals(l.size(), 1L)) {
      return 1L;

    } else {
      final Number mi = min_index(SeqUtil.tail(Utils.copy(l)));
      if (((StageResult) Utils.get(l, mi.longValue() + 1L)).stage_time.longValue()
          < ((StageResult) Utils.get(l, 1L)).stage_time.longValue()) {
        return mi.longValue() + 1L;

      } else {
        return 1L;
      }
    }
  }

  public Stage() {}

  public String toString() {

    return "Stage{"
        + "event := "
        + Utils.toString(event)
        + ", stage_results := "
        + Utils.toString(stage_results)
        + ", date := "
        + Utils.toString(date)
        + ", name := "
        + Utils.toString(name)
        + ", distance := "
        + Utils.toString(distance)
        + ", number := "
        + Utils.toString(number)
        + "}";
  }
}
