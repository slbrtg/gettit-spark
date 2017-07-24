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
}
