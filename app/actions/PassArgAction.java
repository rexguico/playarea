package actions;

import play.mvc.*;
import play.mvc.Action.Simple;

import java.util.concurrent.CompletionStage;

public class PassArgAction extends Simple {
    public CompletionStage<Result> call(Http.Context ctx) {
        ctx.args.put("user", "myuser");
        return delegate.call(ctx);
    }
}
