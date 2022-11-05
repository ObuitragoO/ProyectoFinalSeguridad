package grupo.misiontic.seguridad.controladores;


import grupo.misiontic.seguridad.modelos.Permiso;
import grupo.misiontic.seguridad.modelos.PermisoRol;
import grupo.misiontic.seguridad.modelos.Rol;
import grupo.misiontic.seguridad.repositorios.RepositorioPermiso;
import grupo.misiontic.seguridad.repositorios.RepositorioPermisoRol;
import grupo.misiontic.seguridad.repositorios.RepositorioRol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/permiso-rol")
public class ControladorPermisoRol {

    @Autowired
    private RepositorioPermisoRol miRepositorioPermisoRol;
    @Autowired
    private RepositorioRol miRepositorioRol;
    @Autowired
    private RepositorioPermiso miRepositorioPermiso;

    @GetMapping
    public List<PermisoRol> buscarTodosLosPermisoRoles(){
        log.info("Find all permiso rol");
        return miRepositorioPermisoRol.findAll();
    }

    @PostMapping("rol/{idRol}/permiso/{idPermiso}")
    public PermisoRol crearPermisoRol(@PathVariable String idRol,@PathVariable String idPermiso){
        Rol rol = miRepositorioRol.findById(idRol).orElse(null);
        Permiso permiso = miRepositorioPermiso.findById(idPermiso).orElse(null);

        if(rol!=null&&permiso!=null){
            PermisoRol permisoRol = new PermisoRol(rol,permiso);
            return miRepositorioPermisoRol.save(permisoRol);
        }else {
            return null;
        }
    }

    // consultar un idRolPermiso
    @GetMapping("{idRolPermiso}")
    public PermisoRol buscarPermisoRol(@PathVariable String idRolPermiso){
        PermisoRol permisoRol = miRepositorioPermisoRol.findById(idRolPermiso).orElse(null);
        if(permisoRol!=null){
            log.info("show one RolPermiso"+idRolPermiso);
            return miRepositorioPermisoRol.findById(idRolPermiso).orElse(new PermisoRol(null,null));
        }else{
            log.error("fail to find RolPermiso");
            return null;
        }

    }

    // borrar por idRolPermiso
    @DeleteMapping("{idRolPermiso}")
    public void borrarIdRolPermiso(@PathVariable String idRolPermiso){
        PermisoRol permisoRol = miRepositorioPermisoRol.findById(idRolPermiso).orElse(null);
        if(permisoRol!=null){
            miRepositorioPermisoRol.deleteById(idRolPermiso);
            log.info("the idRolPermiso is deleted "+idRolPermiso);
        }else {
            log.error("sorry, idRolPermiso is null ");
        }
    }

    // modificacion
    @PutMapping("{idRolPermiso}")
    public PermisoRol modificarRolPermiso(@PathVariable String idRolPermiso,@RequestBody PermisoRol infopermisoRol){
        PermisoRol permisoRolActual = miRepositorioPermisoRol.findById(idRolPermiso).orElse(null);
        if(permisoRolActual!=null){
            log.info("User find in DataBase {}",idRolPermiso);
            permisoRolActual.setRol(infopermisoRol.getRol());
            permisoRolActual.setPermiso(infopermisoRol.getPermiso());
            log.info("update User {}",permisoRolActual);
            return miRepositorioPermisoRol.save(permisoRolActual);
        }else{
            log.error("sorry, idRolPermiso is null ");
            return null;
        }
    }
}