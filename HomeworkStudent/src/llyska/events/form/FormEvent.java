package llyska.events.form;

public class FormEvent {
    public static final int FORM_SAVE = 1;
    public static final int FORM_CANCEL = 2;

    private final int _command;

    public FormEvent(int command) {
        _command = command;
    }

    public boolean checkCommand(int command) {
        return (_command == command) ? true : false;
    }
}
