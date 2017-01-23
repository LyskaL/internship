package llyska.jface.tableexample;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public class TableView extends Composite {
    private TableViewer viewer;
    private final Group _group = new Group(30);

    private final Image CHECKED = new Image(Constance.DISPLAY, "./src/resources/CHECKED.png");
    private final Image UNCHECKED = new Image(Constance.DISPLAY, "./src/resources/CHECKED.png");

    public TableView(Composite parent, int style) {
        super(parent, style);
        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        setLayout(new FillLayout());

        createTable();

    }

    private void createTable() {
        /*
         * viewer = new TableViewer(this, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
         * viewer.setContentProvider(ArrayContentProvider.getInstance());
         */

        viewer = new TableViewer(this, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

        // create the columns
        // not yet implemented
        createColumns(viewer);

        // make lines and header visible
        final Table table = viewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        viewer.setContentProvider(ArrayContentProvider.getInstance());
        viewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                    IStructuredSelection selection = viewer.getStructuredSelection();
                    Object firstElement = selection.getFirstElement();
                    // do something with it
                    System.out.println("отработал: addSelectionChangedListener(new ISelectionChangedListener()");
            }
    });

    }

    private void createColumns(TableViewer viewer2) {
     // create a column for the first name
        TableViewerColumn colFirstName = new TableViewerColumn(viewer, SWT.NONE);
        colFirstName.getColumn().setWidth(200);
        colFirstName.getColumn().setText("Firstname");
        colFirstName.setLabelProvider(new ColumnLabelProvider() {
                @Override
                public String getText(Object element) {
                        Person p = (Person) element;
                        return p.getFirstName();
                }
        });

        // create more text columns if required...

        // create column for married property of Person
        // uses getImage() method instead of getText()
        // CHECKED and UNCHECK are fields of type Image

        TableViewerColumn colMarried = new TableViewerColumn(viewer, SWT.NONE);
        colMarried.getColumn().setWidth(200);
        colMarried.getColumn().setText("Married");
        colMarried.setLabelProvider(new ColumnLabelProvider() {
        @Override
        public String getText(Object element) {
          return null;  // no string representation, we only want to display the image
        }

        @Override
        public Image getImage(Object element) {
                if (((Person) element).isMarried()) {
                        return CHECKED;
                }
                return UNCHECKED;
                }
        });
    }
}
