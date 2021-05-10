package output;

public class ConsoleOutputManager implements OutputManager{
    @Override
    public void printMsg(String msg) {
        System.out.println(msg);
    }

    @Override
    public void printErrorMsg(String msg) {
        System.err.println(msg);
    }
}
