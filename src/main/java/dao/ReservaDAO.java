package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;



import models.Reserva;

public class ReservaDAO {
	
	private Connection con;
	
	public ReservaDAO() {
		
	}
	
	public ReservaDAO(Connection con) {
		this.con=con;
	}

	public void guardar_reserva(Reserva reserva) {
		try {
			String query="insert into reservas (fecha_entrada,fecha_salida,valor,forma_pago) values(?,?,?,?)";
			PreparedStatement pstm=con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			try (pstm){
				pstm.setObject(1, reserva.getFecha_entrada());
				pstm.setObject(2, reserva.getFecha_salida());
				pstm.setObject(3, reserva.getValor());
				pstm.setObject(4, reserva.getForma_pago());
				
				pstm.executeUpdate();
				ResultSet rs=pstm.getGeneratedKeys();
				try(rs){
					while(rs.next()) {
						reserva.setId(rs.getInt(1));					
						}
					}
				}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Reserva> mostrar(){
		List<Reserva>reservas= new ArrayList<>();
		try {
			String query="select * from reservas";
			PreparedStatement stm= con.prepareStatement(query);
			try(stm){
				stm.execute();
				convertirResultado(reservas,stm);
			}
			return reservas;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> mostrar_reserva_id(String id){
		List<Reserva>reservas= new ArrayList<>();
		try {
			String query="select * from reservas where id=?";
			PreparedStatement stm= con.prepareStatement(query);
			try(stm){
				stm.setString(1, id);
				stm.execute();
				convertirResultado(reservas,stm);
			}
			return reservas;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void editarReserva(Integer id,LocalDate fecha_entrada, LocalDate fecha_salida,String valor, String forma_pago) {
		String query="update reservas set fecha_entrada=?, fecha_salida=?,valor=?,forma_pago=? where id=?";
		try(PreparedStatement stm= con.prepareStatement(query)){
			
			//stm.setObject(1, java.sql.Date.valueOf(fecha_entrada));
			//stm.setObject(2, java.sql.Date.valueOf(fecha_salida));
			stm.setObject(1, fecha_entrada);
			stm.setObject(2, fecha_salida);
			stm.setString(3, valor);
			stm.setString(4, forma_pago);
			stm.setInt(5, id);
			stm.execute();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void eliminarReserva(int id) {
		String query="delete from reservas where id=?";
		try {
			Statement stm= con.createStatement();
			stm.execute("set foreign_key_checks=0");
			PreparedStatement pstm=con.prepareStatement(query);
			pstm.setInt(1, id);
			pstm.execute();
			stm.execute("set foreign_key_checks=1");
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void convertirResultado(List<Reserva> reserva, PreparedStatement stm) throws SQLException {
		ResultSet rs=stm.getResultSet();
		try (rs){
			while (rs.next()) {
			int id=rs.getInt("id");
				LocalDate fecha_entrada= rs.getDate("fecha_entrada").toLocalDate().plusDays(0);
				LocalDate fecha_salida= rs.getDate("fecha_salida").toLocalDate().plusDays(0);
				String valor= rs.getString("valor");
				String forma_pago=rs.getString("forma_pago");
				
				Reserva producto= new Reserva(id,fecha_entrada,fecha_salida,valor,forma_pago);
				reserva.add(producto);
			}
		}
	}
}
