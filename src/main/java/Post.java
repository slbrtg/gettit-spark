import org.sql2o.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

public class Post {
  private int id;
  private String type;
  private String title;
  private String content;
  private String subName;
  private String username;
  private String glyph;
  private int votes;
  private Timestamp time;

  //CONSTRUCTOR
  public Post(String type, String title, String content, String subName, String username, String glyph){
    this.type = type;
    this.title = title;
    this.content = content;
    this.subName = subName;
    this.votes = 0;
    this.username = username;
    this.glyph = glyph;
  }

  //GET/SET METHODS
  public int getId(){
    return id;
  }

  public String getType(){
    return type;
  }

  public String getTitle(){
    return title;
  }

  public String getContent(){
    return content;
  }

  public String getSubName(){
    return subName;
  }

  public int getVotes(){
    return votes;
  }

  public void upvote(){
    votes += 1;
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE posts SET votes=:votes WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("votes", this.votes)
        .addParameter("id", this.id)
        .executeUpdate();

    }
  }

  public void downvote(){
    votes -= 1;
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE posts SET votes=:votes WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("votes", this.votes)
        .addParameter("id", this.id)
        .executeUpdate();

    }
  }


  //DATABASE METHODS
  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO posts(title, type, content, votes, subname, time, username, glyph) VALUES (:title, :type, :content, :votes, :subname, now(), :username, :glyph);";

      this.id = (int) con.createQuery(sql, true)
        .addParameter("title", this.title)
        .addParameter("type", this.type)
        .addParameter("content", this.content)
        .addParameter("votes", this.votes)
        .addParameter("subname", this.subName)
        .addParameter("username", this.username)
        .addParameter("glyph", this.glyph)
        .executeUpdate()
      .getKey();
    }
  }

  public void update(String content){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE posts SET content=:content where id=:id;";
      con.createQuery(sql)
        .addParameter("content", this.content)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM posts WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public static List<Post> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM posts;";
      return con.createQuery(sql).executeAndFetch(Post.class);
    }
  }

  public static List<Post> allFromSub(String subname){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM posts WHERE subname=:subname;";
      List<Post> allFromSub = con.createQuery(sql)
        .addParameter("subname", subname)
        .executeAndFetch(Post.class);
      return allFromSub;
    }
  }

  public static List<Post> find(String title){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM posts WHERE title=:title;";
      List<Post> searchResults =  con.createQuery(sql)
        .addParameter("title", title)
        .executeAndFetch(Post.class);
      return searchResults;
    }
  }

  public static Post findByID(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM posts WHERE id=:id;";
      Post post = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Post.class);
      return post;
    }
  }
}
