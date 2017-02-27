package viewpart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class ContactsView extends ViewPart {

    public static final String ID = "StudentsRCP.viewpart.contacts";

    public ContactsView() {
        super();
    }

    @Override
    public void createPartControl(Composite parent) {
        SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL | SWT.BORDER);

        Composite tablePanel = new Composite(sashForm, SWT.NONE);
        tablePanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        tablePanel.setLayout(new GridLayout(1, true));

        TableView table = new TableView(tablePanel);
        table.setSelectionProvider(getSite());

        Composite formPanel = new Composite(sashForm, SWT.NONE);
        formPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        formPanel.setLayout(new GridLayout(1, true));

        new FormView(formPanel, SWT.NONE, getSite().getWorkbenchWindow());

    }

    @Override
    public void setFocus() {

    }
}
