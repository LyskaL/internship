package ch.makery.address;

import java.io.IOException;

import ch.makery.address.model.Person;
import ch.makery.address.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage _primaryStage;
    private BorderPane _rootLayout;
    private final ObservableList<Person> _personData = FXCollections.observableArrayList();

    public MainApp() {
        _personData.add(new Person("Hans", "Muster"));
        _personData.add(new Person("Ruth", "Mueller"));
        _personData.add(new Person("Heinz", "Kurz"));
        _personData.add(new Person("Cornelia", "Meier"));
        _personData.add(new Person("Werner", "Meyer"));
        _personData.add(new Person("Lydia", "Kunz"));
        _personData.add(new Person("Anna", "Best"));
        _personData.add(new Person("Stefan", "Meier"));
        _personData.add(new Person("Martin", "Mueller"));
    }

    @Override
    public void start(Stage primaryStage) {
        _primaryStage = primaryStage;
        _primaryStage.setTitle("AddressApp");

        initRootLayout();
        showPersonOverview();
    }

    /**
     * Initialisere the root layout.
     */
    public void initRootLayout() {
        try {
            // Downloadable root layout from the fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            _rootLayout = (BorderPane) loader.load();

            // Display a scene containing the root layout.
            Scene scene = new Scene(_rootLayout);
            _primaryStage.setScene(scene);
            _primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows in the root layout information about the recipients.
     */
    public void showPersonOverview() {
        try {
            // Downloadable information about the recipients.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Placing information about the recipients to the center of root layout.
            _rootLayout.setCenter(personOverview);

            // Give access to the main application to the controller
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     *
     * @return the main stage
     */
    public Stage getPrimaryStage() {
        return _primaryStage;
    }

    public ObservableList<Person> getPersonData() {
        return _personData;
    }

    public static void main(String[] args) {
        launch(args);
    }


}
