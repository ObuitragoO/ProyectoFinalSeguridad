package grupo.misiontic.seguridad.modelos;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Rol {

    @Id
    private String _id;

    private String nombre;
    private String descripcion;

    public Rol(String nombre,String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

}
