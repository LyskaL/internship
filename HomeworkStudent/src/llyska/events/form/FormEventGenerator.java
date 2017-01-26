package llyska.events.form;

import java.util.Set;

public interface FormEventGenerator {
    void addTableEventListener(FormEventListener listener);
    void removeTableEventListener(FormEventListener listener);
    Set<FormEventListener> getListeners();
}
