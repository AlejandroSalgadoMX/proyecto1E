package mx.edu.utez.proyecto1E.controller.usuario.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class RequestUpdateUserStatusDto {
    @NotNull(message = "El estado del usuario es obligatorio")
    private Boolean enable;
}
