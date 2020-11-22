import java.util.Objects;

public abstract class Furniture {
    private final String type;
    public Furniture(String type){
        this.type = type;
    }
    public String getType(){ return type; }
    public void follow(Furniture x, Barricade b){
        if (FrekenBok.getp()){
            b.setCounter();
            System.out.println(getType() + " followed to " + x.getType() + ".");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Furniture furniture = (Furniture) o;
        return Objects.equals(type, furniture.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type); 
    }

    @Override
    public String toString() {
        return "Furniture{" +
                "type='" + type + '\'' +
                '}';
    }
}
