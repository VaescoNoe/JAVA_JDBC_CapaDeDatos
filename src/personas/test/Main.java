package personas.test;

import java.sql.SQLException;
import java.util.List;
import personas.dto.PersonaDTO;
import personas.jdbc.PersonaDao;
import personas.jdbc.PersonaDaoJDBC;

public class Main {

	public static void main(String[] args) {
		
		PersonaDao personaDao = new PersonaDaoJDBC();
		
		PersonaDTO persona = new PersonaDTO();
		persona.setNombre("Antonio");
		persona.setApellido("Alonso");
		
		try {
			//personaDao.insert(persona);
			
			//personaDao.delete(new PersonaDTO(1));
			
			//persona.setId_persona(1);
			//personaDao.update(persona);
			
			select(personaDao.select());
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	private static void select(List<PersonaDTO> listaPersonas) {
		for(PersonaDTO p:listaPersonas) {
			System.out.println(p);
		}
	}
}
