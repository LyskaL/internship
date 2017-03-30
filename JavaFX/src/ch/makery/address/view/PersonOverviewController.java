package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {
    @FXML
    private TableView<Person> _personTable;
    @FXML
    private TableColumn<Person, String> _firstNameColumn;
    @FXML
    private TableColumn<Person, String> _lastNameColumn;

    @FXML
    private Label _firstNameLabel;
    @FXML
    private Label _lastNameLabel;
    @FXML
    private Label _streetLabel;
    @FXML
    private Label _postalCodeLabel;
    @FXML
    private Label _cityLabel;
    @FXML
    private Label _birthdayLabel;

    private MainApp _mainApp;

    public PersonOverviewController() {}

    @FXML
    private void initialize() {
        // Initialize a table of recipients with two columns.
        _firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        _lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        showPersonDetails(null);

        //We listen to changes in the choice, and when you change, show additional information about the addressee.
        _personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        _mainApp = mainApp;

        // Adding a data table from the observable list
        _personTable.setItems(_mainApp.getPersonData());
    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill labels with information from the Person object.
            _firstNameLabel.setText(person.getFirstName());
            _lastNameLabel.setText(person.getLastName());
            _streetLabel.setText(person.getStreet());
            _postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            _cityLabel.setText(person.getCity());
            _birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            _firstNameLabel.setText("");
            _lastNameLabel.setText("");
            _streetLabel.setText("");
            _postalCodeLabel.setText("");
            _cityLabel.setText("");
            _birthdayLabel.setText("");

        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = _personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            _personTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(_mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }



}
