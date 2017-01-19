package llyska.interfaces;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class MenuView {
	private Menu _menu, _fileMenu, _editMenu, _helpMenu;
	private MenuItem _fileMenuHeader, _editMenuHeader, _helpMenuHeader;
	
	private MenuItem _fileNewFileItem, _fileOpenItem, 
					 _fileExitItem, _fileSaveItem, 
					 _helpGetHelpItem, _editDeleteItem;
	
	public MenuView(Decorations parent, int style) {
		_menu = new Menu(parent, style);
		
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

		_fileOpenItem = new MenuItem(_fileMenu, SWT.PUSH);
		_fileOpenItem.setText("&Open");
		
		new MenuItem(_fileMenu, SWT.SEPARATOR);

		_fileSaveItem = new MenuItem(_fileMenu, SWT.PUSH);
		_fileSaveItem.setText("&Save");
		
		new MenuItem(_fileMenu, SWT.SEPARATOR);
		
		_fileExitItem = new MenuItem(_fileMenu, SWT.PUSH);
		_fileExitItem.setText("E&xit");

	}

	private void setupEditMenu(Decorations parent) {
		_editMenuHeader = new MenuItem(_menu, SWT.CASCADE);
		_editMenuHeader.setText("&Edit");
		
		_editMenu = new Menu(parent, SWT.DROP_DOWN);
		_editMenuHeader.setMenu(_editMenu);
		
		_editDeleteItem = new MenuItem(_editMenu, SWT.PUSH);
		_editDeleteItem.setText("Delete");
		

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

}
