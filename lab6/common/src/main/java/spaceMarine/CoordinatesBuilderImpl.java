package spaceMarine;

import exceptions.InvalidFieldException;

public class CoordinatesBuilderImpl implements CoordinatesBuilder{
    private float x;
    private double y;
    private final ValidateField validateField;

    public CoordinatesBuilderImpl(){
        validateField = new SpaceMarineValidateField();
    }

    @Override
    public void setX(float x) throws InvalidFieldException {
            if (validateField.validateCoordinateX(x)) {
                this.x = x;
            }else{
                throw new InvalidFieldException();
            }
    }

    @Override
    public void setY(double y) throws InvalidFieldException {
        if (validateField.validateCoordinateY(y)) {
            this.y = y;
        }else{
            throw new InvalidFieldException();
        }
    }

    @Override
    public Coordinates getCoordinates() {
        return new Coordinates(x,y);
    }
}
