package controllers;

import play.mvc.*;
import play.mvc.Http.*;

import java.time.Duration;

public class CookieController extends Controller {

    public Result setValue() {
        response().setCookie(
            Cookie.builder("source", "server")
                .withMaxAge(Duration.ofSeconds(3600))
                .withPath("/some/path")
                .withDomain(".test.com")
                .withSecure(false)
                .withHttpOnly(true)
                .withSameSite(Cookie.SameSite.STRICT)
                .build()
        );
        return  ok("cookie is set");
    }

    public Result removeValue() {
        response().discardCookie("source");
        return ok("cookie is removed");
    }
}
