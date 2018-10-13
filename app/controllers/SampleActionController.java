package controllers;

import actions.PassArgAction;
import actions.VerboseAction;
import play.mvc.*;

public class SampleActionController extends Controller {

    @With(VerboseAction.class)
    public Result callVerbose() {
        return ok("called verbose action");
    }

    @With(PassArgAction.class)
    public Result passArg() {
        String user = (String)ctx().args.get("user");
        return ok("Passed user: " + user);
    }
}
