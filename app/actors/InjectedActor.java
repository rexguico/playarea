package actors;

import akka.actor.*;
import akka.japi.*;
import actors.InjectedActorProtocol.*;

public class InjectedActor extends AbstractActor {

    public static Props getProps() {
        return Props.create(InjectedActor.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(SayHello.class, hello -> {
                String reply = "Injected hello " + hello.name;
                sender().tell(reply, self());
            })
            .build();
    }
}

