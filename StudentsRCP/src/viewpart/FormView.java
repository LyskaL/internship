package viewpart;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.swt.SWT;
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

/**
 * The class is composite on which form located for information about student. Also, form has panel with buttons for
 * managing other components.
 *
 * @author Lyska Lyudmila
 */
public class FormView extends Composite {
    /** Stores a name of student **/
    private Text _nameText;

    /** Stores a group number **/
    private Text _numberGroupText;

    /** Stores a task done status **/
    private Button _checkButton;

    /** The New Button for creating a new table (to clean old data) **/
    private Button _newButton;

    /** The Save Button for saving data from form to table **/
    private Button _saveButton;

    /** The Delete Button for removing selection row from table **/
    private Button _deleteButton;

    /** The Cancel Button for cleaning data from form **/
    private Button _cancelButton;

    IWorkbenchWindow _window;

    /**
     * Constructor to create a panel with a table. Creates services, sets layouts and adds other components.
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
    }

    /**
     * Creates panel with text fields for entering data about a student.
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
        // _nameText.addKeyListener(new TextKeyListener());

        Label groupLabel = new Label(textPanel, SWT.NONE);
        groupLabel.setText("Group");
        groupLabel.setLayoutData(new GridData(SWT.FILL, SWT.HORIZONTAL, true, true));
        _numberGroupText = new Text(textPanel, SWT.BORDER | SWT.RIGHT);
        _numberGroupText.setLayoutData(new GridData(SWT.FILL, SWT.HORIZONTAL, true, true));
        // _numberGroupText.addKeyListener(new TextKeyListener());
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

        ActionContributionItem actionItem = new ActionContributionItem(new NewAction(_window));
        actionItem.fill(buttonsPanel);
        _newButton = (Button) actionItem.getWidget();
        _newButton.setText("New");
        _newButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        actionItem = new ActionContributionItem(new SaveAction(_window));
        actionItem.fill(buttonsPanel);
        _saveButton = (Button) actionItem.getWidget();
        _saveButton.setText("Save");
        _saveButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        actionItem = new ActionContributionItem(new DeleteAction(_window));
        actionItem.fill(buttonsPanel);
        _deleteButton = (Button) actionItem.getWidget();
        _deleteButton.setText("Delete");
        _deleteButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        actionItem = new ActionContributionItem(new CancelAction(_window));
        actionItem.fill(buttonsPanel);
        _cancelButton = (Button) actionItem.getWidget();
        _cancelButton.setText("Cancel");
    }

}
