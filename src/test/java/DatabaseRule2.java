import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/gettit_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
<<<<<<< HEAD
      String deleteUsersQuery = "DELETE FROM users *;";
      con.createQuery(deleteUsersQuery).executeUpdate();
      String deletePostsQuery = "DELETE FROM posts *;";
      con.createQuery(deletePostsQuery).executeUpdate();
=======
      String deleteCommentsQuery = "DELETE FROM comments *;";
      String deleteSubsQuery = "DELETE FROM subs *;";
      con.createQuery(deleteCommentsQuery).executeUpdate();
      con.createQuery(deleteSubsQuery).executeUpdate();
>>>>>>> 2c33a229db359629a09c26907e12522f80264f36
    }
  }
}
