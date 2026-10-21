package mx.edu.utez.proyecto1E.controller.usuario;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1E.controller.usuario.dto.RequestCreateUserDto;
import mx.edu.utez.proyecto1E.controller.usuario.dto.RequestUpdateUserDto;
import mx.edu.utez.proyecto1E.controller.usuario.dto.RequestUpdateUserStatusDto;
import mx.edu.utez.proyecto1E.model.usuario.Usuario;
import mx.edu.utez.proyecto1E.service.usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/usuario")
public class UsuarioController {

    //inyeccion de de dependencias
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Servicios expuestos
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsers(){
        return  ResponseEntity
                .status(200)
                .body(
                        usuarioService.getAllUsers()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id){
        return ResponseEntity
                .status(200)
                .body(usuarioService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id,
                                            @RequestBody @Valid RequestUpdateUserDto payload){
        return ResponseEntity
                .status(200)
                .body(usuarioService.updateUser(id, payload));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Usuario> updateUserStatus(@PathVariable Long id,
                                                  @RequestBody @Valid RequestUpdateUserStatusDto payload){
        return ResponseEntity
                .status(200)
                .body(usuarioService.updateUserStatus(id, payload));
    }

    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody @Valid RequestCreateUserDto payload){
        return ResponseEntity
                .status(201)
                .body(
                        usuarioService.createUser(payload)
                );
    }
}
