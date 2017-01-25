package llyska.events;

import java.util.Set;

public interface ChangeStateButtonEventGenerator {
    void addDataEventListener(ChangeStateButtonEventListener listener);
    void removeDataEventListener(ChangeStateButtonEventListener listener);
    Set<ChangeStateButtonEventListener> getListeners();
}
