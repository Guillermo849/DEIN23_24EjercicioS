package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AnimalesController {

    @FXML
    private MenuItem mnItAniadirAnimal;

    @FXML
    private MenuItem mnItEditarAnimal;

    @FXML
    private MenuItem mnItBorrarAnimal;

    @FXML
    private ImageView imgAnimal;

    @FXML
    private TextField txtVNombre;

    @FXML
    private TableView<?> tbViewAnimales;

    @FXML
    private TableColumn<?, ?> tbColId;

    @FXML
    private TableColumn<?, ?> tbColNombre;

    @FXML
    private TableColumn<?, ?> tbColEspecie;

    @FXML
    private TableColumn<?, ?> tbColRaza;

    @FXML
    private TableColumn<?, ?> tbColSexo;

    @FXML
    private TableColumn<?, ?> tbColPeso;

    @FXML
    private TableColumn<?, ?> tbColEdad;

    @FXML
    private TableColumn<?, ?> tbColObservaciones;

    @FXML
    private TableColumn<?, ?> tbColFecha;

    @FXML
    void aniadirAnimal(ActionEvent event) {

    }

    @FXML
    void borrarAnimal(ActionEvent event) {

    }

    @FXML
    void editarAnimal(ActionEvent event) {

    }

    @FXML
    void filtrarNombre(ActionEvent event) {

    }

}
