package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Driver extends TeamMember {
  public void cg_init_Driver_1(final String nm, final String nati) {

    nationality = nati;
    name = nm;
  }

  public Driver(final String nm, final String nati) {

    cg_init_Driver_1(nm, nati);
  }

  public Driver() {}

  public String toString() {

    return "Driver{}";
  }
}
