package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TeamMember {
  public String name;
  public String nationality;

  public TeamMember() {}

  public String toString() {

    return "TeamMember{"
        + "name := "
        + Utils.toString(name)
        + ", nationality := "
        + Utils.toString(nationality)
        + "}";
  }
}
