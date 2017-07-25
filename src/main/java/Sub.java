import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;
import java.util.Arrays;
import java.sql.Timestamp;

public class Sub {
  private int id;
  private String name;
  private String description;
  private int modId;

  public Sub (String name, int modId, String description){
    this.name = name;
    this.description = description;
    this.modId = modId;
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getModId() {
    return modId;
  }

  public static List<Sub> all() {
    String sql = "SELECT id, name, modId, description FROM subs";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Sub.class);
    }
  }

  //Create Save Method
  public void save() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO subs(name, modId, description) VALUES (:name, :modId, :description);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("modId", this.modId)
        .addParameter("description", this.description)
        .executeUpdate()
        .getKey();
      }
    }

    public static Sub find(int id) {
      try(Connection con = DB.sql2o.open()){
        String sql = "SELECT * FROM subs WHERE id=:id";
        Sub sub = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Sub.class);
      return sub;
      }
    }

    public static Sub findByName(String name) {
      try(Connection con = DB.sql2o.open()){
        String sql = "SELECT * FROM subs WHERE name=:name";
        Sub sub = con.createQuery(sql)
        .addParameter("name", name)
        .executeAndFetchFirst(Sub.class);
      return sub;
      }
    }

    public void update(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE subs SET name = :name WHERE id=:id";
      con.createQuery(sql)
        .addParameter("name", name)
         .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public static void delete(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM subs WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  @Override
  public boolean equals(Object otherSub) {
    if(!(otherSub instanceof Sub)) {
      return false;
    } else {
      Sub newSub = (Sub) otherSub;
      return this.getId() == newSub.getId() &&
             this.getName().equals(newSub.getName()) &&
             this.getModId() == newSub.getModId() &&
             this.getDescription().equals(newSub.getDescription());
          }
      }

}
