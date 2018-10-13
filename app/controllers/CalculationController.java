package controllers;

import extensions.CalculationContext;
import extensions.MyExecutionContext;
import play.libs.concurrent.HttpExecutionContext;
import play.libs.concurrent.HttpExecution;
import play.mvc.*;
import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class CalculationController extends Controller {

    private final HttpExecutionContext httpExecutionContext;
    private final CalculationContext calculationContext;
    private final MyExecutionContext myExecutionContext;

    @Inject
    public CalculationController(HttpExecutionContext executionContext, CalculationContext calculationContext,
                                 MyExecutionContext myExecutionContext) {
        this.httpExecutionContext = executionContext;
        this.calculationContext = calculationContext;
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

    public CompletionStage<Result> asyncCalc(Integer num) {
        Executor myEc = HttpExecution.fromThread((Executor) calculationContext);
        Executor myEc2 = HttpExecution.fromThread((Executor) myExecutionContext);

        return supplyAsync(() -> intensiveComputation(num), myEc2)
            .thenApplyAsync(i -> processResult(i), myEc2)
            .thenApplyAsync(i -> ok("Got result: " + i), myEc2);
    }

    public Result syncCalc(Integer num) {
        int result = intensiveComputation(num);
        result = processResult(result);
        return ok("Got result: " + result);
    }

    public int intensiveComputation(Integer num) {
        if (num < 3) {
            return 1;
        }

        return intensiveComputation(num - 1) + intensiveComputation(num - 2);
    }

    private int processResult(int result) {
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {

        }


        return result;
    }
}
