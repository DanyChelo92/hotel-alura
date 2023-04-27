package conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Conexion {
	
private DataSource data_source;
	
	public Conexion() {
		var pooledDataSource= new ComboPooledDataSource();
		
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("Cafayate123");
		
		this.data_source=pooledDataSource;
	}
	
	public Connection recuperaConexion(){
		try {
			return this.data_source.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en la conexion");
			throw new RuntimeException(e);
		}
	}

}
