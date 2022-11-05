package grupo.misiontic.seguridad.controladores;

import grupo.misiontic.seguridad.modelos.Rol;
import grupo.misiontic.seguridad.modelos.Usuario;
import grupo.misiontic.seguridad.repositorios.RepositorioRol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    @GetMapping("{idRol}")
    public Rol buscarRol(@PathVariable String idRol){
        return miRepositorioRol.findById(idRol).orElse(new Rol("",""));
    }

}
