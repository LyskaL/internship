package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import javafx.fxml.FXML;
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
    }

    public void setMainApp(MainApp mainApp) {
        _mainApp = mainApp;

        // Adding a data table from the observable list
        _personTable.setItems(_mainApp.getPersonData());
    }
}
