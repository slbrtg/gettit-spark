import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class User {
  private int id;
  private String username;
  private String password;
  private List<String> subs;

  public User(String username, String password){
    this.username = username;
    this.password = password;
    this.subs = new ArrayList<String>();
  }

  public int getId(){
    return id;
  }

  public String getUsername(){
    return username;
  }

  public String getPassword(){
    return password;
  }

  public boolean subscribe(String name){
    subs.add(name);
    if (subs.contains(name)){
      return true;
    } else {
      return false;
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO users(username, password) VALUES(:username, :password);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("username", this.username)
        .addParameter("password", this.password)
        .executeUpdate()
      .getKey();
    }
  }
}
