package grupo.misiontic.seguridad.controladores;

import grupo.misiontic.seguridad.modelos.Usuario;
import grupo.misiontic.seguridad.repositorios.RepositorioUsuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Slf4j
@CrossOrigin  // de que usuarios se van a conectar
@RestController //servicio rest

@RequestMapping("/usuario") //mapeo de valores desde postman
public class ControladorUsuario {
    @Autowired // inicializar objetos NEW
    private RepositorioUsuario miRepositorioUsuario;

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping // servicio GET
    public List<Usuario> buscarTodosLosUsuarios(){
        log.info("Find all Users");
        return miRepositorioUsuario.findAll();
    }


    @GetMapping("{idUsuario}")
    public Usuario buscarUsuario(@PathVariable String idUsuario){
        return miRepositorioUsuario.findById(idUsuario).orElse(new Usuario("","",""));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario infoUsuario){

        log.info("Create new user..");
        String contrasenaCifrada = convertirSHA256(infoUsuario.getContrasena());
        infoUsuario.setContrasena(contrasenaCifrada);
        return miRepositorioUsuario.save(infoUsuario);

    }

    // code hiden password
    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
