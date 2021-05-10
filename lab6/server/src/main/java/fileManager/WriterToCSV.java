package fileManager;

import log.Log;
import spaceMarine.SpaceMarine;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Locale;
import java.util.logging.Level;

/**
 * A class that implements the Writer interface
 */
public class WriterToCSV implements Writer {
    private final String fileName;

    /**
     * Constructor for WriterToCSV
     * @param fileName file name
     */
    public WriterToCSV(String fileName){
        Log.getLogger().info("FileManager init");
        this.fileName = fileName;
    }

    /**
     * The method that writes to the file
     * @param spaceMarines collection
     */
    public void write(Collection<? extends SpaceMarine> spaceMarines){
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(fileName)
                ){
            Log.getLogger().info("Writing csv string to csv file");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                String output = makeFile(spaceMarines);
                byte[] buffer = output.getBytes();
                bos.write(buffer);
                bos.writeTo(fileOutputStream);
                bos.close();
        }catch (IOException e){
            Log.getLogger().error( "EXCEPTION: ", e);
        }
    }

    /**
     * The method that generates the file
     * @param spaceMarines collection
     * @return String argument
     */
    private String makeFile(Collection<? extends SpaceMarine> spaceMarines){
        Log.getLogger().info("Making csv string");
        StringBuilder csv = new StringBuilder();
        csv.append("id,name,coordinates,creationDate,health,category,weaponType,meleeWeapon,chapter\n");
        for (SpaceMarine spaceMarine: spaceMarines) {
            csv.append(String.format(Locale.ROOT, "%d,%s,\"%f,%f\",%s,%d,%s,%s,%s,\"%s,%s\"", spaceMarine.getId(), spaceMarine.getName(),
                    spaceMarine.getCoordinates().getX(), spaceMarine.getCoordinates().getY(),
                    spaceMarine.getCreationDate(), spaceMarine.getHealth(), spaceMarine.getCategory(),
                    spaceMarine.getWeaponType(), spaceMarine.getMeleeWeapon(), spaceMarine.getChapter().getName(),
                    spaceMarine.getChapter().getWorld())).append('\n');

        }
        return csv.toString();
    }
}
