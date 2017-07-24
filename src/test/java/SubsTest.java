import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.sql.Timestamp;

public class SubsTest {

  @Rule
   public DatabaseRule database = new DatabaseRule();

   @Test
   public void subs_instantiatesCorrectly_true() {
     Subs testSubs = new Subs("Molly", 1, 2);
     assertEquals(true, testSubs instanceof Subs);
   }

   @Test
   public void getId_getIdFromObject_1() {
     Subs testSubs = new Subs("Molly", 1, 2);
     assertEquals("Molly", testSubs.getName());
   }

   @Test
   public void getSubs_returnSubs_comment() {
     Subs testSubs = new Subs("Good One", 1, 2);
     assertEquals(1, testSubs.getUserId());
   }

   @Test
   public void getUserId_returnUserId_userId() {
     Subs testSubs = new Subs("Good One", 1, 2);
     assertEquals(2, testSubs.getModId());
   }

}
