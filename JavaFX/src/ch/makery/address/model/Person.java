package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {

    private final StringProperty _firstName;
    private final StringProperty _lastName;
    private final StringProperty _street;
    private final IntegerProperty _postalCode;
    private final StringProperty _city;
    private final ObjectProperty<LocalDate> _birthday;

    public Person() {
        this(null, null);
    }

    public Person(String firstName, String lastName) {
        _firstName = new SimpleStringProperty(firstName);
        _lastName = new SimpleStringProperty(lastName);
        _street = new SimpleStringProperty("Topol 2");
        _postalCode = new SimpleIntegerProperty(1234);
        _city = new SimpleStringProperty("Dnipro");
        _birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getFirstName() {
        return _firstName.get();
    }

    public void setFirstName(String firstName) {
        this._firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return _firstName;
    }

    public String getLastName() {
        return _lastName.get();
    }

    public void setLastName(String lastName) {
        this._lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return _lastName;
    }

    public String getStreet() {
        return _street.get();
    }

    public void setStreet(String street) {
        this._street.set(street);
    }

    public StringProperty streetProperty() {
        return _street;
    }

    public int getPostalCode() {
        return _postalCode.get();
    }

    public void setPostalCode(int postalCode) {
        this._postalCode.set(postalCode);
    }

    public IntegerProperty postalCodeProperty() {
        return _postalCode;
    }

    public String getCity() {
        return _city.get();
    }

    public void setCity(String city) {
        this._city.set(city);
    }

    public StringProperty cityProperty() {
        return _city;
    }

    public LocalDate getBirthday() {
        return _birthday.get();
    }

    public void setBirthday(LocalDate birthday) {
        this._birthday.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return _birthday;
    }
}