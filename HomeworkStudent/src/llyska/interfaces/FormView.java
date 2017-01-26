package llyska.interfaces;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import llyska.entities.Student;
import llyska.events.form.FormEvent;
import llyska.events.form.FormEventListener;
import llyska.events.state.ChangeStateEvent;
import llyska.events.state.ChangeStateEventListener;
import llyska.listeners.CancelButtonListener;
import llyska.listeners.DeleteButtonListener;
import llyska.listeners.NewButtonListener;
import llyska.listeners.SaveButtonListener;
import llyska.services.FormEventService;
import llyska.services.StateService;
import llyska.services.TableService;
import llyska.util.Constants;

/**
 * The class is composite on which located form for fill information about student.
 * Also, form has panel with buttons for management other components.
 *
 * The class implements interfaces for tracking events a change state and form event.
 *
 * @author Lyska Lyudmila
 *
 */
public class FormView extends Composite implements ChangeStateEventListener, FormEventListener {
    /** The field for fill name of student **/
    private Text _nameText;
    /** The field for fill number of group **/
    private Text _numberGroupText;
    /** The check button for choosing student done task or not **/
    private Button _checkButton;
    /** The New Button for create new table (clean old data) **/
    private Button _newButton;
    /** The Save Button for save data from form to table **/
    private Button _saveButton;
    /** The Delete Button for removing selection row from table **/
    private Button _deleteButton;
    /** The Cancel Button for cleaning data from form **/
    private Button _cancelButton;

    /** Service for changing state buttons on form and menu panel **/
    private final StateService _stateService = StateService.getInstance();
    /** Service for handling event on form **/
    private final FormEventService _formService = FormEventService.getInstance();
    /** Service for working data in table **/
    private final TableService _tableService = Constants.TABLE_SERVICE;

    /**
     * Constructor this class.
     * Creates services, sets layouts and adds other components.
     *
     * @param parent on what form
     * @param style form
     */
    public FormView(Composite parent, int style) {
        super(parent, style);

        _formService.addTableEventListener(this);
        _stateService.addDataEventListener(this);

        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        setLayout(new GridLayout(1, true));

        createTextPanel();
        createButtonsPanel();
    }
    /**
     * Creates panel with text fields for entering data about student.
     */
    private void createTextPanel() {
        Composite textPanel = new Composite(this, SWT.NONE);
        textPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        textPanel.setLayout(new GridLayout(2, true));

        Label nameLabel = new Label(textPanel, SWT.NONE);
        nameLabel.setText("Name");
        nameLabel.setLayoutData(new GridData(SWT.FILL, SWT.HORIZONTAL, true, true));
        _nameText = new Text(textPanel, SWT.BORDER | SWT.RIGHT);
        _nameText.setLayoutData(new GridData(SWT.FILL, SWT.HORIZONTAL, true, true));
        _nameText.addKeyListener(new TextKeyListener());

        Label groupLabel = new Label(textPanel, SWT.NONE);
        groupLabel.setText("Group");
        groupLabel.setLayoutData(new GridData(SWT.FILL, SWT.HORIZONTAL, true, true));
        _numberGroupText = new Text(textPanel, SWT.BORDER | SWT.RIGHT);
        _numberGroupText.setLayoutData(new GridData(SWT.FILL, SWT.HORIZONTAL, true, true));
        _numberGroupText.addKeyListener(new TextKeyListener());
        createCheckButtonPanel(textPanel);
    }
    /**
     * Creates check button for choosing to student task done or not.
     *
     * @param textPanel panel on that adds check button
     */
    private void createCheckButtonPanel(Composite textPanel) {
        Composite checkPanel = new Composite(textPanel, SWT.NONE);
        checkPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
        checkPanel.setLayout(new GridLayout(2, true));

        Label swtTaskLabel = new Label(checkPanel, SWT.NONE);
        swtTaskLabel.setText("SWT task done");
        swtTaskLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true));

        _checkButton = new Button(checkPanel, SWT.CHECK);
        _checkButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, true));
    }

    /**
     * Creates buttons for management other components.
     */
    private void createButtonsPanel() {
        Composite buttonsPanel = new Composite(this, SWT.NONE);
        buttonsPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        buttonsPanel.setLayout(new GridLayout(4, true));

        _newButton = new Button(buttonsPanel, SWT.NONE);
        _newButton.setText("New");
        _newButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        _newButton.addListener(SWT.Selection, new NewButtonListener());

        _saveButton = new Button(buttonsPanel, SWT.NONE);
        _saveButton.setText("Save");
        _saveButton.setEnabled(false);
        _saveButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        _saveButton.addListener(SWT.Selection, new SaveButtonListener());

        _deleteButton = new Button(buttonsPanel, SWT.NONE);
        _deleteButton.setText("Delete");
        _deleteButton.setEnabled(false);
        _deleteButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        _deleteButton.addListener(SWT.Selection, new DeleteButtonListener());

        _cancelButton = new Button(buttonsPanel, SWT.NONE);
        _cancelButton.setText("Cancel");
        _cancelButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        _cancelButton.addListener(SWT.Selection, new CancelButtonListener());
    }

    @Override
    public void handleEvent(ChangeStateEvent e) {
        _deleteButton.setEnabled(e.checkState(ChangeStateEvent.TABLE_SELECTED));
        _saveButton.setEnabled(e.checkState(ChangeStateEvent.FORM_FILLED));
    }

    /**
     * The class handles event on pressing on key.
     *
     * @author Lyska Lyudmila
     *
     */
    class TextKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
        }

        /**
         *  If filled all text fields to say StateService enabled save of button. Disable it if not.
         */
        @Override
        public void keyReleased(KeyEvent e) {
            if (_nameText.getCharCount() > 0 && _numberGroupText.getCharCount() > 0) {
                _stateService.enableState(ChangeStateEvent.FORM_FILLED);
            } else {
                _stateService.disableState(ChangeStateEvent.FORM_FILLED);
            }
            _stateService.runEvent();
        }
    }

    /**
     * Depending on the command in event to handle it.
     *
     * If command is FORM_SAVE: data from form save to table.
     * If command is FORM_CANCEL: clean data on form.
     */
    @Override
    public void formEvent(FormEvent e) {
        if(e.checkCommand(FormEvent.FORM_SAVE)) {
            Student student = new Student(_nameText.getText(),_numberGroupText.getText(),_checkButton.getSelection());
            _tableService.addStudent(student);
        }
        _nameText.setText("");
        _numberGroupText.setText("");
        _checkButton.setSelection(false);

        _stateService.disableState(ChangeStateEvent.FORM_FILLED);
        _stateService.runEvent();
    }
}