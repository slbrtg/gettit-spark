import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.sql.Timestamp;

public class CommentTest {

  @Rule
   public DatabaseRule database = new DatabaseRule();

  @Test
  public void comment_instantiatesCorrectly_true() {
    Comment testComment = new Comment("Good One", 1, 2);
    assertEquals(true, testComment instanceof Comment);
  }

  @Test
  public void getId_getIdFromObject_1() {
    Comment testComment = new Comment("Good One", 1, 2);
    assertEquals(1, testComment.getPostId());
  }

  @Test
  public void getComment_returnComment_comment() {
    Comment testComment = new Comment("Good One", 1, 2);
    assertEquals("Good One", testComment.getComment());
  }

  @Test
  public void getUserId_returnUserId_userId() {
    Comment testComment = new Comment("Good One", 1, 2);
    assertEquals(2, testComment.getUserId());
  }


}
