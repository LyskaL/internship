package llyska.jface;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;

public class TestTableViewer {

    TableViewer viewer;

    public TestTableViewer() {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("JFace homework log");
        shell.setLayout(new GridLayout(1, true));

        final int width = 600;
        final int height = 400;
        Monitor monitor = display.getPrimaryMonitor();
        int x = (monitor.getBounds().width / 2) - width / 2;
        int y = (monitor.getBounds().height / 2) - height / 2;
        shell.setBounds(x, y, width, height);

        Composite com = new Composite(shell, SWT.BORDER);
        com.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        com.setLayout(new FillLayout());

        createTable(com);

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }

    private void createTable(Composite com) {
        viewer = new TableViewer(com, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        viewer.setContentProvider(ArrayContentProvider.getInstance());

        String[] titles = { "First name", "Last name", "Gender", "Married" };
        int[] bounds = { 100, 100, 100, 100 };

        // first column is for the first name
        for (String title : titles) {
            TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
            col.setLabelProvider(new ColumnLabelProvider() {
                @Override
                public String getText(Object element) {
                    return "4363";
                }
            });
        }

        viewer.getTable().setLinesVisible(true);
        viewer.getTable().setHeaderVisible(true);
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

    public static void main(String[] args) {
        new TestTableViewer();
    }

}
