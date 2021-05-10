package server;

import application.Application;

public class Main {
    public static void main(String[] args) {
        Application application = new ServerApplication(61231);
        application.start();
    }
}
