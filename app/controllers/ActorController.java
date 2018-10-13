package controllers;

import actors.HelloActor;
import actors.HelloActorProtocol;
import actors.HelloActorProtocol.*;
import actors.InjectedActorProtocol;
import actors.InjectedActor;
import play.mvc.*;
import akka.actor.*;
import scala.compat.java8.FutureConverters;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.CompletionStage;
import static akka.pattern.Patterns.ask;

public class ActorController extends Controller {

    private final ActorRef helloActor;
    private final ActorRef injectedActor;

    @Inject
    public ActorController(ActorSystem system, @Named("injected-actor") ActorRef injectedActor) {
        helloActor = system.actorOf(HelloActor.getProps());
        this.injectedActor = injectedActor;
    }

    public CompletionStage<Result> sayHello(String name) {
        return FutureConverters.toJava(ask(helloActor, new SayHello(name), 1000))
            .thenApply(response -> ok((String)response));
    }

    public CompletionStage<Result> sayInjectedHello(String name) {
        return FutureConverters.toJava(ask(injectedActor, new InjectedActorProtocol.SayHello(name), 1000))
            .thenApply(response -> ok((String)response));
    }
}
