package br.edu.ifg.trilhadeaprendizadoapims.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCreateDto {

    @NotNull
    private String nome;
    @NotNull
    private String email;
    @NotNull
    private String senha;

}
