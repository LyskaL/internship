package llyska.jface.tableexample;

import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class NumberEditing extends TextCellEditor {

    public NumberEditing() {
        super();
    }

    public NumberEditing(Composite parent, int style) {
        super(parent, style);
    }

    public NumberEditing(Composite parent) {
        super(parent);
    }

    @Override
    protected Control createControl(Composite parent) {
        Text text = (Text) super.createControl(parent);
        text.addVerifyListener(new VerifyListener() {

            @Override
            public void verifyText(VerifyEvent e) {

                Text text = (Text)e.getSource();

                // get old text and create new text by using the VerifyEvent.text
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
