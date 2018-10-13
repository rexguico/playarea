package controllers;

import play.libs.concurrent.HttpExecutionContext;
import play.libs.concurrent.HttpExecution;
import play.mvc.*;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import static java.util.concurrent.CompletableFuture.supplyAsync;

import extensions.*;

public class AsyncExampleController extends Controller {

    private final HttpExecutionContext httpExecutionContext;
    private final MyExecutionContext myExecutionContext;

    @Inject
    public AsyncExampleController(HttpExecutionContext executionContext, MyExecutionContext myExecutionContext) {
        this.httpExecutionContext = executionContext;
        this.myExecutionContext = myExecutionContext;
    }

    public CompletionStage<Result> index() {
        return calculateResponse().thenApplyAsync(answer -> {
            ctx().flash().put("info", "Reponse updated!");
            return ok("answer was " + answer);
        }, httpExecutionContext.current());
    }

    private static CompletionStage<String> calculateResponse() {
        return CompletableFuture.completedFuture("42");
    }

    public CompletionStage<Result> customAsync() {
        Executor myEc = HttpExecution.fromThread((Executor)myExecutionContext);
        return supplyAsync(() -> intensiveComputation(), myEc)
            .thenApplyAsync(i -> ok("Got result: " + i), myEc);
    }

    public int intensiveComputation() {return 2;}
}
