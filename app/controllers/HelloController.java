package controllers;

import play.mvc.*;
import com.typesafe.config.Config;
import javax.inject.Inject;

public class HelloController extends Controller {
    private final Config config;

    @Inject
    public HelloController(Config config) {
        this.config = config;
    }

    public Result greet() {
        return ok(views.html.hello.render());
    }
}
