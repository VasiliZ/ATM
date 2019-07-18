import java.util.List;

/**
 * Created by Home on 18.07.2019.
 */
public interface Subject<T> {
    void register(Observer observer);
    void notifyAllObServers(List<T> listData);
}
