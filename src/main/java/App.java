import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.Map;
import java.util.HashMap;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String publicLayout = "templates/public-layout.vtl";
    String privateLayout = "templates/private-layout.vtl";


    //PUBLIC VIEWS
    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("posts", Post.all());
      model.put("template", "templates/index.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, publicLayout)
      );
    });

    // get("/:subgettit", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Sub sub = Sub.find(request.params(":subgettit"));
    //   model.put("posts", Post.allFromSub(sub.getName()));
    //   model.put("template", "templates/sub.vtl");
    //   return new VelocityTemplateEngine().render(
    //     new ModelAndView(model, publicLayout)
    //   );
    // });
    //
    // get("/:subgettit/:post", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Post post = Post.findByID(Integer.parseInt(request.queryParams("post")));
    //   model.put("post", post);
    //   model.put("comments", Comment.allFromPost(post.findByID()));
    //   model.put("template", "templates/post.vtl");
    //   return new VelocityTemplateEngine().render(
    //     new ModelAndView(model, publicLayout)
    //   );
    // });
    //
    //
    // //SIGNING UP
    // post("/new-user", (request, response) -> {
    //   Map<String, Object> model = new HashMap <String, Object>();
    //   String username = request.queryParams("username");
    //   String password = request.queryParams("password");
    //   User user = new User(username, password);
    //   user.save();
    //   model.put("template", "templates/new-user.vtl");
    //   return new VelocityTemplateEngine().render(
    //     new ModelAndView(model, publicLayout);
    //   );
    // });
    //
    // //PRIVATE VIEWS
    // get("/:user", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   model.put("posts", Post.all());
    //   model.put("template", "templates/index.vtl");
    //   return new VelocityTemplateEngine().render(
    //     new ModelAndView(model, privateLayout)
    //   );
    // });
    //
    // get("/:user/:subgettit", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Sub sub = Sub.find(request.params(":subgettit"));
    //   model.put("posts", Post.allFromSub(sub.getName()));
    //   model.put("template", "templates/sub.vtl");
    //   return new VelocityTemplateEngine().render(
    //     new ModelAndView(model, privateLayout)
    //   );
    // });
    //
    // get("/:user/:subgettit/:post", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Post post = Post.findByID(Integer.parseInt(request.queryParams("post")));
    //   model.put("post", post);
    //   model.put("comments", Comment.allFromPost(post.findByID()));
    //   model.put("template", "templates/post.vtl");
    //   return new VelocityTemplateEngine().render(
    //     new ModelAndView(model, privateLayout)
    //   );
    // });
    //
    // get("/:user/newSub", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   model.put("template", "template/newSub.vtl");
    //   return new VelocityTemplateEngine().render(
    //     new ModelAndView(model, privateLayout)
    //   );
    // });
    //
    // post("/:user/newSub/", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   User user = User.find(request.params("user"));
    //   String name = request.queryParams("name");
    //   String description = request.queryParams("description");
    //   Sub sub = new Sub(name, user.getId, description())
    //   sub.save();
    //   model.put("template", "template/index.vtl");
    //   return new VelocityTemplateEngine().render(
    //     new ModelAndView(model, privateLayout)
    //   );
    // });
    //
    // get("/:user/:sub/newPost", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   model.put("template", "template/newPost.vtl");
    //   return new VelocityTemplateEngine().render(
    //     new ModelAndView(model, privateLayout)
    //   );
    // });
    //
    // post("/:user/:sub/newPost", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Sub sub = Sub.findByName(request.params("sub"))
    //   String title = request.queryParams("title");
    //   String content = request.queryParams("content");
    //   Post post = new post(, title, content, sub.getName());
    //   post.save();
    //   model.put("template", "template/index.vtl");
    //   return new VelocityTemplateEngine().render(
    //     new ModelAndView(model, privateLayout)
    //   );
    // });
  }
}
