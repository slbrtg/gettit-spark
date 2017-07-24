import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.sql.Timestamp;

public class CommentTest {

  @Rule
   public DatabaseRule database = new DatabaseRule();

  @Test
  public void comment_instantiatesCorrectly_true() {
    Comment testComment = new Comment("Good One", 1, "2");
    assertEquals(true, testComment instanceof Comment);
  }

  @Test
  public void getId_getIdFromObject_1() {
    Comment testComment = new Comment("Good One", 1, "2");
    assertEquals(1, testComment.getPostId());
  }

  @Test
  public void getComment_returnComment_comment() {
    Comment testComment = new Comment("Good One", 1, "2");
    assertEquals("Good One", testComment.getComment());
  }

  @Test
  public void getUsername_returnUsername_userId() {
    Comment testComment = new Comment("Good One", 1, "2");
    assertEquals("2", testComment.getUsername());
  }
//   @Test
//   public void getId_instantiatesWithAnId_true() {
//     Comment testComment = new Comment("Good One", 1, "2");
//     testComment.save();
//     assertTrue(testComment.getId() > 0);
// }
  @Test
  public void save_assignsIdToObject() {
    Comment testComment = new Comment("Good One", 1, "2");
    testComment.save();
    Comment savedComment = Comment.all().get(0);
    assertEquals(testComment.getId(), savedComment.getId());
  }
  @Test
  public void all_returnsAllInstances_true() {
    Comment firstComment = new Comment("Good One", 1, "2");
    firstComment.save();
    Comment secondComment = new Comment("Awesome", 3, "3");
    secondComment.save();
    assertEquals(true, Comment.all().get(0).equals(firstComment));
    assertEquals(true, Comment.all().get(1).equals(secondComment));
  }

  @Test
  public void find_returnCommentWIthSameId_testComment2() {
    Comment testComment1 = new Comment("Good One", 1, "2");
    testComment1.save();
    Comment testComment2 = new Comment("Goodbye", 2, "3");
    testComment2.save();
    assertEquals(Comment.find(testComment2.getId()), testComment2);
  }

  @Test
  public void update_updatesComment_true() {
    Comment testComment = new Comment("Good One", 1, "2");
    testComment.save();
    testComment.update("Goodbye");
    assertEquals("Goodbye", Comment.find(testComment.getId()).getComment());
  }

  @Test
  public void delete_deletesAComment_true() {
    Comment testComment2 = new Comment("Goodbye", 2, "3");
    testComment2.save();
    int testCommentId = testComment2.getId();
    Comment.delete(testCommentId);
    assertEquals(null, Comment.find(testCommentId));
  }


}
