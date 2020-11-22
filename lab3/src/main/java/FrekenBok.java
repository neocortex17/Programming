import java.util.Objects;

public final class FrekenBok extends Human {
    private final String fname;
    private static boolean p;

    public FrekenBok(String name, String gender, String _fname){
        super(name, gender);
        fname = _fname;
        System.out.println("Human - " + name + ' ' + _fname + " was successfully created.");
    }
    public String getFname(FrekenBok k){
        return k.fname;
    }

    public String lookAt(){
        return ("scared " + getName());
    }

    public void noWantHear(FrekenBok k){
        System.out.println("Now " + getName() + " didn't want to hear about " + getFname(k) + '.');
    }

    public static boolean getp(){
        return p;
    }

    public void move (Door door){
            p = true;
            System.out.println(getName() + " continued to move all the furniture to the " + door.getType() + ".");
    }

    @Override
    public void lookAt(FrekenBok hildur) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrekenBok frekenBok = (FrekenBok) o;
        return Objects.equals(fname, frekenBok.fname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fname);
    }

    @Override
    public String toString() {
        return "Freken Bok";
    }

}
