package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class StageResult {
  public Car car;
  public Number stage_time;

  public void cg_init_StageResult_1(final Car c, final Number s_t) {

    car = c;
    stage_time = s_t;
  }

  public StageResult(final Car c, final Number s_t) {

    cg_init_StageResult_1(c, s_t);
  }

  public StageResult() {}

  public String toString() {

    return "StageResult{"
        + "car := "
        + Utils.toString(car)
        + ", stage_time := "
        + Utils.toString(stage_time)
        + "}";
  }
}
