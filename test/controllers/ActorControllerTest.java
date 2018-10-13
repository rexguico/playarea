package controllers;

import actors.HelloActor;
import org.junit.Test;
import play.Application;
import play.test.WithApplication;
import play.inject.guice.GuiceApplicationBuilder;
import akka.actor.*;
import play.mvc.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ActorControllerTest /*extends WithApplication*/ {

    //@Override
    //protected Application provideApplication() {
    //    return new GuiceApplicationBuilder().build();
    //}

    @Test
    public void testConstructor(){
        ActorRef helloActor = mock(ActorRef.class);
        ActorSystem system = mock(ActorSystem.class);
        when(system.actorOf(HelloActor.getProps())).thenReturn(helloActor);

        ActorRef injectedActor = mock(ActorRef.class);

        ActorController controller = new ActorController(system, injectedActor);
        assertTrue(controller != null);
    }
}
