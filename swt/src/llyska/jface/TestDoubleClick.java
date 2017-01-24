package llyska.jface;
import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

/**
 * Editor Activation on DoubleClick instead of single.
 *
 * @author Tom Schindl <tom.schindl@bestsolution.at>
 *
 */
public class TestDoubleClick {

  private class MyEditingSupport extends EditingSupport {

    private boolean enabled;

    public void setEnabled(boolean enabled) {
      this.enabled = enabled;
    }

    public MyEditingSupport(ColumnViewer viewer) {
      super(viewer);
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
      return new TextCellEditor((Composite) getViewer().getControl());
    }

    @Override
    protected boolean canEdit(Object element) {
      return enabled;
    }

    @Override
    protected Object getValue(Object element) {
      return ((MyModel) element).counter + "";
    }

    @Override
    protected void setValue(Object element, Object value) {
      getViewer().update(element, null);
    }

  }

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

  public TestDoubleClick(Shell shell) {
    final Table table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
    final TableViewer v = new TableViewer(table);
    final MyEditingSupport editingSupport = new MyEditingSupport(v);

    table.addListener(SWT.MouseDown, new Listener() {

      @Override
      public void handleEvent(Event event) {
        editingSupport.setEnabled(false);
      }

    });

    table.addListener(SWT.MouseDoubleClick, new Listener() {

      @Override
      public void handleEvent(Event event) {
        editingSupport.setEnabled(true);
      }

    });

    TableViewerColumn viewerColumn = new TableViewerColumn(v, SWT.NONE);
    viewerColumn.getColumn().setWidth(200);
    viewerColumn.setLabelProvider(new ColumnLabelProvider());
    viewerColumn.setEditingSupport(editingSupport);

    v.setContentProvider(ArrayContentProvider.getInstance());

    v.setInput(createModel());
    v.getTable().setLinesVisible(true);
  }

  private List<MyModel> createModel() {
    List<MyModel> elements = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      elements.add(new MyModel(i));
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
    new TestDoubleClick(shell);
    shell.open();

    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
    }
    }
    display.dispose();
  }

}