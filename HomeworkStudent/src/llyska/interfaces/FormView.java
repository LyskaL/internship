package llyska.interfaces;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import llyska.events.ChangeStateButtonEvent;
import llyska.events.ChangeStateButtonEventListener;
import llyska.listeners.CancelButtonListener;
import llyska.listeners.DeleteButtonListener;
import llyska.listeners.NewButtonListener;
import llyska.listeners.SaveButtonListener;
import llyska.services.StateButtonService;

public class FormView extends Composite  implements ChangeStateButtonEventListener {
    private Text _nameText;
    private Text _numberGroupText;
    private Button _checkButton;

    private Button _newButton;
    private Button _saveButton;
    private Button _deleteButton;
    private Button _cancelButton;

    private final StateButtonService _stateButtonService;

    public FormView(Composite parent, int style) {
        super(parent, style);

        _stateButtonService = StateButtonService.getInstance();
        _stateButtonService.addDataEventListener(this);

        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        setLayout(new GridLayout(1, true));

        createTextPanel();
        createButtonsPanel();
    }

    private void createCheckButtonPanel(Composite textPanel ) {
        Composite checkPanel = new Composite(textPanel, SWT.NONE);
        checkPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
        checkPanel.setLayout(new GridLayout(2, true));

        Label swtTaskLabel = new Label(checkPanel, SWT.NONE);
        swtTaskLabel.setText("SWT task done");
        swtTaskLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true));

        _checkButton = new Button(checkPanel, SWT.CHECK);
        _checkButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, true));
    }

    private void createButtonsPanel() {
        Composite buttonsPanel = new Composite(this, SWT.NONE);
        buttonsPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        buttonsPanel.setLayout(new GridLayout(4, true));

        _newButton = new Button(buttonsPanel, SWT.NONE);
        _newButton.setText("New");
        _newButton.setEnabled(false);
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

    private void createTextPanel() {
        Composite textPanel = new Composite(this, SWT.NONE);
        textPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        textPanel.setLayout(new GridLayout(2, true));

        Label nameLabel = new Label(textPanel, SWT.NONE);
        nameLabel.setText("Name");
        nameLabel.setLayoutData(new GridData(SWT.FILL, SWT.HORIZONTAL, true, true));
        _nameText = new Text(textPanel, SWT.BORDER);
        _nameText.setLayoutData(new GridData(SWT.FILL, SWT.HORIZONTAL, true, true));

        Label groupLabel = new Label(textPanel, SWT.NONE);
        groupLabel.setText("Group");
        groupLabel.setLayoutData(new GridData(SWT.FILL, SWT.HORIZONTAL, true, true));
        _numberGroupText = new Text(textPanel, SWT.BORDER);
        _numberGroupText.setLayoutData(new GridData(SWT.FILL, SWT.HORIZONTAL, true, true));
        createCheckButtonPanel(textPanel);
    }

    @Override
    public void handleEvent(ChangeStateButtonEvent e) {
        if ((e.getStateButton() & ChangeStateButtonEvent.TABLE_SELECTED) != 0) {
            _deleteButton.setEnabled(true);
        } else {
            _deleteButton.setEnabled(false);
        }

        if ((e.getStateButton() & ChangeStateButtonEvent.TABLE_EDITED) != 0) {
            _saveButton.setEnabled(true);
        } else {
            _saveButton.setEnabled(false);
        }

        if ((e.getStateButton() & ChangeStateButtonEvent.FORM_FILLED) != 0) {
            _newButton.setEnabled(true);
        } else {
            _newButton.setEnabled(false);
        }
    }

}