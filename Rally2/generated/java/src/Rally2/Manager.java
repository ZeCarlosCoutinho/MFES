package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Manager extends TeamMember {
  public void cg_init_Manager_1(final String nm, final String nati) {

    nationality = nati;
    name = nm;
    return;
  }

  public Manager(final String nm, final String nati) {

    cg_init_Manager_1(nm, nati);
  }

  public Manager() {}

  public String toString() {

    return "Manager{}";
  }
}
