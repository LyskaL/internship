package llyska.jface.tableexample;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class View {
    private TableViewer viewer;
    // static fields to hold the images
    private static final Image CHECKED = Constance.CHECKED;
    private static final Image UNCHECKED = Constance.UNCHECKED;

    public void createPartControl(Composite parent) {
        GridLayout layout = new GridLayout(2, false);
        parent.setLayout(layout);
        Label searchLabel = new Label(parent, SWT.NONE);
        searchLabel.setText("Search: ");
        final Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
        searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
        createViewer(parent);
    }

    private void createViewer(Composite parent) {
        viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        createColumns(parent, viewer);
        final Table table = viewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        viewer.setContentProvider(new ArrayContentProvider());
        // get the content for the viewer, setInput will call getElements in the
        // contentProvider
        viewer.setInput(ModelProvider.INSTANCE.getPersons());
        // make the selection available to other views
        //getSite().setSelectionProvider(viewer);
        // set the sorter for the table

        // define layout for the viewer
        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.horizontalSpan = 2;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        viewer.getControl().setLayoutData(gridData);
    }

    public TableViewer getViewer() {
        return viewer;
    }

    // create the columns for the table
    private void createColumns(final Composite parent, final TableViewer viewer) {
        String[] titles = { "First name", "Last name", "Gender", "Married" };
        int[] bounds = { 100, 100, 100, 100 };

        // first column is for the first name
        TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
        col.setEditingSupport(new FirstNameEditingSupport(viewer));
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                Person p = (Person) element;
                System.out.println("отработал: p.getFirstName()");
                return p.getFirstName();
            }
        });

        // second column is for the last name
        col = createTableViewerColumn(titles[1], bounds[1], 1);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                Person p = (Person) element;
                System.out.println("отработал: p.getLastName()");
                return p.getLastName();
            }
        });

        // now the gender
        col = createTableViewerColumn(titles[2], bounds[2], 2);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                Person p = (Person) element;
                System.out.println("отработал: p.getGender()");
                return p.getGender();
            }
        });

        // now the status married
        col = createTableViewerColumn(titles[3], bounds[3], 3);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return null;
            }

            @Override
            public Image getImage(Object element) {
                if (((Person) element).isMarried()) {
                    System.out.println("отработал: колонка 4 CHECKED");
                    return CHECKED;
                } else {
                    System.out.println("отработал: колонка 4 UNCHECKED");
                    return UNCHECKED;
                }
            }
        });

    }

    private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
        TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
        TableColumn column = viewerColumn.getColumn();
        column.setText(title);
        column.setWidth(bound);
        column.setResizable(true);
        column.setMoveable(true);
        return viewerColumn;
    }

    public void setFocus() {
        viewer.getControl().setFocus();
    }
}