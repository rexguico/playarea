package extensions;

import play.libs.concurrent.HttpExecution;
import play.libs.concurrent.CustomExecutionContext;

import akka.actor.*;

import javax.inject.Inject;
import java.util.concurrent.Executor;
import java.util.concurrent.CompletionStage;
import static java.util.concurrent.CompletableFuture.supplyAsync;

import extensions.*;

public class MyExecutionContext extends CustomExecutionContext {

    @Inject
    public MyExecutionContext(ActorSystem actorSystem) {
        super(actorSystem, "my.dispathcer");
    }
}
