package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Mechanic extends TeamMember {
  public void cg_init_Mechanic_1(final String nm, final String nati) {

    name = nm;
    nationality = nati;
  }

  public Mechanic(final String nm, final String nati) {

    cg_init_Mechanic_1(nm, nati);
  }

  public Mechanic() {}

  public String toString() {

    return "Mechanic{}";
  }
}
