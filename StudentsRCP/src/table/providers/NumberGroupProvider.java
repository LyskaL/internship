package table.providers;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import entities.Student;

/**
 * The class extends ColumnLabelProvider. The class is responsible for display information in cell with a number of
 * group where studying student.
 *
 * @author Lyska Lyudmila
 */
public class NumberGroupProvider extends ColumnLabelProvider {
    @Override
    public String getText(Object element) {
        Student p = (Student) element;
        return String.valueOf(p.getNumberGroup());
    }
}