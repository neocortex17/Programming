package spaceMarine;

import exceptions.InvalidFieldException;
import exceptions.NoMessException;

import java.io.Serializable;
import java.util.Formatter;
import java.util.Locale;
import java.util.Objects;

/**
 * Coordinate class
 */
public class Coordinates implements Serializable {
    private static final long serialVersionUID = 2399366023042694435L;
    private float x;
    private double y;
    private ValidateField validateField;

    public Coordinates(float x, double y) {
        this.x=x;
        this.y=y;
    }

    public Coordinates getCoordinates(){
        return new Coordinates(x, y);
    }

    public float getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(that.x, x) == 0 &&
                Double.compare(that.y, y) == 0 &&
                Objects.equals(validateField, that.validateField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, validateField);
    }

    @Override
    public String toString() {
        return String.format("Coordinates(%s;%s)",String.format(Locale.ROOT,"%.1f",x),String.format(Locale.ROOT,"%.2f",y));
    }
}

