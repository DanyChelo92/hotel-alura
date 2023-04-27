package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import javax.management.RuntimeErrorException;


import models.Huesped;
import models.Reserva;

public class HuespedDAO {

	private Connection con;
	
	public HuespedDAO(Connection con) {
		super();
		this.con=con;
	}
	
	public void guardarHuesped(Huesped huesped) {
		try {
			String query="insert into huespedes (nombre,apellido,fecha_nacimiento,nacionalidad,telefono,id_reserva) values(?,?,?,?,?,?)";
			PreparedStatement stm=con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			try(stm){
				stm.setString(1, huesped.getNombre());
				stm.setString(2, huesped.getApellido());
				stm.setObject(3, huesped.getFecha_nacimiento());
				stm.setString(4, huesped.getNacionalidad());
				stm.setString(5, huesped.getTelefono());
				stm.setInt(6, huesped.getId_reserva());
				stm.execute();
				ResultSet rs= stm.getGeneratedKeys();
				try(rs){
					while (rs.next()) {
						huesped.setId(rs.getInt(1));
					}
				}
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huesped> mostrarHuespedes(){
		List<Huesped>huespedes= new ArrayList<>();
		try {
			String query="select * from huespedes";
			PreparedStatement stm= con.prepareStatement(query);
			try(stm){
				stm.execute();
				convertirResultado(huespedes,stm);
			}
			return huespedes;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huesped> mostrarHuespedId(String id){
		List<Huesped>huesped= new ArrayList<>();
		try {
			String query="select * from huespedes where id=?";
			PreparedStatement stm= con.prepareStatement(query);
			try(stm){
				stm.setString(1, id);
				stm.execute();
				convertirResultado(huesped,stm);
			}
			return huesped;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void convertirResultado(List<Huesped> huespedes, PreparedStatement stm) throws SQLException {
		ResultSet rs=stm.getResultSet();
		try (rs){
			while (rs.next()) {
			int id=rs.getInt("id");
				String nombre= rs.getString("nombre");
				String apellido= rs.getString("apellido");
				LocalDate fecha_nacimiento= rs.getDate("fecha_nacimiento").toLocalDate().plusDays(0);
				String nacionalidad= rs.getString("nacionalidad");
				String telefono= rs.getString("telefono");
				int id_reserva=rs.getInt("id_reserva");
				
				Huesped huesped= new Huesped(id,nombre,apellido,fecha_nacimiento,nacionalidad,telefono,id_reserva);
				huespedes.add(huesped);
			}
		}
	}
	
	public void editarHuesped(int id,String nombre,String apellido,LocalDate fecha_nacimiento,String nacionalidad,String telefono,int id_reserva) {
		String query="update huespedes set nombre=?,apellido=?,fecha_nacimiento=?,nacionalidad=?,telefono=?,id_reserva=? where id=?";
		try(PreparedStatement stm= con.prepareStatement(query)){
			stm.setString(1, nombre);
			stm.setString(2, apellido);
			stm.setObject(3, fecha_nacimiento);
			stm.setString(4, nacionalidad);
			stm.setString(5, telefono);
			stm.setInt(6, id_reserva);
			stm.setInt(7, id);
			
			stm.execute();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void eliminarHuesped(int id) {
		String query="delete from huespedes where id=?";
		try {
			PreparedStatement pstm=con.prepareStatement(query);
			pstm.setInt(1, id);
			pstm.execute();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
