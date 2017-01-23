package llyska.interfaces;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import llyska.table.editors.CheckBoxEditingSupport;
import llyska.table.editors.NameEditingSupport;
import llyska.table.editors.NumberEditingSupport;
import llyska.table.providers.CheckButtonProvider;
import llyska.table.providers.ModelProvider;
import llyska.table.providers.NameProvider;
import llyska.table.providers.NumberGroupProvider;

public class TableView {
    private TableViewer viewer;

    public TableView(Composite parent) {
        createViewer(parent);
    }

    private void createViewer(Composite parent) {
        viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        createColumns(parent, viewer);
        final Table table = viewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        viewer.setContentProvider(new ArrayContentProvider());
        viewer.setInput(ModelProvider.INSTANCE.getPersons());

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

    private void createColumns(final Composite parent, final TableViewer viewer) {
        String[] titles = { "Name", "Group", "SWT done" };
        int[] bounds = { 140, 70, 90 };

        //Avoiding of padding in the first column
        //see bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=43910
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

    public void refresh() {
        viewer.refresh();
    }

}