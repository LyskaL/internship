package llyska.interfaces;

import java.util.EventObject;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TableViewerFocusCellManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import llyska.events.ChangeStateButtonEvent;
import llyska.services.StateButtonService;
import llyska.services.TableService;
import llyska.services.TableServiceImp;
import llyska.table.editors.CheckBoxEditingSupport;
import llyska.table.editors.NameEditingSupport;
import llyska.table.editors.NumberEditingSupport;
import llyska.table.providers.CheckButtonProvider;
import llyska.table.providers.ModelProvider;
import llyska.table.providers.NameProvider;
import llyska.table.providers.NumberGroupProvider;

public class TableView {
    private TableViewer _viewer;
    private final TableService _service = new TableServiceImp();

    private StateButtonService _stateButtonService;

    public TableView(Composite parent) {
        createViewer(parent);
    }

    private void createViewer(Composite parent) {
        _stateButtonService = StateButtonService.getInstance();
        _viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        createColumns(parent, _viewer);
        Table table = _viewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        _viewer.setContentProvider(new ArrayContentProvider());
        ModelProvider.INSTANCE.setPersons(_service.getGroup());
        _viewer.setInput(ModelProvider.INSTANCE.getPersons());

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
        TableViewerEditor.create(_viewer, focusCellManager, activationSupport,
                ColumnViewerEditor.DEFAULT);


        _viewer.getTable().addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event e) {
                System.out.println("��������� runEvent �� ������� - TABLE_SELECTED");
                ChangeStateButtonEvent.getInstance().setStateButton(ChangeStateButtonEvent.TABLE_SELECTED);
                _stateButtonService.runEvent();
              }
            });

        _viewer.getTable().addListener(SWT.DefaultSelection, new Listener() {
            @Override
            public void handleEvent(Event e) {
              /*  System.out.println("��������� runEvent �� ������� - TABLE_EDITED");
                ChangeStateButtonEvent.getInstance().setStateButton(ChangeStateButtonEvent.TABLE_EDITED);
                _stateButtonService.runEvent();*/
              }
            });


        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.horizontalSpan = 2;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;

    }

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

    private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
        TableViewerColumn viewerColumn = new TableViewerColumn(_viewer, SWT.NONE);
        TableColumn column = viewerColumn.getColumn();
        column.setText(title);
        column.setWidth(bound);
        column.setResizable(true);
        column.setMoveable(true);
        return viewerColumn;
    }

    public void setFocus() {
        _viewer.getControl().setFocus();
    }

    public void refresh() {
        _viewer.refresh();
    }

}