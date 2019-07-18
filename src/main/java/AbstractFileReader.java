/**
 * Created by Home on 18.07.2019.
 */
public abstract class AbstractFileReader<T> implements FileActions<T> {

    public void openFile() {
        try {
            T file = readFile();
            if (file != null) {
                readDataFromFile(file);
            }
        } catch (Exception e) {
            errorOpenFile(e);
        }
    }
}
