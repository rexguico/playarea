package actors;

public class InjectedActorProtocol {
    public static class SayHello {
        public final String name;

        public SayHello(String name) {
            this.name = name;
        }
    }
}
