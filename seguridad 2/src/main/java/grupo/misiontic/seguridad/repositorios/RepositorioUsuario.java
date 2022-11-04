package grupo.misiontic.seguridad.repositorios;

import grupo.misiontic.seguridad.modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

// mogo hace la libreria crud base de datos
public interface RepositorioUsuario extends MongoRepository<Usuario, String>{

}
