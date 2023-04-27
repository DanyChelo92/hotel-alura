package Controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import conexion.Conexion;
import dao.HuespedDAO;
import models.Huesped;

public class ControllerHuesped {

	private HuespedDAO huesped_dao;
	
	public ControllerHuesped() {
		Connection con=new Conexion().recuperaConexion();
		this.huesped_dao= new HuespedDAO(con);
	}
	
	public void guardar(Huesped huesped) {
		this.huesped_dao.guardarHuesped(huesped);
	}
	
	public List<Huesped> mostrarHuespedes(){
		return this.huesped_dao.mostrarHuespedes();
	}
	
	public List<Huesped> mostrarHuespedId(String id){
		return this.huesped_dao.mostrarHuespedId(id);
	}
	
	public void editarHuesped(int id,String nombre,String apellido,LocalDate fecha_nacimiento,String nacionalidad,String telefono,int id_reserva) {
		this.huesped_dao.editarHuesped(id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva);
	}
	
	public void eliminarHuesped(int id) {
		this.huesped_dao.eliminarHuesped(id);	
	}
}
