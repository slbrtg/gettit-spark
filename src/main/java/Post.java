import org.sql2o.*;
import java.sql.Timestamp;

public class Post {
  private int id;
  private String type;
  private String title;
  private String content;
  private String subName;
  private int votes;
  private Timestamp time;

  //CONSTRUCTOR
  public Post(String type, String title, String content, String subName){
    this.type = type;
    this.title = title;
    this.content = content;
    this.subName = subName;
    this.votes = 0;
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
  }

  //DATABASE METHODS
  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO posts(title, type, content, votes, subname, time) VALUES (:title, :type, :content, :votes, :subname, now());";

      this.id = (int) con.createQuery(sql, true)
        .addParameter("title", this.title)
        .addParameter("type", this.type)
        .addParameter("content", this.content)
        .addParameter("votes", this.votes)
        .addParameter("subname", this.subName)
        .executeUpdate()
      .getKey();
    }
  }
}
