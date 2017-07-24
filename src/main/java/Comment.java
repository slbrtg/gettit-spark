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

  this.comment = comment;
  this.postId = postId;
  this.userId = userId;

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
}
