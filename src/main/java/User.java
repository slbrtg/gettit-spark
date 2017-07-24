import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class User {
  private int id;
  private String username;
  private String password;
  private List<String> subs;

  //CONSTRUCTOR
  public User(String username, String password){
    this.username = username;
    this.password = password;
    this.subs = new ArrayList<String>();
  }

  //GETTER/SETTER
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

  //DATABASE METHODS
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

  public void update(String username){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE users SET username=:username where id=:id;";
      con.createQuery(sql)
        .addParameter("username", this.username)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM users WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }



  public static List<User> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM users;";
      return con.createQuery(sql).executeAndFetch(User.class);
    }
  }

  public static User find(String username){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM users WHERE username=:username;";
      User user = con.createQuery(sql)
        .addParameter("username", username)
        .executeAndFetchFirst(User.class);
      return user;
    }
  }

  public static User findByID(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM users WHERE id=:id;";
      User user = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(User.class);
      return user;
    }
  }
}
