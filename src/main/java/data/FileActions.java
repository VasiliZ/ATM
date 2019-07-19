package data;

import java.io.FileNotFoundException;

public interface FileActions<T> {
    void openFile();
    T readFile();
    void readDataFromFile(T file) throws FileNotFoundException;
    void errorOpenFile(Throwable throwable);
    void writeDataToFile();


}
