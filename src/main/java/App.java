import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.Map;
import java.util.HashMap;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String publicLayout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("posts", Post.all());
      model.put("template", "templates/index.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, publicLayout)
      );
    });

    get("/:subgettit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Sub sub = Sub.find(request.params(":subgettit"));
      model.put("posts", Post.allFromSub(sub.getName()));
      model.put("template", "templates/sub.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, publicLayout)
      );
    });

    get("/:subgettit/:post", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Post post = Post.findByID(Integer.parseInt(request.queryParams("post")));
      model.put("post", post);
      model.put("comments", Comment.allFromPost(post.findByID()));
      model.put("template", "templates/post.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, publicLayout)
      );
    });



    post("/new-user", (request, response) -> {
      Map<String, Object> model = new HashMap <String, Object>();
      String username = request.queryParams("username");
      String password = request.queryParams("password");
      User user = new User(username, password);
      user.save();
      model.put("template", "templates/new-user.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, publicLayout);
      );
    });
  }
}
