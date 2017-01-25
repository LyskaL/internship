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

import llyska.events.state.ChangeStateEvent;
import llyska.events.state.ChangeStateEventListener;
import llyska.listeners.CancelButtonListener;
import llyska.listeners.DeleteButtonListener;
import llyska.listeners.NewButtonListener;
import llyska.listeners.SaveButtonListener;
import llyska.services.StateService;

public class FormView extends Composite implements ChangeStateEventListener {
    private Text _nameText;
    private Text _numberGroupText;
    private Button _checkButton;

    private Button _newButton;
    private Button _saveButton;
    private Button _deleteButton;
    private Button _cancelButton;

    private final StateService _stateService;

    public FormView(Composite parent, int style) {
        super(parent, style);

        _stateService = StateService.getInstance();
        _stateService.addDataEventListener(this);

        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        setLayout(new GridLayout(1, true));

        createTextPanel();
        createButtonsPanel();
    }

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

    private void createButtonsPanel() {
        Composite buttonsPanel = new Composite(this, SWT.NONE);
        buttonsPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        buttonsPanel.setLayout(new GridLayout(4, true));

        _newButton = new Button(buttonsPanel, SWT.NONE);
        _newButton.setText("New");
        _newButton.setEnabled(false);
        _newButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        _newButton.addListener(SWT.Selection, new NewButtonListener(this));

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
        _cancelButton.addListener(SWT.Selection, new CancelButtonListener(this));
    }

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

    @Override
    public void handleEvent(ChangeStateEvent e) {
        _deleteButton.setEnabled(e.checkState(ChangeStateEvent.TABLE_SELECTED));
        _saveButton.setEnabled(e.checkState(ChangeStateEvent.TABLE_EDITED));
        _newButton.setEnabled(e.checkState(ChangeStateEvent.FORM_FILLED));
    }

    class TextKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
        }

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

    public Text getNameField() {
        return _nameText;
    }

    public Text getNumberField() {
        return _numberGroupText;
    }

    public Button getCheckButton() {
        return _checkButton;
    }
}