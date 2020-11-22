public class Main {
    public static void main(String[] args) {
        Kid kid = new Kid("Malysh", "male");
        FrekenBok hildur = new FrekenBok("Freken Bok", "female", "Hildur");
        FrekenBok frida = new FrekenBok("Freken Bok", "female", "Frida");

        Father_Bed bed = new Father_Bed("father's bed");
        Door door = new Door("door");
        Commode commode = new Commode("chest of drawers");
        Chairs chairs = new Chairs("chairs");
        Bookcase bookcase = new Bookcase("bookcase");
        Table table = new Table("table");
        Barricade barricade = new Barricade("barricade");
        kid.changeStatus(Status.MISUNDERSTANDING);
        kid.sition(bed);
        hildur.changeStatus(Status.SCARED);
        kid.lookAt(hildur);
        kid.shakeHead();
        hildur.noWantHear(frida);
        hildur.move(door);
        commode.follow(door, barricade);
        table.follow(door, barricade);
        chairs.follow(door, barricade);
        bookcase.follow(door, barricade);
        barricade.formed(barricade);
        /*System.out.println(hildur.getFname());*/
    }
}
