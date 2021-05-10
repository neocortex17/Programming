package input;

import inputManager.CommandReader;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleCommandReader implements CommandReader {
    private BufferedReader bufferedReader;

    public ConsoleCommandReader (BufferedReader bufferedReader){
        this.bufferedReader = bufferedReader;
    }

    @Override
    public String readCommand() throws IOException {
        String input = bufferedReader.readLine();
        if (input == null) throw new IOException();
        return input.trim().toLowerCase();
    }
}
