package llyska.events.table;

import llyska.events.state.ChangeStateEvent;

public interface TableEventListener {
    void tableEvent(ChangeStateEvent e);
}
