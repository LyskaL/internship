package llyska.interfaces;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import llyska.entities.Group;
import llyska.tableproviders.CheckButtonProvider;
import llyska.tableproviders.NameProvider;
import llyska.tableproviders.NumberGroupProvider;
import llyska.tableproviders.TableModel;
import llyska.util.Constants;

public class TableView extends Composite {
    private TableViewer _viewer;
    private final Group _group = new Group(30);

    public TableView(Composite parent, int style) {
        super(parent, style);
        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        setLayout(new FillLayout());

        createTable();

    }

    private void createTable() {
        _viewer = new TableViewer(this, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        _viewer.setContentProvider(ArrayContentProvider.getInstance());

        String[] titles = { "Name", "Group", "SWT done" };
        int[] bounds = { 140, 70, 100 };

        //TODO: magic
        TableViewerColumn bagColumn = createTableViewerColumn("", 0);
        bagColumn.setLabelProvider(new NameProvider());

        TableViewerColumn nameColumn = createTableViewerColumn(titles[0], bounds[0]);
        nameColumn.setLabelProvider(new NameProvider());
        nameColumn.getColumn().setAlignment(SWT.LEFT);

        TableViewerColumn numberColumn = createTableViewerColumn(titles[1], bounds[1]);
        numberColumn.setLabelProvider(new NumberGroupProvider());
        numberColumn.getColumn().setAlignment(SWT.LEFT);

        TableViewerColumn checkButtonColumn = createTableViewerColumn(titles[2], bounds[2]);
        checkButtonColumn.setLabelProvider(new CheckButtonProvider());

        TableModel[] model = createModel();
        _viewer.setInput(model);
        _viewer.getTable().setLinesVisible(true);
        _viewer.getTable().setHeaderVisible(true);

        _viewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            @Override
            public void selectionChanged(SelectionChangedEvent selectionEvent)
            {
                //changed rows
                //get Table Model
                final IStructuredSelection selection = (IStructuredSelection) _viewer.getSelection();
                if (selection != null) {
                   //System.out.println(selection.toString());
                }
            }
        });

        _viewer.getTable().addListener(SWT.MouseDown, new Listener(){
            @Override
            public void handleEvent(Event event){
                Point pt = new Point(event.x, event.y);
                TableItem item = _viewer.getTable().getItem(pt);
                if(item != null) {
                    for (int col = 0; col < _viewer.getTable().getColumnCount(); col++) {
                        Rectangle rect = item.getBounds(col);
                        if (rect.contains(pt) && col == 3) {
                            if(_viewer.getCell(pt).getImage().equals(Constants.CHECKED)) {
                                _viewer.getCell(pt).setImage(Constants.UNCHECKED);
                            } else {
                                _viewer.getCell(pt).setImage(Constants.CHECKED);
                            }
                        }
                    }
                }
            }
        });

    }

    private TableModel[] createModel() {
        TableModel[] elements = new TableModel[_group.size()];

        for (int i = 0; i < _group.size(); i++) {
            elements[i] = new TableModel(_group.getStudent(i), _group.getNumberGroup());
        }

        return elements;
    }

    private TableViewerColumn createTableViewerColumn(String title, int bound) {
        TableViewerColumn viewerColumn = new TableViewerColumn(_viewer, SWT.NONE);
        TableColumn column = viewerColumn.getColumn();
        column.setText(title);
        column.setWidth(bound);
        column.setResizable(false);
        column.setMoveable(false);
        return viewerColumn;
    }
}
