import java.util.Objects;

public abstract class Human  {
    private final String name;
    private final String gender;

    private Status status = Status.NORMAL;
    public Human(String name, String gender){
        this.name = name;
        this.gender = gender;
    }
    public String getName(){ return name; }
    public void changeStatus(Status status){
        String msg = "";
        if (status == Status.NORMAL){
            msg = "normal";
        }else if (status == Status.MISUNDERSTANDING){
            msg = "misunderstanding";
        }else if (status == Status.SCARED){
            msg = "scared";
        }
        System.out.println(toString() + " change status to " + msg + '.');
        this.status = status;
    }

    public abstract void lookAt(FrekenBok hildur);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(name, human.name) &&
                Objects.equals(gender, human.gender) &&
                status == human.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, status);
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", status=" + status +
                '}';
    }
}


