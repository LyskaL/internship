package llyska.events;

public class ChangeStateButtonEvent {
    public static final int TABLE_SELECTED = 1;
    public static final int TABLE_EDITED = 1<<1;
    public static final int FORM_FILLED = 1<<2;

    private static ChangeStateButtonEvent _stateButtonEvent;
    private int _stateButton;

    static {
        _stateButtonEvent = new ChangeStateButtonEvent();
        System.out.println("Создан обьект ChangeStateButtonEvent");
    }

    private ChangeStateButtonEvent() {
       _stateButton = 0;
    }

    public static ChangeStateButtonEvent getInstance() {
        return _stateButtonEvent;
    }

    public int getStateButton() {
        return _stateButton;
    }

    public void setStateButton(int stateButton) {
        _stateButton = stateButton;
    }



}
