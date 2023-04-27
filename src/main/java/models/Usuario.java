package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

import conexion.Conexion;

public class Usuario {

	private String usuario;
	private String contrasena;
	
	public Usuario() {
		
	}
	
	public Usuario (String usuario, String contrasena) {
		this.usuario=usuario;
		this.contrasena=contrasena;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	public static boolean validar_usuario(String usuario,String contrasena) {
		Conexion con = new Conexion();
		Connection conexion=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		try {
			conexion= con.recuperaConexion();
			stm=conexion.prepareStatement("select * from usuarios where usuario=? and contrasena=?");
			stm.setString(1, usuario);
			stm.setString(2, contrasena);
			rs=stm.executeQuery();
			return rs.next();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			}catch (SQLException er) {
				er.printStackTrace();
				//throw new RuntimeErrorException(er)
			}
		}
	}
}
