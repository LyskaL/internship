package llyska.table.editors;

import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * The class extends TextCellEditor.
 * Allows to enter only numbers in field of edit.
 *
 * @author Lyska Lyudmila
 */
public class NumbersCellEditor extends TextCellEditor {

    public NumbersCellEditor() {
        super();
    }

    public NumbersCellEditor(Composite parent, int style) {
        super(parent, SWT.RIGHT);
    }

    public NumbersCellEditor(Composite parent) {
        super(parent, SWT.RIGHT);
    }

    @Override
    protected Control createControl(Composite parent) {
        Text text = (Text) super.createControl(parent);


        text.addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent e) {
                Text text = (Text)e.getSource();

                final String oldS = text.getText();
                String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);

                boolean isFloat = true;
                try{
                    Float.parseFloat(newS);
                } catch (NumberFormatException ex) {
                    isFloat = false;
                }

                if(!isFloat) {
                    e.doit = false;
                }
            }
        });
        return text;
    }
}