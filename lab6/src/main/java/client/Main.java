package client;

import application.Application;

public class Main {
    public static void main(String[] args) {
        Application application = new ClientApplication("localhost", 61231);
        application.start();
    }
}
