package actors;

import akka.actor.*;
import akka.japi.*;
import actors.HelloActorProtocol.*;

public class HelloActor extends AbstractActor {

    public static Props getProps() {
        return Props.create(HelloActor.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(SayHello.class, hello -> {
                String reply = "Hello " + hello.name;
                sender().tell(reply, self());
            })
            .build();
    }
}
