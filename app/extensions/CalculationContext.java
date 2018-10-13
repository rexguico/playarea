package extensions;

import play.libs.concurrent.CustomExecutionContext;

import akka.actor.*;

import javax.inject.Inject;

public class CalculationContext extends CustomExecutionContext {

    @Inject
    public CalculationContext(ActorSystem actorSystem) {
        super(actorSystem, "calculation.dispatcher");
    }
}
