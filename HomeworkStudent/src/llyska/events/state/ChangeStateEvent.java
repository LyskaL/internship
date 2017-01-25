package llyska.events.state;

public class ChangeStateEvent {
    public static final int TABLE_SELECTED = 1;
    public static final int TABLE_EDITED = 1<<1;
    public static final int FORM_FILLED = 1<<2;

    private final int _state;

    public ChangeStateEvent(int state) {
        super();
        _state = state;
    }

    public boolean checkState(int state) {
        return (_state & state) != 0;
    }

}
