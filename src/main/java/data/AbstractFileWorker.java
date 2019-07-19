package data;

public abstract class AbstractFileWorker<T> implements FileActions<T> {

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
