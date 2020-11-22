import java.util.Objects;

public final class Barricade {
    private int Counter;
    private final String name;
    private Status status;

    public Barricade (String name){
    this.name = name;
    }

    public String getName(){return name;}

    public void formed(Barricade b){
        String msg = "";
        if (b.Counter > 3){
            b.status = Status.PRESENT;
        } else {
            b.status = Status.NOTPRESENT;
        }
        if (b.status == Status.NOTPRESENT){
            msg = "fake";
        }
        if (b.status == Status.PRESENT){
            msg = "present";
        }
        System.out.println("A " + msg + " " + getName() + " was formed.");
    }

    public void setCounter() {
        Counter += 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barricade barricade = (Barricade) o;
        return Objects.equals(name, barricade.name) &&
                status == barricade.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, status);
    }

    @Override
    public String toString() {
        return "Barricade{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}

