package conexion;

import java.sql.Connection;
import java.sql.SQLException;

public class Test_Conexion {

	public static void main(String[] args) throws SQLException {
		Conexion con=new Conexion();
		Connection conexion= con.recuperaConexion();
		
		System.out.println("Conexion exitosa!");
		conexion.close();
	}
}
