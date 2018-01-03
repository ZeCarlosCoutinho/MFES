package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Sponsor {
  public String name;

  public void cg_init_Sponsor_1(final String nm) {

    name = nm;
  }

  public Sponsor(final String nm) {

    cg_init_Sponsor_1(nm);
  }

  public Sponsor() {}

  public String toString() {

    return "Sponsor{" + "name := " + Utils.toString(name) + "}";
  }
}
