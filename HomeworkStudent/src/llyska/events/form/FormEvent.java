package llyska.events.form;

/**
 * The class event arising on a form.
 *
 * Contains such commands as:
 * FORM_SAVE - save data on form;
 * FORM_CANCEL - clean data on form;
 *
 * @author Lyska Lyudmila
 *
 */
public class FormEvent {
    public static final int FORM_SAVE = 1;
    public static final int FORM_CANCEL = 2;

    private final int _command;

    /**
     * Constructor creating event.
     *
     * @param command for event (e.g. FORM_SAVE, FORM_CANCEL)
     */
    public FormEvent(int command) {
        _command = command;
    }

    /**
     * Check to event command equals command transmitted or not.
     *
     * @param command for checking
     * @return true - if commands are equal, false - if not
     */
    public boolean checkCommand(int command) {
        return (_command == command) ? true : false;
    }
}
