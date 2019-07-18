import pojo.ATMCard;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Home on 18.07.2019.
 */
public interface FileActions<T> {
    void openFile();
    T readFile();
    void readDataFromFile(T file) throws FileNotFoundException;
    void errorOpenFile(Throwable throwable);
    void writeDataToFile(ATMCard atmCard);


}
