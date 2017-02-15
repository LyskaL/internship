package events;

import java.util.Set;

public interface GroupChangeGenerator {
    void addDataEventListener(GroupChangeListener listener);
    void removeDataEventListener(GroupChangeListener listener);
    Set<GroupChangeListener> getListeners();
}
