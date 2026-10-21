package mx.edu.utez.proyecto1E.service.usuario;

import mx.edu.utez.proyecto1E.controller.usuario.dto.RequestCreateUserDto;
import mx.edu.utez.proyecto1E.controller.usuario.dto.RequestUpdateUserDto;
import mx.edu.utez.proyecto1E.controller.usuario.dto.RequestUpdateUserStatusDto;
import mx.edu.utez.proyecto1E.exception.customExceptions.CustomBadRequestException;
import mx.edu.utez.proyecto1E.model.persona.Persona;
import mx.edu.utez.proyecto1E.model.usuario.Roles;
import mx.edu.utez.proyecto1E.model.usuario.Usuario;
import mx.edu.utez.proyecto1E.repository.persona.PersonaRepository;
import mx.edu.utez.proyecto1E.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    //inyeccion de dependencias
    private final UsuarioRepository usuarioRepository;

    private final PersonaRepository personaRepository;


    public UsuarioService(UsuarioRepository usuarioRepository, PersonaRepository personaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
    }

    //Servicios
    public List<Usuario> getAllUsers(){
        return usuarioRepository.findAll();
    }

    public Usuario getUserById(Long id){
        if (id == null || id <= 0){
            throw new CustomBadRequestException("El id del usuario debe ser positivo");
        }

        return usuarioRepository.findById(id)
                .orElseThrow(() -> new CustomBadRequestException("El usuario no existe"));
    }

    @Transactional
    public Usuario updateUser(Long id, RequestUpdateUserDto payload){
        Usuario usuario = getUserById(id);
        Optional<Usuario> usuarioExist = usuarioRepository.findByUsername(payload.getUsername());

        if (usuarioExist.isPresent() && !usuarioExist.get().getId().equals(usuario.getId())){
            throw new CustomBadRequestException("el username ya existe");
        }

        //Modificar la entidad existente para conservar su persona y los demas datos
        usuario.setUsername(payload.getUsername());
        usuario.setDescripcion(payload.getDescripcion());

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario updateUserStatus(Long id, RequestUpdateUserStatusDto payload){
        Usuario usuario = getUserById(id);
        usuario.set_enable(payload.getEnable());

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public  Usuario createUser(RequestCreateUserDto payload){
        //buscar si el usuario no esta registrado

        Optional<Usuario> usuarioExist = usuarioRepository.findByUsername(payload.getUsername());

        if (usuarioExist.isPresent()){
            throw  new CustomBadRequestException("el username ya existe");
        }

        //crear persona
        Persona newPersona = new Persona();
        newPersona.setNombres(payload.getNombres());
        newPersona.setPrimerApellido(payload.getPrimerApellido());
        newPersona.setSegundoApellido(payload.getSegundoApellido());
        newPersona.setCurp(payload.getCurp());
        newPersona.setEmail(payload.getCorreo());
        newPersona.setFechaNacimiento(payload.getFechaNacimiento());

        //Obtener los datos de la persona recien registrada pero ya como entidad en mi bd
        newPersona = personaRepository.save(newPersona);

        //crear el usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(payload.getUsername());
        nuevoUsuario.setPassword(payload.getPassword());
        nuevoUsuario.setDescripcion(payload.getDescripcion());

        nuevoUsuario.setRol(Roles.valueOf(payload.getRol()));
        nuevoUsuario.set_enable(true);

        //Relacionar el registro de persona con el nuevo usuario
        nuevoUsuario.setPersona(newPersona);

        return usuarioRepository.save(nuevoUsuario);
    }
}
