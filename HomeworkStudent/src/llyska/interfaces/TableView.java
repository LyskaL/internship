package llyska.interfaces;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

import llyska.entities.Group;
import llyska.entities.Student;
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
        _viewer.setLabelProvider(new GroupBookLabelProvider());
        String[] titles = { "Name", "Group", "SWT done" };
        int[] bounds = { 140, 70, 100 };

        for (int i = 0; i < titles.length; i++) {
            TableViewerColumn col = createTableViewerColumn(titles[i], bounds[i], i);
            col.setLabelProvider(new CellLabelProvider() {
                @Override
                public void update(ViewerCell col) {
                    MyModel model = (MyModel) col.getElement();
                    int index = col.getColumnIndex();
                    switch (index) {
                    case 0:
                        col.setText(model.getName());

                        break;
                    case 1:
                        col.setText(String.valueOf(model.getNumber()));
                        break;
                    case 2:
                        col.setImage((model.isCheck()) ? Constants.CHECKED : Constants.UNCHECKED);
                        break;
                    }
                }
            });
        }

        MyModel[] model = createModel();
        _viewer.setInput(model);
        _viewer.getTable().setLinesVisible(true);
        _viewer.getTable().setHeaderVisible(true);

    }

    class GroupBookLabelProvider implements ITableLabelProvider {
        @Override
        public String getColumnText(Object element, int columnIndex) {
            MyModel ae = (MyModel) element;

            switch (columnIndex) {
            case 0:
                return ae.getName();
            case 1:
                return String.valueOf(ae.getNumber());
            }
            return element.toString();
        }

        @Override
        public void addListener(ILabelProviderListener arg0) {}

        @Override
        public void dispose() {}

        @Override
        public boolean isLabelProperty(Object arg0, String arg1) {
            return false;
        }

        @Override
        public void removeListener(ILabelProviderListener arg0) {}

        @Override
        public Image getColumnImage(Object element, int index) {
            MyModel model = (MyModel) element;
            return (model.isCheck()) ? Constants.CHECKED : Constants.UNCHECKED;
        }
    }

    public class MyModel {
        private final String name;
        private final int number;
        private final boolean isCheck;
        public int counter;

        public MyModel(Student student, int numberGroup) {
            name = student.getName();
            number = numberGroup;
            isCheck = student.isSwtDone();
        }

        @Override
        public String toString() {
            return "MyModel";
        }

        public String getName() {
            return name;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public int getNumber() {
            return number;
        }
    }

    private MyModel[] createModel() {
        MyModel[] elements = new MyModel[_group.size()];

        for (int i = 0; i < _group.size(); i++) {
            elements[i] = new MyModel(_group.getStudent(i), _group.getNumberGroup());
        }

        return elements;
    }

    private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
        TableViewerColumn viewerColumn = new TableViewerColumn(_viewer, SWT.NONE);
        TableColumn column = viewerColumn.getColumn();
        column.setText(title);
        column.setWidth(bound);
        column.setResizable(false);
        column.setMoveable(false);

        switch(colNumber){
        case 0:
            column.setAlignment(SWT.CENTER);
            break;
        case 1:
            column.setAlignment(SWT.RIGHT);
            break;
        }
        return viewerColumn;
    }
}
