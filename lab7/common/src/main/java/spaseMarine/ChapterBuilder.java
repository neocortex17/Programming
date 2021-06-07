package spaceMarine;

import exceptions.InvalidFieldException;

public interface ChapterBuilder {
    void setName(String name) throws InvalidFieldException;
    void setWorld(String world);
    Chapter getChapter();
}
