import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;
import java.util.Arrays;
import java.sql.Timestamp;

public class Comment {
  private int id;
  private String comment;
  private int postId;
  private int userId;
  private Timestamp time;


public Comment(String comment, int postId, int userId) {
  this.id = id;
  this.comment = comment;
  this.postId = postId;
  this.userId = userId;
}
public static List<Comment> all() {
  String sql = "SELECT id, comment, postId, userId, time FROM comments";
  try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Comment.class);
  }
}

//Create Save Method
public void save() {
  try(Connection con = DB.sql2o.open()) {
  String sql = "INSERT INTO comments(comment, postId, userId, time) VALUES (:comment, :postId, :userId, now());";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("comment", this.comment)
      .addParameter("postId", this.postId)
      .addParameter("userId", this.userId)
      .executeUpdate()
      .getKey();
    }
  }

  public static Comment find(int id) {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM comments WHERE id=:id";
      Comment comment = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Comment.class);
    return comment;
    }
  }

  public void update(String comment) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE comments SET comment = :comment WHERE id=:id";
    con.createQuery(sql)
      .addParameter("comment", comment)
       .addParameter("id", this.id)
      .executeUpdate();
  }
}

public static void delete(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM comments WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
  }
}

@Override
public boolean equals(Object otherComment) {
  if(!(otherComment instanceof Comment)) {
    return false;
  } else {
    Comment newComment = (Comment) otherComment;
    return this.getId() == newComment.getId() &&
           this.getComment().equals(newComment.getComment()) &&
           this.getPostId() == newComment.getPostId() &&
           this.getUserId() == newComment.getUserId();
        }
    }

  public int getId() {
    return id;
  }

  public String getComment() {
    return comment;
  }

  public int getPostId() {
    return postId;
  }

  public int getUserId() {
    return userId;
  }

  public Timestamp time(){
     return time;
 }

}
