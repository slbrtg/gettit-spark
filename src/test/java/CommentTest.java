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

}
