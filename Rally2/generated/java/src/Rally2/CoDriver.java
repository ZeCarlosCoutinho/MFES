package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class CoDriver extends TeamMember {
  public void cg_init_CoDriver_1(final String nm, final String nati) {

    nationality = nati;
    name = nm;
    return;
  }

  public CoDriver(final String nm, final String nati) {

    cg_init_CoDriver_1(nm, nati);
  }

  public CoDriver() {}

  public String toString() {

    return "CoDriver{}";
  }
}
