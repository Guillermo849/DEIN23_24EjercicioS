package controllers;

import java.io.File;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Animal;

public class AniadirModAnimalController {

	@FXML
	private Button btnGuardar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Label lblFinanciacion;

	@FXML
	private Label lblNumTrabajadores;

	@FXML
	private TextField txtFNombre;

	@FXML
	private TextField txtFEspecie;

	@FXML
	private TextField txtFRaza;

	@FXML
	private TextField txtFSexo;

	@FXML
	private TextField txtFEdad;

	@FXML
	private TextField txtFPeso;

	@FXML
	private TextField txtFObservaciones;

	@FXML
	private Button btnImagen;

	@FXML
	private ImageView imgAnimal;

	@FXML
	private DatePicker fchConsulta;

	private AnimalesController mainController;

	private Animal animal;

	private String imagenName;
	
	DateTimeFormatter formatter;

	public void setParent(AnimalesController parent, Animal animal) {
		formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		
		this.mainController = parent;
		this.animal = animal;

		if (animal != null) {
    		txtFNombre.setText(animal.getNombre().toString());
    		txtFEspecie.setText(animal.getEspecie().toString());
    		txtFRaza.setText(animal.getRaza().toString());
    		txtFSexo.setText(animal.getSexo().toString());
    		txtFEdad.setText(animal.getEdad() + "");
    		txtFPeso.setText(animal.getPeso() + "");
    		txtFObservaciones.setText(animal.getNombre().toString());
    		LocalDate primeraConsulta = LocalDate.parse(animal.getFecha(), formatter);
    		fchConsulta.setValue(primeraConsulta);
    		imgAnimal.setImage(new Image(Paths.get(".").toAbsolutePath().normalize().toString() + "/resources/img/" + animal.getFoto()));
    		imagenName = animal.getFoto();
		}

	}

	@FXML
	void cancelar(ActionEvent event) {
		Node n = (Node) event.getSource();
		Stage stage = (Stage) n.getScene().getWindow();
		stage.close();
	}

	@FXML
	void getDate(ActionEvent event) {
	}

	@FXML
	void guardarAnimal(ActionEvent event) {

		boolean error = false;

		Alert alertWindows = new Alert(Alert.AlertType.ERROR);

		String mensaje = "";

		if (txtFNombre.getText().isEmpty() || txtFEspecie.getText().isEmpty() || txtFRaza.getText().isEmpty()
				|| txtFSexo.getText().isEmpty() || txtFEdad.getText().isEmpty() || !txtFEdad.getText().matches("[0-9]*")
				|| txtFPeso.getText().isEmpty() || !txtFPeso.getText().matches("[0-9]*")) {

			error = true;

			if (txtFNombre.getText().isEmpty()) {
				mensaje += "El campo Nombre es Obligatorio \n";
			}
			if (txtFEspecie.getText().isEmpty()) {
				mensaje += "El campo Especie es Obligatorio \n";
			}
			if (txtFRaza.getText().isEmpty()) {
				mensaje += "El campo Raza es Obligatorio \n";
			}
			if (txtFSexo.getText().isEmpty()) {
				mensaje += "El campo Sexo es Obligatorio \n";
			}

			if (txtFEdad.getText().isEmpty()) {
				mensaje += "El campo Edad es Obligatorio \n";
			}
			if (!txtFEdad.getText().matches("[0-9]*")) {
				mensaje += "El campo Edad debe ser númerico \n";
			}

			if (txtFPeso.getText().isEmpty()) {
				mensaje += "El campo Peso es Obligatorio \n";
			}
			if (!txtFPeso.getText().matches("[0-9]*")) {
				mensaje += "El campo Peso debe ser númerico \n";
			}
		}

		if (error == true) {
			Stage stage = (Stage) alertWindows.getDialogPane().getScene().getWindow();
			alertWindows.setHeaderText(null);
			alertWindows.setContentText(mensaje);
			alertWindows.showAndWait();

		} else {

			/*
			 * Si se han introducido los datos correctamente se guarfará el aeropuerto en la
			 * base de datos
			 */
			Animal a;
			
			String nombre = txtFNombre.getText().toString();
			String especie = txtFEspecie.getText().toString();
			String raza = txtFEspecie.getText().toString();
			String sexo = txtFSexo.getText().toString();
			int edad = Integer.parseInt(txtFEdad.getText().toString());
			long peso = Long.parseLong(txtFPeso.getText().toString());
			String obervacion = txtFObservaciones.getText().toString();
			
			LocalDate selectedDate = fchConsulta.getValue();
			String fechaFormateada = selectedDate.format(formatter);

			if (animal != null) {
				a = new Animal(animal.getID(), nombre, especie, raza, sexo, edad, peso, obervacion, fechaFormateada, imagenName);
				mainController.getGstBDDAnimales().modAnimal(a);
			} else {
				a = new Animal(nombre, especie, raza, sexo, edad, peso, obervacion, fechaFormateada, imagenName);
				mainController.getGstBDDAnimales().insertAnimal(a);
			}
			mainController.cargarTabla();
		}
		// Una vez guardada la persona se cerrara la ventana
		Node n = (Node) event.getSource();
		Stage stage = (Stage) n.getScene().getWindow();stage.close();
	}

	@FXML
	void seleccionarImagen(ActionEvent event) {
		FileChooser fc = new FileChooser();
		String currentPath = Paths.get(".").toAbsolutePath().normalize().toString() + "/resources/img";
		fc.setInitialDirectory(new File(currentPath));

		fc.setTitle("Select Image");
		fc.getExtensionFilters().add(new ExtensionFilter("Imagenes", "*.png", "*.jpg"));

		File imagenElegida = fc.showOpenDialog(null);

		if (imagenElegida != null) {
			imgAnimal.setImage(new Image(imagenElegida.getAbsolutePath().toString()));
			imagenName = imagenElegida.getName().toString();
		}
	}

}
