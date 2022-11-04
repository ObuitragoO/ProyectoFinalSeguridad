package grupo.misiontic.seguridad.repositorios;

import grupo.misiontic.seguridad.modelos.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioRol extends MongoRepository<Rol,String> {
}
