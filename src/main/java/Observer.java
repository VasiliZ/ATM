import java.util.List;

/**
 * Created by Home on 18.07.2019.
 */
interface Observer<T> {
    void logIn(List<T> data);
}
