package collectionManager;

import exceptions.*;
import spaceMarine.SpaceMarine;
import spaceMarine.SpaceMarinesGetter;

import java.time.ZonedDateTime;
import java.util.Collection;
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
   //void addSpaceMarine(SpaceMarinesGetter spaceMarinesGetter);

   /**
    * Cleaning up a collection
    */
   void clear();

   /**
    * Remove an item from the collection by its id
    * @param id
    * @return
    */
   void removeById(Integer id);

   /**
    * @return collection initialization date
    */
   ZonedDateTime getInitDate();

   /**
    * @return a Stream object of the collection of elements
    */
   Stream<SpaceMarine> getSpaceMarineStream();

   void setSpaceMarines(Collection<SpaceMarine> spaceMarines);

   void setNewCollection(Collection<SpaceMarine> spaceMarines);

}
