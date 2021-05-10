package collectionManager;

import exceptions.*;
import spaceMarine.SpaceMarine;
import spaceMarine.SpaceMarinesGetter;
import spaceMarine.Weapon;

import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.stream.Stream;

/**
 * The interface of the class that manages the collection
 * @author  neocortex
 */
public interface CollectionManager {

   /**
    * @return collection type
    */
   Class getType();

   /**
    * @return length of collection
    */
   int getLength();

   /**
    * @param spaceMarine object of collection
    *
    */
   void addSpaceMarine(SpaceMarine spaceMarine);
   void addSpaceMarine(SpaceMarinesGetter spaceMarinesGetter);

   /**
    * Cleaning up a collection
    */
   void clear();

   /**
    * Remove an item from the collection by its id
    * @param id
    * @return
    */
   boolean removeById(Integer id);

   /**
    * save collection in file
    */
   void saveSpaceMarines();

   /**
    * @return collection initialization date
    */
   ZonedDateTime getInitDate();

   /**
    * add collection
    * @throws NoEvnVarException if the required environment variable is missing
    * @throws InvalidFieldException if an invalid object field is specified
    * @throws NoDataException if there is no collection data
    * @throws BrokenDataException if an error occurs in the data structure
    * @throws InvalidArgumentTypeException an invalid data type is specified for an argument
    */
   void addSpaceMarines() throws NoEvnVarException, InvalidFieldException, NoDataException, BrokenDataException, InvalidArgumentTypeException;

   /**
    * @return a Stream object of the collection of elements
    */
   Stream<SpaceMarine> getSpaceMarineStream();

   void setSpaceMarines(LinkedHashSet<SpaceMarine> hashSet);

}
