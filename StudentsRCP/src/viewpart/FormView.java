package viewpart;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchWindow;

import actions.CancelAction;
import actions.DeleteAction;
import actions.NewAction;
import actions.SaveAction;
import entities.Student;
import events.form.FormEvent;
import events.form.FormEventListener;
import services.FormService;
import services.StateForm;
import services.StateService;
import services.TableService;
import services.TableServiceImp;

/**
 * The class is composite on which form located for information about student.
 * Also, form has panel with buttons for
 * managing other components.
 *
 * @author Lyska Lyudmila
 */
public class FormView extends Composite implements FormEventListener {
    /** Stores a name of student **/
    private Text _nameText;

    /** Stores a group number **/
    private Text _numberGroupText;

    /** Stores a task done status **/
    private Button _checkButton;

    IWorkbenchWindow _window;

    /** Service for handling event on form **/
    private final StateService _stateService = StateService.getInstance();

    /** Service for working with data in table **/
    private final TableService _tableService = TableServiceImp.getInstance();

    /** Service for handling event on form **/
    private final FormService _formService = FormService.getInstance();

    /**
     * Constructor to create a panel with a table.
     * Creates services, sets layouts and adds other components.
     *
     * @param parent on what form
     * @param style form
     */
    public FormView(Composite parent, int style, IWorkbenchWindow window) {
        super(parent, style);
        _window = window;

        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        setLayout(new GridLayout(1, true));

        createTextPanel();
        createButtonsPanel();

        _formService.addTableEventListener(this);
    }

    /**
     * Creates panel with text fields for entering data about a student.
     */
    private void createTextPanel() {
        Composite textPanel = new Composite(this, SWT.NONE);
        textPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        textPanel.setLayout(new GridLayout(2, true));

        createLabel(textPanel, "Name");
        _nameText = new Text(textPanel, SWT.BORDER | SWT.RIGHT);
        _nameText.setLayoutData(new GridData(SWT.FILL, SWT.HORIZONTAL, true, true));
        _nameText.addKeyListener(new TextKeyListener());

        createLabel(textPanel, "Group");
        _numberGroupText = new Text(textPanel, SWT.BORDER | SWT.RIGHT);
        _numberGroupText.setLayoutData(new GridData(SWT.FILL, SWT.HORIZONTAL, true, true));
        _numberGroupText.addKeyListener(new TextKeyListener());

        createCheckButtonPanel(textPanel);
    }

    /**
     * Creates a check button for choosing to student task done or not.
     *
     * @param textPanel a panel on that adds a check button
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
     * Creates buttons for management state a table and a form.
     */
    private void createButtonsPanel() {
        Composite buttonsPanel = new Composite(this, SWT.NONE);
        buttonsPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        buttonsPanel.setLayout(new GridLayout(4, true));

        getButtonFromAction(buttonsPanel, "New", new NewAction(_window));
        getButtonFromAction(buttonsPanel, "Save", new SaveAction());
        getButtonFromAction(buttonsPanel, "Delete", new DeleteAction(_window));
        getButtonFromAction(buttonsPanel, "Cancel", new CancelAction());
    }

    /**
     * The class handles event on pressing on key.
     *
     * @author Lyska Lyudmila
     */
    class TextKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {}

        /**
         * Sets application's state to StateForm.FILLED if all text fields are filled.
         * Disables this state in other cases.
         */
        @Override
        public void keyReleased(KeyEvent e) {
            StateForm state = StateForm.EMPTY;

            if (_nameText.getCharCount() > 0 && _numberGroupText.getCharCount() > 0) {
                state = StateForm.FILLED;
            }
            _stateService.setState(state);
            _stateService.runEvent();
        }
    }

    @Override
    public boolean setFocus() {
        return super.setFocus();
    }

    @Override
    public void formEvent(FormEvent e) {
        if (e.checkCommand(FormEvent.FORM_SAVE)) {
            _tableService.addStudent(
                    new Student(_nameText.getText(), _numberGroupText.getText(), _checkButton.getSelection()));
        }
        resetDataFromForm();
    }

    private void resetDataFromForm() {
        _nameText.setText("");
        _numberGroupText.setText("");
        _checkButton.setSelection(false);

        _stateService.setState(StateForm.EMPTY);
        _stateService.runEvent();
    }

    private Button getButtonFromAction(Composite parent, String nameButton, Action action) {
        ActionContributionItem actionItem = new ActionContributionItem(action);
        actionItem.fill(parent);
        Button button = (Button) actionItem.getWidget();
        button.setText(nameButton);
        button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        return button;
    }

    private Label createLabel(Composite parent, String text) {
        Label label = new Label(parent, SWT.NONE);
        label.setText(text);
        label.setLayoutData(new GridData(SWT.FILL, SWT.HORIZONTAL, true, true));
        return label;
    }
}
