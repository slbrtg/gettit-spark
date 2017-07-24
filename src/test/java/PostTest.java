import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class PostTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void post_instanceofPost_true(){
    Post testPost = new Post("red", "red", "","");
    assertTrue(testPost instanceof Post);
  }

  @Test
  public void save_assignsIdToPost_true(){
    Post testPost = new Post("red", "red", "","");
    testPost.save();
    assertTrue(testPost.getId() > 0);
  }
}
