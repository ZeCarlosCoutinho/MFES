package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class AtoB extends Event {
  public VDMSeq round;

  public void cg_init_AtoB_1(
      final String nm, final String loc, final Date beginning_d, final Date end_d) {

    teams = SetUtil.set();
    beginning_date = beginning_d;
    end_date = end_d;
    name = nm;
    location = loc;
    round = SeqUtil.seq();
    return;
  }

  public void addRound(final Car car, final Number round_time, final Date date) {

    Round newRound = new Round(round_time, date, car);
    round = SeqUtil.conc(Utils.copy(round), SeqUtil.seq(newRound));
  }

  public VDMSeq getCarRounds(final Car car) {

    VDMSeq car_rounds = SeqUtil.seq();
    long toVar_1 = this.round.size();

    for (Long n = 1L; n <= toVar_1; n++) {
      if (Utils.equals(((Round) Utils.get(this.round, n)).car, car)) {
        car_rounds =
            SeqUtil.conc(Utils.copy(car_rounds), SeqUtil.seq(((Round) Utils.get(this.round, n))));
      }
    }
    return Utils.copy(car_rounds);
  }

  public void sortRounds() {

    SelectionSortResults(1L);
  }

  public VDMSeq getFinalResults() {

    VDMSeq result = SeqUtil.seq();
    Boolean not_in_results = true;
    sortRounds();
    long toVar_3 = round.size();

    for (Long n = 1L; n <= toVar_3; n++) {
      not_in_results = true;
      long toVar_2 = result.size();

      for (Long m = 1L; m <= toVar_2; m++) {
        if (Utils.equals(((Round) Utils.get(result, m)).car, ((Round) Utils.get(round, n)).car)) {
          not_in_results = false;
        }
      }
      if (not_in_results) {
        result = SeqUtil.conc(Utils.copy(result), SeqUtil.seq(((Round) Utils.get(round, n))));
      }
    }
    return Utils.copy(result);
  }

  public AtoB(final String nm, final String loc, final Date beginning_d, final Date end_d) {

    cg_init_AtoB_1(nm, loc, beginning_d, end_d);
  }

  public Round getRound(final Car car, final Date date) {

    for (Iterator iterator_1 = SeqUtil.elems(Utils.copy(round)).iterator();
        iterator_1.hasNext();
        ) {
      Round rnd = (Round) iterator_1.next();
      Boolean andResult_4 = false;

      if (Utils.equals(rnd.car, car)) {
        if (Utils.equals(rnd.date, date)) {
          andResult_4 = true;
        }
      }

      if (andResult_4) {
        return rnd;
      }
    }
    return null;
  }

  private void SelectionSortResults(final Number i) {

    if (i.longValue() < round.size()) {
      Round temp = null;
      Number mi =
          min_index(SeqUtil.subSeq(Utils.copy(round), i, round.size())).longValue()
              + i.longValue()
              - 1L;
      temp = ((Round) Utils.get(round, mi));
      Utils.mapSeqUpdate(round, mi, ((Round) Utils.get(round, i)));
      Utils.mapSeqUpdate(round, i, temp);
      SelectionSortResults(i.longValue() + 1L);
    }
  }

  public AtoB() {}

  private static Number min_index(final VDMSeq l) {

    if (Utils.equals(l.size(), 1L)) {
      return 1L;

    } else {
      final Number mi = min_index(SeqUtil.tail(Utils.copy(l)));

      Number ternaryIfExp_1 = null;

      if (((Round) Utils.get(l, mi.longValue() + 1L)).round_time.longValue()
          < ((Round) Utils.get(l, 1L)).round_time.longValue()) {
        ternaryIfExp_1 = mi.longValue() + 1L;
      } else {
        ternaryIfExp_1 = 1L;
      }

      return ternaryIfExp_1;
    }
  }

  public String toString() {

    return "AtoB{" + "round := " + Utils.toString(round) + "}";
  }
}
