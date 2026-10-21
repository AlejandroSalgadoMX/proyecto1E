package mx.edu.utez.proyecto1E.model.persona;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.proyecto1E.model.cursos.Curso;
import mx.edu.utez.proyecto1E.model.direccion.Direccion;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "personas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres;
    private String primerApellido;
    private String segundoApellido;
    private String curp;
    private String email;
    private LocalDate fechaNacimiento;
/*
    @ManyToMany
    @JoinTable(
            name = "persona_cursos",
            joinColumns = @JoinColumn(name = "persona_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private List<Curso> cursos;//
*/

}
