package br.edu.ifg.trilhadeaprendizadoapims.user.dto;

import br.edu.ifg.trilhadeaprendizadoapims.user.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {

    private Long id;
    private String nome;
    private String email;
    private Role role;

}
