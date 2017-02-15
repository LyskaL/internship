package entities;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import viewpart.Constants;

public class User {
    private String _login;
    private String _service;
    private String _name;
    private Image _image;
    private Gender _gender;
    private GroupType _groupType;

    public User(String login, String service, String name, Gender gender, GroupType groupType) {
        setLogin(login);
        setName(name);
        setService(service);
        setGender(gender);
        setGroupType(groupType);

        URL url = null;
        if (gender == Gender.FEMALE) {
            url = Platform.getBundle("Eclipse-RCP").getEntry("icons/female_user_icon.png");
        } else {
            url = Platform.getBundle("Eclipse-RCP").getEntry("icons/male_user_icon.png");
        }
        ImageDescriptor image = ImageDescriptor.createFromURL(url);
        setImage(new Image(Constants.DISPLAY, image.getImageData()));
    }

    public String getLogin() {
        return _login;
    }

    public void setLogin(String login) {
        _login = login;
    }

    @Override
    public String toString() {
        return "" +_login + " (" + _name + "@" + _service +")";
    }

    public String getService() {
        return _service;
    }

    public void setService(String service) {
        _service = service;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public Image getImage() {
        return _image;
    }

    private void setImage(Image image) {
        _image = image;
    }

    public Gender getGender() {
        return _gender;
    }

    private void setGender(Gender gender) {
        _gender = gender;
    }

    public GroupType getGroupType() {
        return _groupType;
    }

    public void setGroupType(GroupType groupType) {
        _groupType = groupType;
    }



    public enum Gender {
        FEMALE,
        MALE
    }

    public enum GroupType {
        FAMILY,
        BUSINESS,
        FRIENDS,
        OTHER
    }
}
