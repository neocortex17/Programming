package input;

import exceptions.ScriptException;
import inputManager.CommandReader;

import java.io.BufferedReader;
import java.io.IOException;

public class ScriptCommandReader implements CommandReader {
    private BufferedReader bufferedReader;

    public ScriptCommandReader(BufferedReader bufferedReader){
        this.bufferedReader = bufferedReader;
    }

    @Override
    public String readCommand() {
        try {
            String input = bufferedReader.readLine();
            return input.trim().toLowerCase();
        }catch (IOException e){
            throw new ScriptException();
        }
    }
}
