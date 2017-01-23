package llyska.table.providers;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import llyska.entities.Student;

public class NumberGroupProvider extends ColumnLabelProvider {
    @Override
    public String getText(Object element) {
        Student p = (Student) element;
        return String.valueOf(p.getNumberGroup());
    }
}