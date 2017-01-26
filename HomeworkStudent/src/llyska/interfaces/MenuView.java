package llyska.interfaces;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import llyska.events.state.ChangeStateEvent;
import llyska.events.state.ChangeStateEventListener;
import llyska.listeners.CancelButtonListener;
import llyska.listeners.DeleteButtonListener;
import llyska.listeners.NewButtonListener;
import llyska.listeners.SaveButtonListener;
import llyska.services.StateService;

public class MenuView implements ChangeStateEventListener {
    private final Menu _menu;
    private Menu _fileMenu;
    private Menu _editMenu;
    private Menu _helpMenu;
    private MenuItem _fileMenuHeader, _editMenuHeader, _helpMenuHeader;
    private MenuItem _fileNewFileItem, _fileExitItem, _helpGetHelpItem,
                     _editSaveItem, _editDeleteItem,  _editCancelItem;

    private final StateService _stateService;

    public MenuView(Decorations parent, int style) {
        _menu = new Menu(parent, style);
        _stateService = StateService.getInstance();
        _stateService.addDataEventListener(this);
        setupFileMenu(parent);
        setupEditMenu(parent);
        setupHelpMenu(parent);
    }

    private void setupFileMenu(Decorations parent) {

        _fileMenuHeader = new MenuItem(_menu, SWT.CASCADE);
        _fileMenuHeader.setText("&File");

        _fileMenu = new Menu(parent, SWT.DROP_DOWN);
        _fileMenuHeader.setMenu(_fileMenu);

        _fileNewFileItem = new MenuItem(_fileMenu, SWT.PUSH);
        _fileNewFileItem.setText("&New");
        _fileNewFileItem.addListener(SWT.Selection, new NewButtonListener());

        new MenuItem(_fileMenu, SWT.SEPARATOR);

        _fileExitItem = new MenuItem(_fileMenu, SWT.PUSH);
        _fileExitItem.setText("E&xit");

    }

    private void setupEditMenu(Decorations parent) {
        _editMenuHeader = new MenuItem(_menu, SWT.CASCADE);
        _editMenuHeader.setText("&Edit");

        _editMenu = new Menu(parent, SWT.DROP_DOWN);
        _editMenuHeader.setMenu(_editMenu);

        _editSaveItem = new MenuItem(_editMenu, SWT.PUSH);
        _editSaveItem.setText("Save");
        _editSaveItem.setEnabled(false);
        _editSaveItem.addListener(SWT.Selection, new SaveButtonListener());

        _editDeleteItem = new MenuItem(_editMenu, SWT.PUSH);
        _editDeleteItem.setText("Delete");
        _editDeleteItem.setEnabled(false);
        _editDeleteItem.addListener(SWT.Selection, new DeleteButtonListener());

        new MenuItem(_editMenu, SWT.SEPARATOR);

        _editCancelItem = new MenuItem(_editMenu, SWT.PUSH);
        _editCancelItem.setText("Cancel");
        _editCancelItem.addListener(SWT.Selection, new CancelButtonListener());
    }

    private void setupHelpMenu(Decorations parent) {
        _helpMenuHeader = new MenuItem(_menu, SWT.CASCADE);
        _helpMenuHeader.setText("&Help");

        _helpMenu = new Menu(parent, SWT.DROP_DOWN);
        _helpMenuHeader.setMenu(_helpMenu);

        _helpGetHelpItem = new MenuItem(_helpMenu, SWT.PUSH);
        _helpGetHelpItem.setText("&Get Help");
    }

    public Menu getMenu() {
        return _menu;
    }

    @Override
    public void handleEvent(ChangeStateEvent e) {
        _editDeleteItem.setEnabled(e.checkState(ChangeStateEvent.TABLE_SELECTED));
        _editSaveItem.setEnabled(e.checkState(ChangeStateEvent.FORM_FILLED));
    }

}
