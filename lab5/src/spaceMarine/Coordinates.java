package spaceMarine;

import exceptions.InvalidFieldException;
import exceptions.NoMessException;
import managers.SpaceMarineValidateField;
import managers.ValidateField;
import messageManager.IMessageMessenger;

import java.util.Formatter;
import java.util.Locale;

/**
 * Coordinate class
 */
public class Coordinates {
    private float x;
    private double y;
    private ValidateField validateField;
    private IMessageMessenger messageMessenger;

    public Coordinates(float x, double y) {
        this.x=x;
        this.y=y;
    }
    public Coordinates(IMessageMessenger messageMessenger){
        validateField = new SpaceMarineValidateField();
        this.messageMessenger = messageMessenger;
    }

    public Coordinates getCoordinates(){
        return new Coordinates(x, y);
    }

    public void setX(float x) throws InvalidFieldException, NoMessException {
        if (validateField.validateCoordinateX(x)){
            this.x = x;
        }else {
            throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidCoordinatesX"));
        }
    }

    public void setY(double y) throws InvalidFieldException, NoMessException {
        if (validateField.validateCoordinateY(y)){
            this.y = y;
        }else {
            throw new InvalidFieldException(messageMessenger.getExceptionMess("invalidCoordinatesY"));
        }
    }

    public float getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        Formatter formatter = new Formatter();
        formatter.format("\"%s,%s\"",String.format(Locale.ROOT,"%.1f",x),String.format(Locale.ROOT,"%.2f",y));
        return formatter.toString();
    }
}

