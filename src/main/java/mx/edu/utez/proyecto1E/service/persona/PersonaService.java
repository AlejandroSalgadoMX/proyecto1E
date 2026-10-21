package mx.edu.utez.proyecto1E.service.persona;

import mx.edu.utez.proyecto1E.controller.persona.dto.RequestUpdatePersonaDto;
import mx.edu.utez.proyecto1E.exception.customExceptions.CustomBadRequestException;
import mx.edu.utez.proyecto1E.model.persona.Persona;
import mx.edu.utez.proyecto1E.repository.persona.PersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaService {
    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Transactional
    public Persona updatePersona(Long id, RequestUpdatePersonaDto payload){
        if (id == null || id <= 0){
            throw new CustomBadRequestException("El id de la persona debe ser positivo");
        }

        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new CustomBadRequestException("La persona no existe"));

        persona.setNombres(payload.getNombres());
        persona.setPrimerApellido(payload.getPrimerApellido());
        persona.setSegundoApellido(payload.getSegundoApellido());
        persona.setEmail(payload.getCorreo());
        persona.setCurp(payload.getCurp());
        persona.setFechaNacimiento(payload.getFechaNacimiento());

        return personaRepository.save(persona);
    }
}
