package grupo.misiontic.seguridad.modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class PermisoRol {

    @Id
    private String _id;

    @DBRef
    private Rol rol;
    @DBRef
    private Permiso permiso;


    public PermisoRol(Rol rol, Permiso permiso) {
        this.rol = rol;
        this.permiso = permiso;
    }
}
