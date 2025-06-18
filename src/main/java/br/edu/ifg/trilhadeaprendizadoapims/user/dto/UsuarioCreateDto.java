package br.edu.ifg.trilhadeaprendizadoapims.user.dto;

import br.edu.ifg.trilhadeaprendizadoapims.user.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCreateDto {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Role role;

}
