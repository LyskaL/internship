package llyska.events.state;

import java.util.Set;

public interface ChangeStateEventGenerator {
    void addDataEventListener(ChangeStateEventListener listener);
    void removeDataEventListener(ChangeStateEventListener listener);
    Set<ChangeStateEventListener> getListeners();
}
