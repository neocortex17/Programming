package spaceMarine;

import exceptions.InvalidFieldException;

public class ChapterBuilderImpl implements ChapterBuilder{
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String world; //Поле может быть null
    private final ValidateField validateField;

    public ChapterBuilderImpl(){
        validateField = new SpaceMarineValidateField();
    }

    @Override
    public void setName(String name) throws InvalidFieldException {
        if (validateField.validateChapterName(name)){
            this.name = name;
        }else {
            throw new InvalidFieldException();
        }
    }

    @Override
    public void setWorld(String world) {
            this.world = world;
    }

    @Override
    public Chapter getChapter() {
        return new Chapter(name, world);
    }
}
