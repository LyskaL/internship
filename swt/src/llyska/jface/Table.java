package llyska.jface;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;

/**
 * TableViewer: Hide full selection
 *
 */
public class Table {

    public class MyModel {
        public int counter;

        public MyModel(int counter) {
            this.counter = counter;
        }

        @Override
        public String toString() {
            return "Item " + this.counter;
        }
    }

    public Table(Shell shell) {
        final TableViewer v = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
        v.setContentProvider(ArrayContentProvider.getInstance());

        TableColumn column = new TableColumn(v.getTable(), SWT.NONE);
        column.setWidth(100);
        column.setText("Column 1");
        TableViewerColumn viewerColumn1 = new TableViewerColumn(v, column);
        viewerColumn1.setLabelProvider(new ColumnLabelProvider());
        viewerColumn1.setEditingSupport(new EditColumns(v));

        column = new TableColumn(v.getTable(), SWT.NONE);
        column.setWidth(100);
        column.setText("Column 2");
        TableViewerColumn viewerColumn2 = new TableViewerColumn(v, column);
        viewerColumn2.setLabelProvider(new ColumnLabelProvider());
        viewerColumn2.setEditingSupport(new EditColumns(v));

        MyModel[] model = createModel();
        v.setInput(model);
        v.getTable().setLinesVisible(true);
        v.getTable().setHeaderVisible(true);

        v.getTable().addListener(SWT.EraseItem, new Listener() {

            @Override
            public void handleEvent(Event event) {
                event.detail &= ~SWT.SELECTED;
            }
        });

    }

    private MyModel[] createModel() {
        MyModel[] elements = new MyModel[10];

        for (int i = 0; i < 10; i++) {
            elements[i] = new MyModel(i);
        }

        return elements;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new FillLayout());

        final int width = 500;
        final int height = 400;
        Monitor monitor = display.getPrimaryMonitor();
        int x = (monitor.getBounds().width / 2) - width / 2;
        int y = (monitor.getBounds().height / 2) - height / 2;
        shell.setBounds(x, y, width, height);

        new Table(shell);
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();

    }

    private class EditColumns extends EditingSupport {

        public EditColumns(ColumnViewer viewer) {
            super(viewer);
        }

        @Override
        protected CellEditor getCellEditor(Object element) {
            return new TextCellEditor((Composite) getViewer().getControl());
        }

        @Override
        protected boolean canEdit(Object element) {
            return true;
        }

        @Override
        protected Object getValue(Object element) {
            return ((MyModel) element).counter + "";
        }

        @Override
        protected void setValue(Object element, Object value) {
            ((MyModel) element).counter = Integer.parseInt(value.toString());
            getViewer().update(element, null);
        }

    }

}