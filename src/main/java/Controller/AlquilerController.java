package Controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.google.gson.Gson;

import Connection.DBConnection;
import Modelos.Alquiler;

public class AlquilerController implements IAlquilerController{

	@Override
	public String listarAlquileres(String username) {

		Gson gson = new Gson();
		DBConnection con = new DBConnection();
		
		String sql = "SELECT l.id, l.titulo, l.genero, l.novedad, a.fecha, a.username FROM libros l " 
				+ "JOIN alquiler a ON l.id = a.id "
				+ "JOIN usuarios u ON a.username = u.username "
				+ "WHERE a.username = '" + username + "'";
		
		List<String> alquileres = new ArrayList<String>();
		
		try {
			Statement st = con.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String genero = rs.getString("genero");
				boolean novedad = rs.getBoolean("novedad");
				Date fechaAlquiler = rs.getDate("fecha");
				
				Alquiler alquiler = new Alquiler (id, titulo, fechaAlquiler, novedad, genero);
				
				alquileres.add(gson.toJson(alquiler));
				
			}
			
		} catch (Exception ex){
			System.out.println(ex.getMessage());
		} finally {
			con.desconectar();
		}
		
		return gson.toJson(alquileres);
	}

}
