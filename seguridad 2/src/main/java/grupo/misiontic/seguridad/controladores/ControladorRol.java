package grupo.misiontic.seguridad.controladores;

import grupo.misiontic.seguridad.modelos.Rol;
import grupo.misiontic.seguridad.repositorios.RepositorioRol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController

@RequestMapping("/rol")
public class ControladorRol {

    @Autowired
    private RepositorioRol miRepositorioRol;

    // consultar todos los roles
    @GetMapping
    public List<Rol> buscarTodosLosRoles(){
        log.info("Find all Rol ....");
        return miRepositorioRol.findAll();
    }

    // crear un nuevo rol
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Rol crearRol(@RequestBody Rol infoRol){
        log.info("Create new user..");
        return miRepositorioRol.save(infoRol);
    }

    // consultar rol con ID
    @GetMapping("{idRol}")
    public Rol buscarRol(@PathVariable String idRol){
        return miRepositorioRol.findById(idRol).orElse(new Rol("",""));
    }

    //eliminar rol
    @DeleteMapping("{idRol}")
    public void eliminarRol(@PathVariable String idRol){
        Rol rolActual = miRepositorioRol.findById(idRol).orElse(null);
        if(rolActual!=null){
            miRepositorioRol.deleteById(idRol);
            log.info("Rol is delete!"+idRol);
        }else{
            log.error("Rol is null!");
        }

    }

    // modificar Rol
    @PutMapping("{idRol}")
    public Rol modificarUsuario(@PathVariable String idRol,@RequestBody Rol infoRol){
        Rol rolActual = miRepositorioRol.findById(idRol).orElse(null);

        if(rolActual!=null){
            log.info("User find in DataBase {}",idRol);
            rolActual.setNombre(infoRol.getNombre());
            rolActual.setDescripcion(infoRol.getDescripcion());
            log.info("update User {}",idRol);
            return miRepositorioRol.save(rolActual);
        }else{
            return null;
        }

    }

}
