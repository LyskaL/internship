package dialog;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class LoginDialog extends TitleAreaDialog {

    private Text textUsername;
    private Text textPassword;

    public LoginDialog(Shell parentShell) {
        super(parentShell);
    }

    @Override
    protected Control createContents(Composite parent) {
        Control contents = super.createContents(parent);
        setTitle("Login");
        setMessage("Please provide credentials");
        return contents;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite area = (Composite) super.createDialogArea(parent);

        Composite container = new Composite(area, SWT.NULL);
        container.setLayout(new GridLayout(2, false));
        container.setLayoutData(new GridData(GridData.FILL_BOTH));

        new Label(container, SWT.NULL).setText("Username");
        textUsername = new Text(container, SWT.BORDER);
        textUsername.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        new Label(container, SWT.NULL).setText("Password");
        textPassword = new Text(container, SWT.PASSWORD | SWT.BORDER);
        textPassword.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        return area;
    }

    @Override
    protected Point getInitialSize() {
        return new Point(400, 200);
    }

}
