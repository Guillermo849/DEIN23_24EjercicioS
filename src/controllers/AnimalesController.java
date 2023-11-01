package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import gestorBDD.GestorBDDAnimales;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Animal;

public class AnimalesController implements Initializable{

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
    private TableView<Animal> tbViewAnimales;

    @FXML
    private TableColumn<Animal, Integer> tbColId;

    @FXML
    private TableColumn<Animal, String> tbColNombre;

    @FXML
    private TableColumn<Animal, String> tbColEspecie;

    @FXML
    private TableColumn<Animal, String> tbColRaza;

    @FXML
    private TableColumn<Animal, String> tbColSexo;

    @FXML
    private TableColumn<Animal, Long> tbColPeso;

    @FXML
    private TableColumn<Animal, Integer> tbColEdad;

    @FXML
    private TableColumn<Animal, String> tbColObservaciones;

    @FXML
    private TableColumn<Animal, String> tbColFecha;
    
    private int animalIndex;
    
    private GestorBDDAnimales gstBDDAnimales;
    
    private AniadirModAnimalController anMdAnimalesController;
    
    public void cargarTabla() {
    	tbViewAnimales.setItems(gstBDDAnimales.cargarAnimals());
    }
    
    void selectAnimal(MouseEvent event) {
		if (tbViewAnimales.getSelectionModel().getSelectedItem() != null) {
			animalIndex = tbViewAnimales.getSelectionModel().getSelectedIndex();
		}
	}
    
    @FXML
    void aniadirAnimal(ActionEvent event) {
    	try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/anidirModAnimal.fxml"));
			Parent root = loader.load();
			anMdAnimalesController = loader.getController();
			anMdAnimalesController.setParent(this, null);

			Stage agregarStage = new Stage();
			agregarStage.setScene(new Scene(root));
			agregarStage.setResizable(false);
			agregarStage.setTitle("GESTOR ANIMALES - AÑADIR");
			agregarStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void borrarAnimal(ActionEvent event) {
    	if (animalIndex > -1) {
			Animal animalEliminar = tbViewAnimales.getItems().get(animalIndex);
			gstBDDAnimales.eliminarAnimal(animalEliminar);
			tbViewAnimales.setItems(gstBDDAnimales.cargarAnimals());
		}
    }

    @FXML
    void editarAnimal(ActionEvent event) {
    	if (animalIndex > -1) {
			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/anidirModAnimal.fxml"));
				Parent root = loader.load();
				anMdAnimalesController = loader.getController();
				anMdAnimalesController.setParent(this, tbViewAnimales.getItems().get(animalIndex));

				Stage agregarStage = new Stage();
				agregarStage.setScene(new Scene(root));
				agregarStage.setTitle("GESTOR ANIMALES - MODIFICAR");
				agregarStage.showAndWait();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }

    @FXML
    void filtrarNombre(ActionEvent event) {
        FilteredList<Animal> filteredData = new FilteredList<Animal>(gstBDDAnimales.cargarAnimals());
        filteredData.setPredicate(s -> s.getNombre().contains(txtVNombre.getText()));
        SortedList<Animal> filteredSortedData = new SortedList<Animal>(filteredData);
        tbViewAnimales.setItems(filteredSortedData);
    }
    
    public GestorBDDAnimales getGstBDDAnimales() {
		return gstBDDAnimales;
	}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		tbColId.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("ID"));
		
		tbColNombre.setCellValueFactory(new PropertyValueFactory<Animal, String>("nombre"));

		tbColEspecie.setCellValueFactory(new PropertyValueFactory<Animal, String>("especie"));
		
		tbColRaza.setCellValueFactory(new PropertyValueFactory<Animal, String>("raza"));

		tbColSexo.setCellValueFactory(new PropertyValueFactory<Animal, String>("sexo"));

		tbColEdad.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("edad"));
		
		tbColPeso.setCellValueFactory(new PropertyValueFactory<Animal, Long>("peso"));
		
		tbColObservaciones.setCellValueFactory(new PropertyValueFactory<Animal, String>("observaciones"));
		
		tbColFecha.setCellValueFactory(new PropertyValueFactory<Animal, String>("fecha"));
		
		gstBDDAnimales = new GestorBDDAnimales();

		tbViewAnimales.setItems(gstBDDAnimales.cargarAnimals());
		
		tbViewAnimales.setOnMouseClicked(e -> selectAnimal(e));
	}

}
