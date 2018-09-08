package personas.jdbc;

import java.sql.SQLException;
import java.util.List;
import personas.dto.PersonaDTO;

public interface PersonaDao {
	
	public void insert(PersonaDTO persona)throws SQLException;
	public void update(PersonaDTO persona)throws SQLException;
	public void delete(PersonaDTO persona)throws SQLException;
	public void deleteAll()throws SQLException;
	public List<PersonaDTO> select()throws SQLException;

}
