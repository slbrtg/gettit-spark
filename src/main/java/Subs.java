
import org.sql2o.*;
import java.sql.Timestamp;

public class Subs {
  private int id;
  private String name;
  private int userId;
  private int modId;

  public Subs (String name, int userId, int modId){
    this.name = name;
    this.userId = userId;
    this.modId = modId;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getUserId() {
    return userId;
  }

  public int getModId() {
    return modId;
  }

}
