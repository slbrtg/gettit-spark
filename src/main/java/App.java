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
      model.put("subs", Sub.all());
      model.put("Comment", Comment.class);
      model.put("template", "templates/index.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, publicLayout)
      );
    });

    get("/subgettit/:subgettit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Sub sub = Sub.findByName(request.params(":subgettit"));
      model.put("posts", Post.allFromSub(sub.getName()));
      model.put("subs", Sub.all());
      model.put("sub", sub);
      model.put("Comment", Comment.class);
      model.put("template", "templates/sub.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, publicLayout)
      );
    });

    get("/subgettit/:subgettit/post/:post", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Post post = Post.findByID(Integer.parseInt(request.params("post")));
      model.put("post", post);
      model.put("subs", Sub.all());
      model.put("Comment", Comment.class);
      model.put("comments", Comment.allFromPost(post.getId()));
      model.put("template", "templates/post.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, publicLayout)
      );
    });

    //SIGNING UP
    get("/new-user", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("subs", Sub.all());
      model.put("template", "templates/new-user.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, privateLayout)
      );
    });

    post("/new-user-success", (request, response) -> {
      Map<String, Object> model = new HashMap <String, Object>();
      String username = request.queryParams("username");
      String password = request.queryParams("password");
      User user = new User(username, password);
      user.save();
      model.put("user", user);
      model.put("subs", Sub.all());
      model.put("template", "templates/new-user-success.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, privateLayout)
      );
    });

    //PRIVATE VIEWS
    get("/:user", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      User user = User.find(request.params("user"));
      model.put("user", user);
      model.put("posts", Post.all());
      model.put("subs", Sub.all());
      model.put("Comment", Comment.class);
      model.put("template", "templates/user.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, privateLayout)
      );
    });

    post("/welcome", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String username = request.queryParams("userUsername");
      String password = request.queryParams("userPassword");
      if (User.login(username,password) != -1){
        User user = User.findByID(User.login(username,password));
        model.put("user", user);
        model.put("subs", Sub.all());
        model.put("posts", Post.all());
        model.put("Comment", Comment.class);
        model.put("template", "templates/user.vtl");
      } else {
        model.put("subs", Sub.all());
        model.put("template", "templates/login-failure.vtl");
      }
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, privateLayout)
      );
    });

    post("/:user/subgettit/:subgettit/post/:postID/upvote", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int postID = Integer.parseInt(request.params("postID"));
      User user = User.find(request.params("user"));
      Post post = Post.findByID(postID);
      post.upvote();
      // post.downvote();
      model.put("post", post);
      model.put("user", user);
      model.put("template", "templates/user.vtl");
      response.redirect("/" + user.getUsername());
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, privateLayout)
      );
    });

    post("/:user/subgettit/:subgettit/post/:postID/downvote", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int postID = Integer.parseInt(request.params("postID"));
      User user = User.find(request.params("user"));
      Post post = Post.findByID(postID);
      post.downvote();
      // post.downvote();
      model.put("post", post);
      model.put("user", user);
      model.put("template", "templates/user.vtl");
      response.redirect("/" + user.getUsername());
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, privateLayout)
      );
    });

    //POST CREATION AND VIEWING
    get("/:user/subgettit/:subgettit/new-post", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      User user = User.find(request.params("user"));
      Sub sub = Sub.findByName(request.params("subgettit"));
      model.put("user", user);
      model.put("subs", Sub.all());
      model.put("sub", sub);
      model.put("template", "templates/new-post.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, privateLayout)
      );
    });

    post("/:user/subgettit/:subgettit/new-post-success", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      User user = User.find(request.params("user"));
      Sub sub = Sub.findByName(request.params("subgettit"));
      String type = request.queryParams("type");
      String title = request.queryParams("title");
      String content = request.queryParams("content");
      String glyph = request.queryParams("glyph");
      Post post = new Post(type, title, content, sub.getName(), user.getUsername(), glyph);
      post.save();
      model.put("post", post);
      model.put("user", user);
      model.put("sub", sub);
      model.put("subs", Sub.all());
      model.put("template", "templates/new-post-success.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, privateLayout)
      );
    });

    get("/:user/subgettit/:subgettit/post/:postID", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      User user = User.find(request.params("user"));
      Sub sub = Sub.findByName(request.params("subgettit"));
      int postID = Integer.parseInt(request.params("postID"));
      Post post = Post.findByID(postID);
      model.put("comments", Comment.allFromPost(post.getId()));
      model.put("Comment", Comment.class);
      model.put("post", post);
      model.put("user", user);
      model.put("sub", sub);
      model.put("subs", Sub.all());
      model.put("template", "templates/post-user.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, privateLayout)
      );
    });

    post("/:user/subgettit/:subgettit/post/:postID", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      User user = User.find(request.params("user"));
      Sub sub = Sub.findByName(request.params("subgettit"));
      int postID = Integer.parseInt(request.params("postID"));
      Post post = Post.findByID(postID);
      String content = request.queryParams("comment");
      Comment comment = new Comment(content, post.getId(), user.getUsername());
      comment.save();
      model.put("comments", Comment.allFromPost(post.getId()));
      model.put("Comment", Comment.class);
      model.put("post", post);
      model.put("user", user);
      model.put("sub", sub);
      model.put("subs", Sub.all());
      model.put("template", "templates/post-user.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, privateLayout)
      );
    });

    //NEW SUB CREATION AND VIEWING
    get("/:user/newSub", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      User user = User.find(request.params("user"));
      model.put("user", user);
      model.put("subs", Sub.all());
      model.put("template", "templates/newSub.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, privateLayout)
      );
    });

    post("/:user/newSub/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      User user = User.find(request.params("user"));
      String name = request.queryParams("name");
      String description = request.queryParams("description");
      Sub sub = new Sub(name, user.getId(), description);
      sub.save();
      model.put("user", user);
      model.put("sub", sub);
      model.put("subs", Sub.all());
      model.put("template", "templates/new-sub-success.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, privateLayout)
      );
    });

    get("/:user/subgettit/:subgettit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Sub sub = Sub.findByName(request.params("subgettit"));
      User user = User.find(request.params("user"));
      model.put("user", user);
      model.put("subs", Sub.all());
      model.put("Comment", Comment.class);
      model.put("sub", sub);
      model.put("posts", Post.allFromSub(sub.getName()));
      model.put("template", "templates/sub-user.vtl");
      return new VelocityTemplateEngine().render(
        new ModelAndView(model, privateLayout)
      );
    });
  }
}
