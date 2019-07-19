package atm;

import java.util.List;

public interface Subject<T> {
    void register(Observer observer);
    void notifyAllObServers(List<T> listData);
}
