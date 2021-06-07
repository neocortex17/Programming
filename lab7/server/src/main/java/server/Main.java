package server;

import application.Application;
import log.Log;

public class  Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            Log.getLogger().error("requires db username and password in the arguments");
        }else {
            Application application = new ServerApplication(61231, args[0],args[1]);
            application.start();
        }
    }
}
