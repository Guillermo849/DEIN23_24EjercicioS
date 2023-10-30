package gestorBDD;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Animal;

public class GestorBDDAnimales {
	private ConexionBDD conexion;

	/* Devuelve una lista de las Animals almacenadas en la base de datos */
	public ObservableList<Animal> cargarAnimals() {

		ObservableList<Animal> Animals = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBDD();
			String consulta = "select * from Animal";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String especie = rs.getString("especie");
				String raza = rs.getString("raza");
				String sexo = rs.getString("sexo");
				int edad = rs.getInt("edad");
				long peso = rs.getLong("peso");
				String observaciones = rs.getString("observaciones");
				Date fecha = rs.getDate("fecha_consulta");
				String foto = rs.getString("foto");
				Animal p = new Animal(nombre, especie, raza, sexo, edad, peso, observaciones, fecha, foto);
				Animals.add(p);
			}
			rs.close();
			conexion.CloseConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Animals;
	}

	/* Insertará una nueva Animal en la base de datos */
	public void insertAnimal(Animal Animal) {

		String nombre = Animal.getNombre();
		String especie = Animal.getEspecie();
		String raza = Animal.getRaza();
		String sexo = Animal.getSexo();
		int edad = Animal.getEdad();
		long peso = Animal.getPeso();
		String observaciones = Animal.getObservaciones();
		Date fecha = Animal.getFecha();
		String foto = Animal.getFoto();

		conexion = new ConexionBDD();
		String consulta = "INSERT INTO animales (nombre, especie, raza, sexo, edad, peso, observaciones, fecha, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			// Set values for the prepared statement
			pstmt.setString(1, nombre);
			pstmt.setString(2, especie);
			pstmt.setString(3, raza);
			pstmt.setString(4, sexo);
			pstmt.setInt(5, edad);
			pstmt.setLong(6, peso);
			pstmt.setString(7, observaciones);
			pstmt.setDate(8, fecha);
			pstmt.setString(9, foto);

			// Execute the query
			pstmt.executeUpdate();
			System.out.println("Data inserted successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* Modificará una Animal de la base de datos */
	public void modAnimal(Animal Animal) {
		
		int id = Animal.getID();
		String nombre = Animal.getNombre();
		String especie = Animal.getEspecie();
		String raza = Animal.getRaza();
		String sexo = Animal.getSexo();
		int edad = Animal.getEdad();
		long peso = Animal.getPeso();
		String observaciones = Animal.getObservaciones();
		Date fecha = Animal.getFecha();
		String foto = Animal.getFoto();

		try {
			conexion = new ConexionBDD();
			String consulta = "UPDATE your_table_name SET nombre=?, especie=?, raza=?, sexo=?, edad=?, peso=?, observaciones=?, fecha=?, foto=? WHERE id=?";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.setString(1, nombre);
			pstmt.setString(2, especie);
			pstmt.setString(3, raza);
			pstmt.setString(4, sexo);
			pstmt.setInt(5, edad);
			pstmt.setLong(6, peso);
			pstmt.setString(7, observaciones);
			pstmt.setDate(8, fecha);
			pstmt.setString(9, foto);
			pstmt.setInt(10, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Eliminará una Animal en la base de datos */
	public void eliminarAnimal(Animal Animal) {

		int idAnimal = Animal.getID();

		try {
			conexion = new ConexionBDD();
			String consulta = "DELETE FROM Animal WHERE id = " + idAnimal + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}