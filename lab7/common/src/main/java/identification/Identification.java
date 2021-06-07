package identification;

import java.io.Serializable;
import java.util.Objects;

public class Identification implements Serializable {
    private static final long serialVersion = -2345653456456L;
    private final String login;
    private final String password;

    public Identification(String login, String password){
        this.login = login;
        this.password = password;
    }

    public static Identification parse(String str){
        if (str == null) throw new IllegalArgumentException();
        String[] logPass = str.split("\\s+");
        if (logPass.length != 2 || logPass[0].length()<2 || logPass[0].length()>20)
            throw new IllegalArgumentException();
        return new Identification(logPass[0],logPass[1]);
    }

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identification that = (Identification) o;
        return login.equals(that.login) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "Identification{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
