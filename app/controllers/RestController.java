package controllers;

import play.mvc.*;
import play.libs.ws.*;
import play.Logger;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import play.libs.ws.WSBodyReadables;

import javax.inject.Inject;

public class RestController extends Controller {

    private final WSClient ws;

    @Inject
    public RestController(WSClient ws) {
        this.ws = ws;
    }

    public CompletionStage<Result> callGitHub() {
        return ws.url("https://api.github.com")
            .setContentType("application/json")
            .get()
            .thenApply((WSResponse r) -> {
                String result = r.getBody();
                Logger.info("Result from github is " + result);
                return ok(r.getBody(WSBodyReadables.instance.json()));
            });
    }

    public Result callGitHubSync() throws InterruptedException, ExecutionException {
        WSRequest req = ws.url("https://api.github.com")
            .setContentType("application/json");
        WSResponse r = req.get().toCompletableFuture().get();

        String result = r.getBody();
        Logger.info("Result from github is " + result);
        return ok(r.getBody(WSBodyReadables.instance.json()));
    }
}
