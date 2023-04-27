package Controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import conexion.Conexion;
import dao.ReservaDAO;
import models.Reserva;

public class ControllerReserva {
	
	private ReservaDAO reserva_dao;
	
	
	
	public ControllerReserva() {
		Connection con = new Conexion().recuperaConexion();
		this.reserva_dao= new ReservaDAO(con);
	}
	
	public void guardar (Reserva reserva) {
		this.reserva_dao.guardar_reserva(reserva);
		
	}
	
	public List<Reserva> mostrar(){
		return this.reserva_dao.mostrar();
	}
	
	public List<Reserva> mostrar_reserva_id(String id){
		return this.reserva_dao.mostrar_reserva_id(id);
	}
	
	public void editarReserva(Integer id,LocalDate fecha_entrada, LocalDate fecha_salida,String valor, String forma_pago) {
		this.reserva_dao.editarReserva(id, fecha_entrada, fecha_salida, valor, forma_pago);
	}
	
	public void eliminarReserva(int id) {
		this.reserva_dao.eliminarReserva(id);
	}

}
