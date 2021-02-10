package com.grupoad3.directoriofx;

import com.grupoad3.directoriofx.db.DataBaseManager;
import com.grupoad3.directoriofx.model.Person;
import com.grupoad3.directoriofx.view.PersonEditDialogController;
import com.grupoad3.directoriofx.view.PersonOverviewController;
import java.io.File;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private final File archivo = new File("./archivo.db");
    DataBaseManager db;
    final Image iconoApp = new Image(getClass().getResourceAsStream("/img/icons/icon.png"));
    
    private final ObservableList<Person> personData = FXCollections.observableArrayList();

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public DataBaseManager getDb() {
        return db;
    }

    public Image getIconoApp() {
        return iconoApp;
    }
    
    
    

    @Override
    public void start(Stage stage) throws Exception {

        

        this.primaryStage = stage;
        this.primaryStage.setTitle("DirectorioFX");        
        //this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/icons/icon.png")));
        this.primaryStage.getIcons().add(iconoApp);
        

        initRootLayout();
        initDataBase();
        initPersonData();      
        
        
        showPersonOverview();
        

       
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            //FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));

            //rootLayout = (BorderPane) loader.load();
            rootLayout = (BorderPane) FXMLLoader.load(getClass().getResource("/fxml/RootLayout.fxml"));            
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
          
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonOverview() {
        try {
            // Load person overview.
            //FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            //AnchorPane personOverview = (AnchorPane) loader.load();
            //AnchorPane personOverview = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/PersonOverview.fxml")); //Una sola linea

            // Cargar el archivo de la vista
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
           

            // Set person overview into the center of root layout. Asignarlo a la raiz correspondiente.
            rootLayout.setCenter(personOverview);

            // Poniendo el controlador de la vista y poniendo la app contenedora.
            PersonOverviewController controlador = loader.getController();
            controlador.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
            loader.setLocation(getClass().getResource("/fxml/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.getIcons().add(iconoApp);
            
            Scene scene = new Scene(page);
            
            //scene.getStylesheets().add("/styles/Styles.css");
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);                        

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void initDataBase() {
        try {
            
            
            archivo.createNewFile();
            
            //Person p = new Person("Daniel", "Serna");
            
            db = new DataBaseManager(archivo);
            db.setupDatabase();
          
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initPersonData() {
        try {
            List<Person> personas = db.getPersonDao().queryForAll();
            for (Person persona : personas) {
                personData.add(persona);
            }
        } catch (SQLException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Error:" + ex.getMessage());
            alerta.setHeaderText(null);
            alerta.setTitle("Error");
            
            
            alerta.showAndWait();
            
        }
    }

}
