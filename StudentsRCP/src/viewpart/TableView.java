package viewpart;

import java.util.EventObject;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TableViewerFocusCellManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbenchPartSite;

import events.table.TableEventListener;
import services.TableService;
import services.TableServiceImp;
import table.editors.CheckBoxEditingSupport;
import table.editors.NameEditingSupport;
import table.editors.NumberEditingSupport;
import table.providers.CheckButtonProvider;
import table.providers.ModelProvider;
import table.providers.NameProvider;
import table.providers.NumberGroupProvider;

public class TableView implements TableEventListener {
    /** Table with data about group **/
    private TableViewer _viewer;

    /** Service for working with data in table **/
    private final TableService _service = TableServiceImp.getInstance();

    /**
     * Constructor this class. Creates services and adds other menu items.
     *
     * @param parent on what menu
     * @param style menu
     */
    public TableView(Composite parent) {
        createViewer(parent);
    }

    /**
     * Creates table and configures it.
     * Adds listener by on click, change default behavior (row selection by one click
     * and not double-click), sets layout (to stretch the table across the component).
     *
     * @param parent on that to add
     */
    private void createViewer(Composite parent) {
        _service.addTableEventListener(this);

        _viewer = new TableViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        createColumns(parent, _viewer);

        Table table = _viewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        _viewer.setContentProvider(new ArrayContentProvider());
        ModelProvider.INSTANCE.setStudents(_service.getGroup());
        _viewer.setInput(ModelProvider.INSTANCE.getStudents());

        TableViewerFocusCellManager focusCellManager = new TableViewerFocusCellManager(_viewer,
                new FocusCellOwnerDrawHighlighter(_viewer));
        ColumnViewerEditorActivationStrategy activationSupport = new ColumnViewerEditorActivationStrategy(_viewer) {
            @Override
            protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
                // Enable editor only with mouse double click
                if (event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION) {
                    EventObject source = event.sourceEvent;
                    if (source instanceof MouseEvent && ((MouseEvent) source).button == 3) {
                        return false;
                    }
                    return true;
                }
                return false;
            }
        };
        TableViewerEditor.create(_viewer, focusCellManager, activationSupport, ColumnViewerEditor.DEFAULT);

        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.horizontalSpan = 1;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;

        _viewer.getTable().setLayoutData(gridData);
    }

    public void setSelectionProvider(IWorkbenchPartSite site) {
        site.setSelectionProvider(_viewer);
    }

    /**
     * Creates columns for table. Sets LabelProviders and EditingSupports them.
     *
     * @param parent on that to add
     * @param viewer is table
     */
    private void createColumns(final Composite parent, final TableViewer viewer) {
        String[] titles = { "Name", "Group", "SWT done" };
        int[] bounds = { 140, 70, 90 };

        // Avoiding of padding in the first column
        // see bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=43910
        TableViewerColumn bagColumn = createTableViewerColumn("", 0, 0);
        bagColumn.setLabelProvider(new NameProvider());

        TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
        col.setEditingSupport(new NameEditingSupport(viewer));
        col.setLabelProvider(new NameProvider());

        col = createTableViewerColumn(titles[1], bounds[1], 1);
        col.setEditingSupport(new NumberEditingSupport(viewer));
        col.setLabelProvider(new NumberGroupProvider());
        col.getColumn().setAlignment(SWT.RIGHT);

        col = createTableViewerColumn(titles[2], bounds[2], 2);
        col.setLabelProvider(new CheckButtonProvider());
        col.setEditingSupport(new CheckBoxEditingSupport(viewer));
    }

    /**
     * Creates new column in table.
     *
     * @param title is name of column
     * @param bound is width of column
     * @param colNumber is number of column
     * @return column
     */
    private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
        TableViewerColumn viewerColumn = new TableViewerColumn(_viewer, SWT.NONE);
        TableColumn column = viewerColumn.getColumn();
        column.setText(title);
        column.setWidth(bound);
        column.setResizable(true);
        column.setMoveable(true);
        return viewerColumn;
    }

    /**
     * Updates the table after data change.
     */
    @Override
    public void tableEvent() {
        ModelProvider.INSTANCE.setStudents(_service.getGroup());
        _viewer.setInput(ModelProvider.INSTANCE.getStudents());
        _viewer.setSelection(StructuredSelection.EMPTY);
        _viewer.refresh();
    }
}
