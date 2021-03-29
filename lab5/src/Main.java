import application.Application;
import application.IApplication;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        IApplication application = new Application();
        application.start();
    }
}
