package controllers;

import play.mvc.*;

public class SessionController extends Controller {

    public Result index() {
        String user = session("connected");
        if (user != null) {
            String greeting = flash("greeting");
            return ok("Hello " + user + "." + greeting);
        } else {
            return unauthorized("Not yet connected...");
        }
    }

    public Result login() {
        session("connected", "thisuser");
        flash("greeting", "Welcome!");
        return redirect(routes.SessionController.index());
    }

    public Result logout() {
        session().remove("connected");
        return ok("Bye =(");
    }
}
