package spaceMarine;

import exceptions.InvalidFieldException;

public interface CoordinatesBuilder {

    void setX(float x) throws InvalidFieldException;
    void setY(double y) throws InvalidFieldException;
    Coordinates getCoordinates();
}
