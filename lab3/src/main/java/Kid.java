public final class Kid extends Human implements ISit, IShowable{

    public Kid (String name, String gender){
        super(name, gender);
        System.out.println("Human - " + name + " was successfully created.");
    }

    public void shakeHead(){
        System.out.println(getName() + " shook his head.");
    }

    @Override
    public void sition(Father_Bed bed) {
        System.out.println(getName() + " sat on the " + bed.getType() + '.');
    }

    @Override
    public void lookAt(FrekenBok hildur) {
        System.out.println(getName() + " looked at " + hildur.lookAt() + '.');
    }

    @Override
    public String toString() {
        return getName();
    }

}
