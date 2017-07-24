import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;

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

  @Test
  public void all_returnsAllPosts_true(){
    Post testPost = new Post("", "", "", "");
    testPost.save();
    assertEquals(Post.all().get(0).getId(), testPost.getId());
  }

  @Test
  public void find_returnsCorrectPosts_true(){
    Post testPost = new Post("rr", "r", "r", "r");
    testPost.save();
    List<Post> searchResults = Post.find(testPost.getTitle());
    assertEquals(searchResults.get(0).getId(), testPost.getId());
  }

  @Test
  public void delete_returnsNullOnFindDeletedPost_null(){
    Post testPost = new Post("", "", "", "");
    testPost.save();
    int id = testPost.getId();
    testPost.delete();
    assertEquals(null, Post.findByID(id));
  }
}
