package dialog;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
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
import viewpart.Constants;

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
    protected Control createDialogArea(Composite parent) {
        getShell().setText("Add contact");

        addImageToWindow();

        Composite dialogComposite = (Composite) super.createDialogArea(parent);

        Composite mainPanel = new Composite(dialogComposite, SWT.NULL);
        mainPanel.setLayout(new GridLayout(1, false));
        mainPanel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        createTextPanel(mainPanel);
        createGroupButtonGender(mainPanel);

        return dialogComposite;
    }

    private void createGroupButtonGender(Composite mainPanel) {
        Group groupButtonGender = new Group(mainPanel, SWT.SHADOW_IN);
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
    }

    private void createTextPanel(Composite mainPanel) {

        Composite containerText = new Composite(mainPanel, SWT.NULL);
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

        addGroupType(containerText);
    }

    private void addGroupType(Composite containerText) {
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
    }

    private void addImageToWindow() {
        URL url = Platform.getBundle("Eclipse-RCP").getEntry("icons/add_contact.png");
        ImageDescriptor image = ImageDescriptor.createFromURL(url);
        getShell().setImage(new Image(Constants.DISPLAY, image.getImageData()));
    }

    @Override
    protected Point getInitialSize() {
        return new Point(450, 280);
    }

}
