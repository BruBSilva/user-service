package br.edu.ifg.trilhadeaprendizadoapims.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AlunoCreateDto extends UsuarioCreateDto{

    @NotNull
    private int xpTotal;
    @NotNull
    private int nivel;

    private LocalDateTime dataCadastro;

}
