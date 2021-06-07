package client;

import application.Application;

public class Main {
    public static void main(String[] args) {
        Application application;
        if (args.length<2){
            application = new ClientApplication(null,null);
        }else {
            application = new ClientApplication(args[0],args[1]);
        }
        application.start();
    }
}
