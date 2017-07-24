import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class UserTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void user_instanceofUser_true(){
    User testUser = new User("red", "red");
    assertTrue(testUser instanceof User);
  }

  @Test
  public void subscribe_addsSubIdToList_true(){
    User testUser = new User("red", "red");
    assertTrue(testUser.subscribe("test"));
  }

  @Test
  public void save_assignsIdToUser_true(){
    User testUser = new User("red", "red");
    testUser.save();
    assertTrue(testUser.getId() > 0);
  }

  @Test
  public void all_returnsAllUsers_true(){
    User testUser = new User("red", "red");
    testUser.save();
    assertEquals(User.all().get(0).getId(), testUser.getId());
  }

  @Test
  public void find_returnsCorrectUsers_true(){
    User testUser = new User("red", "red");
    testUser.save();
    User testUser2 = User.find(testUser.getUsername());
    assertEquals(testUser2.getId(), testUser.getId());
  }

  @Test
  public void delete_returnsNullOnFindDeletedUser_null(){
    User testUser = new User("red", "red");
    testUser.save();
    String username = testUser.getUsername();
    testUser.delete();
    assertEquals(null, User.find(username));
  }
}
