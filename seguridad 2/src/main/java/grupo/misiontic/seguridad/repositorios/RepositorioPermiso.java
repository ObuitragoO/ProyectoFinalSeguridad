package grupo.misiontic.seguridad.repositorios;

import grupo.misiontic.seguridad.modelos.Permiso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioPermiso extends MongoRepository<Permiso,String> {

}
