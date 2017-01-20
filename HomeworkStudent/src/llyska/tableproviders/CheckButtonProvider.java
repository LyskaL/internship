package llyska.tableproviders;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

import llyska.util.Constants;

public class CheckButtonProvider extends CellLabelProvider {
    @Override
    public void update(ViewerCell cell) {
        TableModel model = (TableModel) cell.getElement();
        cell.setImage((model.isCheck()) ? Constants.CHECKED : Constants.UNCHECKED);
    }
}
