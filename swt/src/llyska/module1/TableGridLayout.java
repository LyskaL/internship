package llyska.module1;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class TableGridLayout {

Shell shell;
	
	public TableGridLayout() {
		Display display = new Display();
		shell = new Shell(display);
		shell.setText("Table layout");
		shell.setSize(500, 500);
		shell.setLayout(new GridLayout(1, true));
		
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
	    shell.setLayoutData(gridData);
	    
		createDialogArea(shell);
		
		shell.open();
		while (!shell.isDisposed()) {
		        if (!display.readAndDispatch()){
		               display.sleep();
		         }
		}
		display.dispose();
		
		
	}
	
	protected Control createDialogArea(Composite parent) 
    {
        final Composite area = new Composite(parent, SWT.NONE);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.marginWidth = 15;
        gridLayout.marginHeight = 10;
        area.setLayout(gridLayout);

        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        shell.setLayoutData(gridData);
        area.setLayoutData(gridData);

        createTopButtons(area);
        createTableViewer(area);
        return area;
    }
	
	private void createTableViewer(Composite area)
    {
        Table table = new Table(area, SWT.BORDER|SWT.V_SCROLL|SWT.FULL_SELECTION);
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        table.setLayoutData(gridData);

        table.setLinesVisible(true);
        table.setHeaderVisible(true);

        TableColumn column = new TableColumn(table, SWT.LEFT);
        column.setWidth(100);
        column.setText("Column 1");

        column = new TableColumn(table, SWT.LEFT);
        column.setWidth(200);
        column.setText("Column 2");
    }

    protected void createTopButtons(Composite parent) 
    {

        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout gridLayout = new GridLayout(2, false);
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        gridLayout.verticalSpacing = 0;
        gridLayout.horizontalSpacing = 0;
        composite.setLayout(gridLayout);

        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        composite.setLayoutData(gridData);

        gridData = new GridData(SWT.DEFAULT, SWT.FILL, false, false);

        Button pdfButton = new Button(composite, SWT.PUSH);
        pdfButton.setText("Create PDF");
        pdfButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                shell.close();
            }
        }); 

        pdfButton.setLayoutData(gridData);

        Button plotButton = new Button(composite, SWT.PUSH);
        plotButton.setText("Plot");
        plotButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                shell.close();
            }
        });  
        plotButton.setLayoutData(gridData);
    }

	public static void main(String[] args) {
		new TableGridLayout();
	}

}
