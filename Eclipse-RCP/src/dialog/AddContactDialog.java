package dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import entities.User;
import entities.User.Gender;
import entities.User.GroupType;
import services.GroupService;
import services.GroupServiceImpl;

public class AddContactDialog extends Dialog {

    private Text _textUserID;
    private Text _textUserName;

    private Button _maleGender;
    private Button _femaleGender;

    private Text _textServer;
    private Combo _groupType;

    GroupService _service = GroupServiceImpl.getInstance();

    public AddContactDialog(Shell parent) {
        super(parent);
    }

    @Override
    protected void okPressed() {
        GroupType type = null;
        for (GroupType group : GroupType.values()) {
            _groupType.add(group.toString());
            if (group.toString().equals(_groupType.getText())) {
                type = group;
                break;
            }
        }

        Gender gender = _maleGender.getSelection() ? Gender.MALE : Gender.FEMALE;

        _service.addUser(new User(_textUserID.getText(),
                                  _textServer.getText(),
                                  _textUserName.getText(),
                                  gender,
                                  type));
        super.okPressed();
    }

    @Override
    protected void cancelPressed() {
        super.cancelPressed();
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        getShell().setText("Add contact");
        Composite area = (Composite) super.createDialogArea(parent);

        Composite container = new Composite(area, SWT.NULL);
        container.setLayout(new GridLayout(1, false));
        container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Composite containerText = new Composite(container, SWT.NULL);
        containerText.setLayout(new GridLayout(2, false));
        containerText.setLayoutData(new GridData(GridData.FILL_BOTH));

        new Label(containerText, SWT.NULL).setText("Add contact:");
        new Label(containerText, SWT.NULL).setText("");

        new Label(containerText, SWT.NULL).setText("User ID:");
        _textUserID = new Text(containerText, SWT.BORDER);
        _textUserID.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        new Label(containerText, SWT.NULL).setText("User Name:");
        _textUserName = new Text(containerText, SWT.BORDER);
        _textUserName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        new Label(containerText, SWT.NULL).setText("Server:");
        _textServer = new Text(containerText, SWT.BORDER);
        _textServer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        new Label(containerText, SWT.NULL).setText("Group:");

        _groupType = new Combo(containerText, SWT.READ_ONLY);

        for (GroupType group : GroupType.values()) {
            _groupType.add(group.toString());
            if (group.toString().equals(GroupType.OTHER)) {
            }
        }
        _groupType.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        _groupType.setSelection(new Point(20, 20));
        _groupType.select(GroupType.values().length - 1);
        _groupType.setCapture(false);

        Group groupButtonGender = new Group(container, SWT.SHADOW_IN);
        groupButtonGender.setLayout(new GridLayout(2, false));
        groupButtonGender.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        groupButtonGender.setText(" Gender ");

        _maleGender = new Button(groupButtonGender, SWT.RADIO | SWT.LEFT_TO_RIGHT);
        _maleGender.setText("male");
        _maleGender.setSelection(true);
        _maleGender.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        _femaleGender = new Button(groupButtonGender, SWT.RADIO | SWT.RIGHT_TO_LEFT);
        _femaleGender.setText("female");
        _femaleGender.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        return area;
    }

    @Override
    protected Point getInitialSize() {
        return new Point(450, 280);
    }

}
