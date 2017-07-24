import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.Map;
import java.util.HashMap;

public class App {

  // public static void main(String[] args) {
  //   staticFileLocation("/public");
  //   String layout = "templates/layout.vtl";
  //
  //   get("/", (request, response) -> {
  //     model.put("template", "templates/index.vtl");
  //     return new VelocityTemplateEngine().render(
  //       new ModelAndView(model, layout)
  //     );
  //   });
  // }
}
