package grupo.misiontic.seguridad.repositorios;

import grupo.misiontic.seguridad.modelos.PermisoRol;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioPermisoRol extends MongoRepository<PermisoRol, String> {
}
