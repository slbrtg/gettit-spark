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
      String deleteCommentsQuery = "DELETE FROM comments *;";
      // String deleteCategoriesQuery = "DELETE FROM categories *;";
      con.createQuery(deleteCommentsQuery).executeUpdate();
      // con.createQuery(deleteCategoriesQuery).executeUpdate();
    }
  }

}
