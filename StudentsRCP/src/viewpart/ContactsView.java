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

        //new TableView(tablePanel);
        TableView table = new TableView(tablePanel);
        table.setSelectionProvider(getSite());

        Composite formPanel = new Composite(sashForm, SWT.NONE);
        formPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        formPanel.setLayout(new GridLayout(1, true));
        // sashForm.setBackground(PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_WHITE));
        // new ActionContributionItem(new NewAction(getSite().getWorkbenchWindow())).fill(formPanel);

        new FormView(formPanel, SWT.NONE, getSite().getWorkbenchWindow());
        // IWorkbenchWindow window = getSite().getWorkbenchWindow();
        // ActionContributionItem newAction = new ActionContributionItem(new NewAction(window));
        // newAction.fill(formPanel);
        // Button newButton = (Button) newAction.getWidget();
        // newButton.setText("New");
        //
        // ActionContributionItem saveAction = new ActionContributionItem(new SaveAction(window));
        // saveAction.fill(formPanel);
        // Button saveButton = (Button) saveAction.getWidget();
        // //saveButton.setImage(null);
        // saveButton.setText("Save");
        //
        // ActionContributionItem deleteAction = new ActionContributionItem(new DeleteAction(window));
        // deleteAction.fill(formPanel);
        // Button deleteButton = (Button) deleteAction.getWidget();
        // deleteButton.setText("Delete");
        //
        // ActionContributionItem cancelAction = new ActionContributionItem(new CancelAction(window));
        // cancelAction.fill(formPanel);
        // Button cancelButton = (Button) cancelAction.getWidget();
        // cancelButton.setText("Cancel");
    }

    @Override
    public void setFocus() {

    }
}
