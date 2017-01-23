package llyska.table.providers;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import llyska.entities.Student;

public class NameProvider extends ColumnLabelProvider {
    @Override
    public String getText(Object element) {
        Student p = (Student) element;
        return p.getName();
    }
}
