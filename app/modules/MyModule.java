package modules;

import actors.InjectedActor;
import com.google.inject.AbstractModule;
import play.libs.akka.AkkaGuiceSupport;
import services.EnglishGreeterService;
import services.GreeterService;
import com.google.inject.name.Names;

public class MyModule extends AbstractModule implements AkkaGuiceSupport {

    @Override
    protected void configure(){
        bindActor(InjectedActor.class, "injected-actor");

        bind(GreeterService.class)
            .annotatedWith(Names.named("en"))
            .to(EnglishGreeterService.class)
            .asEagerSingleton(); // created during startup rather than lazily
    }
}
