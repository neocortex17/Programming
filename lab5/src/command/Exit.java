package command;

import application.IApplication;

/**
 * The class of the command that terminates the program
 */
public class Exit implements Command {
    private IApplication application;

    /**
     * Constructor for Exit
     * @param application object of Application
     */
    public Exit (IApplication application) {
        this.application = application;
    }

    @Override
    public void execute() {
        application.exit();
    }
}
