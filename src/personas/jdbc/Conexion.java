package personas.jdbc;

import java.sql.*;

public class Conexion {

	private final static String JDBC_DRIVER="com.mysql.jdbc.Driver";
	private final static String JDBC_URL="jdbc:mysql://localhost:3306/sga?useSSL=false";
	private final static String JDBC_USER="root";
	private final static String JDBC_PASS="";
	private static Driver driver =null;
	
	public static synchronized Connection getConnection() throws SQLException{
		if(driver == null) {
			try {
				Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
				driver = (Driver) jdbcDriverClass.newInstance();
				DriverManager.registerDriver(driver);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Fallo la carga de la conexion");
			}
		}
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement ps) {
		try {
			if(ps!=null) {
				ps.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn) {
		try {
			if(conn!=null) {
				conn.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
