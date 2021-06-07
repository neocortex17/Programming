package input;

import identification.Identification;
import identification.SHA224Generator;
import messages.Message;
import output.OutputManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class ConsoleIdentReader implements IdentReader{
    private final BufferedReader reader;
    private final Message message;
    private final OutputManager outputManager;
    private final Pattern usernamePattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_.]{1,19}$");
    private final Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9]{6,}$");
    private final String salt;

    public ConsoleIdentReader(BufferedReader reader, Message message, OutputManager outputManager,String salt){
        this.reader = reader;
        this.message = message;
        this.outputManager = outputManager;
        this.salt = salt;
    }

    @Override
    public Identification readIdent() throws IOException {
        outputManager.printMsg(message.getMessage("loginInput") + ": ");
        String login = reader.readLine();
        while (!usernamePattern.matcher(login).matches()){
            outputManager.printErrorMsg(message.getMessage("wrongLoginPattern") + "\n");
            outputManager.printMsg(message.getMessage("loginInput") + ": ");
            login = reader.readLine();
        }
        String password;
        outputManager.printMsg(message.getMessage("passwordInput") + ": ");
        if (System.console() != null){
            password = String.valueOf(System.console().readPassword());
            while (!passwordPattern.matcher(password).matches()){
                outputManager.printErrorMsg(message.getMessage("wrongPasswordPattern") + "\n");
                outputManager.printMsg(message.getMessage("passwordInput") + ": ");
                password = String.valueOf(System.console().readPassword());
            }
        }else {
            password = reader.readLine();
            while (!passwordPattern.matcher(password).matches()){
                outputManager.printErrorMsg(message.getMessage("wrongPasswordPattern") + "\n");
                outputManager.printMsg(message.getMessage("passwordInput") + ": ");
                password = reader.readLine();
            }
        }
        password = SHA224Generator.getHash(password + salt);
        return new Identification(login, password);
    }
}
