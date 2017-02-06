package llyska.jface.treeviewer;

import org.eclipse.jface.viewers.LabelProvider;

public class CategoryTreeLabelProvider extends LabelProvider {

    @Override
    public String getText(Object element) {
        if (element instanceof Category) {
            return ((Category) element).getName();
        } else if (element instanceof Link) {
            return ((Link) element).getUrl();
        }

        return "" + element;
    }
}