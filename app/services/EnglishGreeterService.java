package services;

public class EnglishGreeterService implements GreeterService {
    @Override
    public String greet(String name) {
        return "Hello " + name;
    }
}
