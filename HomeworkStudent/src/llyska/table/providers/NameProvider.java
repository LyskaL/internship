package llyska.table.providers;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import llyska.entities.Student;

/**
 * The class extends ColumnLabelProvider.
 * The class is responsible for display information
 * in cell with a name of student.
 *
 * @author Lyska Lyudmila
 */
public class NameProvider extends ColumnLabelProvider {
    @Override
    public String getText(Object element) {
        Student p = (Student) element;
        return p.getName();
    }
}
