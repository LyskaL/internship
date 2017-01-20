package llyska.tableproviders;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

public class NumberGroupProvider extends CellLabelProvider {
    @Override
    public void update(ViewerCell cell) {
        TableModel model = (TableModel) cell.getElement();
        cell.setText(String.valueOf(model.getNumber()));
    }
}