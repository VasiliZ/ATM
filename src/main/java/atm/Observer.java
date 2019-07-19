package atm;

import java.util.List;

public interface Observer<T> {
    void logIn(List<T> data);
}
