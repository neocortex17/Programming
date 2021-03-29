package application;

public interface IApplication {
    /**
     * Method that launches the program
     */
    void start() throws NoSuchFieldException;

    /**
     * Method that stopped the program
     */
    void exit();
}
