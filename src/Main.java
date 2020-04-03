import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

public class Main {
    private static File file = null;
    public static Asked asked = new Asked();
    private static boolean k;
    public static void main(String[] args) throws JAXBException, IOException {
        try {
             file = new File(System.getenv("hleb"));
        } catch (NullPointerException e){
            System.out.println("Создайте переменную окружения(hleb=\"/home/s286535/ww\"\n" +
                    "export hleb)");
        }
        try {
            if (!file.exists()) throw new FileNotFoundException();
        } catch (FileNotFoundException e){
            System.out.println("Файла по указанному пути не существует");
            if (!k) System.exit(1);
            else return;
        } catch (NullPointerException e){}
        try {
            if (!file.canRead() || !file.canWrite()) throw new SecurityException();
        } catch (SecurityException se){
            System.out.println("Файл защищен от чтения и/или записи. Для программы нужны оба разрешения");
            if (!k) System.exit(1);
            else return;
        } catch (NullPointerException e){}
        try {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    System.out.println("\nВыход...");
                }
            });
            asked.app(file);
        } catch (NoSuchElementException e) {
        } catch (NullPointerException e){}
          catch (FileNotFoundException e){}
    }
}


