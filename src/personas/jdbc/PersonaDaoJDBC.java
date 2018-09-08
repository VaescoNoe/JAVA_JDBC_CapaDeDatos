package personas.jdbc;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import personas.dto.PersonaDTO;

public class PersonaDaoJDBC implements PersonaDao{
	
	private Connection userConn;
	
	private final String SQL_INSERT="INSERT INTO persona values(null,?,?)";
	
	private final String SQL_UPDATE="UPDATE persona SET nombre=?,apellido=? WHERE id_persona=?";
	
	private final String SQl_DELETE="DELETE FROM persona WHERE id_persona=?";
	private final String SQl_DELETE_ALL="DELETE FROM persona";

	private final String SQl_SELECT="SELECT * FROM persona ORDER BY id_persona";

	public PersonaDaoJDBC() {}
	
	public PersonaDaoJDBC(Connection conn) {
		this.userConn=conn;
	}

	@Override
	public void insert(PersonaDTO persona) throws SQLException {
		Connection conn = null;
		PreparedStatement ps =null;
		
		int rows =0;
		
		try {
			conn = (this.userConn!=null)?this.userConn:Conexion.getConnection();
			
			ps = conn.prepareStatement(SQL_INSERT);
			int index = 1;
			ps.setString(index++, persona.getNombre());
			ps.setString(index, persona.getApellido());
				System.out.println("Ejecutando query:"+SQL_INSERT);
			rows=ps.executeUpdate();
				System.out.println("Registros afectados:"+rows);
		}finally {
			Conexion.close(ps);
			if(this.userConn==null) {
				Conexion.close(conn);
			}
		}
		
	}

	@Override
	public void update(PersonaDTO persona) throws SQLException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		int rows = 0;
		
		try {
			conn = (this.userConn!=null)?this.userConn:Conexion.getConnection();
			ps = conn.prepareStatement(SQL_UPDATE);
			int index = 1;
			ps.setString(index++, persona.getNombre());
			ps.setString(index++, persona.getApellido());
			ps.setInt(index, persona.getId_persona());
				System.out.println("Ejecutando query: "+SQL_UPDATE);
			rows=ps.executeUpdate();
				System.out.println("Registros afectados:"+rows);
			
		}finally{
			Conexion.close(ps);
			if(this.userConn==null) {
				Conexion.close(conn);
			}
		}
		
	}

	@Override
	public void delete(PersonaDTO persona) throws SQLException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		int rows = 0;
		
		try {
			conn = (this.userConn!=null)?this.userConn:Conexion.getConnection();
			ps = conn.prepareStatement(SQl_DELETE);
			ps.setInt(1, persona.getId_persona());
				System.out.println("Ejecutando query: "+SQL_UPDATE);
			rows=ps.executeUpdate();
				System.out.println("Registros afectados:"+rows);
		}finally{
			Conexion.close(ps);
			if(this.userConn==null) {
				Conexion.close(conn);
			}
		}
	}

	@Override
	public void deleteAll() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		int rows = 0;
		
		try {
			conn = (this.userConn!=null)?this.userConn:Conexion.getConnection();
			ps = conn.prepareStatement(SQl_DELETE_ALL);
				System.out.println("Ejecutando query: "+SQl_DELETE_ALL);
			rows=ps.executeUpdate();
				System.out.println("Registros afectados:"+rows);
		}finally{
			Conexion.close(ps);
			if(this.userConn==null) {
				Conexion.close(conn);
			}
		}
		
		
	}

	@Override
	public List<PersonaDTO> select() throws SQLException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PersonaDTO persona = null;
		List<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		
		try {
			conn = (this.userConn!=null)?this.userConn:Conexion.getConnection();
			ps = conn.prepareStatement(SQl_SELECT);
				System.out.println("Ejecutando query: "+SQl_SELECT);
			rs = ps.executeQuery();
			while(rs.next()) {
				persona = new PersonaDTO();
				int index =1;
				persona.setId_persona(rs.getInt(index++));
				persona.setNombre(rs.getString(index++));
				persona.setApellido(rs.getString(index));
				personas.add(persona);
			}
		}finally{
			Conexion.close(rs);
			Conexion.close(ps);
			if(this.userConn==null) {
				Conexion.close(conn);
			}
		}
		return personas;
	}



}
