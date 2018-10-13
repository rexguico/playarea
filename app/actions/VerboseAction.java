package actions;

import play.mvc.*;
import play.mvc.Action.Simple;
import play.Logger;

import java.util.concurrent.CompletionStage;

public class VerboseAction extends Simple {
    public CompletionStage<Result> call(Http.Context ctx) {
        Logger.info("Calling action for {}", ctx);
        return delegate.call(ctx);
    }
}
