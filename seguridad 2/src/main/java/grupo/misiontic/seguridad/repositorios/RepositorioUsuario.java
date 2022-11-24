package grupo.misiontic.seguridad.repositorios;

import grupo.misiontic.seguridad.modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

// mogo hace la libreria crud base de datos
public interface RepositorioUsuario extends MongoRepository<Usuario, String>{
    @Query("{'correo': ?0}")
    public Usuario getUserByEmail(String correo);

}
