package grupo.misiontic.seguridad.controladores;

import grupo.misiontic.seguridad.modelos.Permiso;
import grupo.misiontic.seguridad.repositorios.RepositorioPermiso;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController

@RequestMapping("/permiso")
public class ControladorPermiso {

    //objeto de permiso
    @Autowired
    private RepositorioPermiso miRepositorioPermiso;

    // consultar todos los permisos
    @GetMapping
    public List<Permiso> buscarTodosLosPermisos(){
        log.info("Find all Pemisos...");
        return miRepositorioPermiso.findAll();
    }

    // crear un nuevo rol
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Permiso crearPermiso(@RequestBody Permiso infoPermiso){
        log.info("Create new Permiso..");
        return miRepositorioPermiso.save(infoPermiso);
    }

    // consultar por permiso

    @GetMapping("{idPermiso}")
    public Permiso buscarPermiso(@PathVariable String idPermiso){
        return miRepositorioPermiso.findById(idPermiso).orElse(new Permiso());
    }

    //eliminar permiso
    @DeleteMapping("{idPermiso}")
    public void eliminarPermiso(@PathVariable String idPermiso){
        Permiso permisoActual = miRepositorioPermiso.findById(idPermiso).orElse(null);
        if(permisoActual!=null){
            miRepositorioPermiso.deleteById(idPermiso);
            log.info("permiso is delete!"+idPermiso);
        }else{
            log.error("permiso is null!");
        }
    }

    // modificar Permiso
    @PutMapping("{idPermiso}")
    public Permiso modificarUsuario(@PathVariable String idPermiso,@RequestBody Permiso infoRol){
        Permiso permisoActual = miRepositorioPermiso.findById(idPermiso).orElse(null);

        if(permisoActual!=null){
            log.info("User find in DataBase {}",idPermiso);
            permisoActual.setUrl(infoRol.getUrl());
            permisoActual.setMetodo(infoRol.getMetodo());
            log.info("update Permiso {}",idPermiso);
            return miRepositorioPermiso.save(permisoActual);
        }else{
            return null;
        }

    }


}
