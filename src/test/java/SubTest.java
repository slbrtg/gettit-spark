import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;
import java.util.Arrays;
import java.sql.Timestamp;
import org.junit.*;
import static org.junit.Assert.*;

public class SubTest {

  @Rule
   public DatabaseRule database = new DatabaseRule();

   @Test
   public void subs_instantiatesCorrectly_true() {
     Sub testSub = new Sub("Technology", 1, "Learn Technology");
     assertEquals(true, testSub instanceof Sub);
   }

   @Test
   public void getId_getIdFromObject_1() {
     Sub testSub = new Sub("Technology", 1, "Learn Technology");
     assertEquals("Technology", testSub.getName());
   }

   @Test
   public void getDescription_returnsDescription_LearnTechnology() {
     Sub testSub = new Sub("Technology", 1, "Learn Technology");
     assertEquals("Learn Technology", testSub.getDescription());
   }

   @Test
   public void getUserId_returnUserId_userId() {
     Sub testSub = new Sub("Technology", 1, "Learn Technology");
     assertEquals(1, testSub.getModId());
   }

   @Test
   public void save_assignsIdToObject() {
     Sub testSub = new Sub("Technology", 1, "Learn Technology");
     testSub.save();
     Sub savedSub = Sub.all().get(0);
     assertEquals(testSub.getId(), savedSub.getId());
   }
   @Test
   public void all_returnsAllInstances_true() {
     Sub firstSub = new Sub("Technology", 1, "Learn Technology");
     firstSub.save();
     Sub secondSub = new Sub("Sport", 2, "Learn Sports");
     secondSub.save();
     assertEquals(true, Sub.all().get(0).equals(firstSub));
     assertEquals(true, Sub.all().get(1).equals(secondSub));
   }

   @Test
   public void find_returnSubWIthSameId_testSub2() {
     Sub testSub1 = new Sub("Technology", 1, "Learn Technology");
     testSub1.save();
     Sub testSub2 = new Sub("Sport", 2, "Learn Sports");
     testSub2.save();
     assertEquals(Sub.find(testSub2.getId()), testSub2);
   }

   @Test
   public void update_updatesSub_true() {
     Sub testSub = new Sub("Technology", 1, "Learn Technology");
     testSub.save();
     testSub.update("Goodbye");
     Sub sub = Sub.find(testSub.getId());
     assertEquals("Goodbye", sub.getName());
   }

   @Test
   public void delete_deletesASub_true() {
     Sub testSub2 = new Sub("Technology", 1, "Learn Technology");
     testSub2.save();
     int testSubId = testSub2.getId();
     Sub.delete(testSubId);
     assertEquals(null, Sub.find(testSubId));
   }


}
