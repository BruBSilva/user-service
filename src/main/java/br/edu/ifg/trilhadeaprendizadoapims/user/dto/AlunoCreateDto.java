package br.edu.ifg.trilhadeaprendizadoapims.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AlunoCreateDto extends UsuarioCreateDto{

    private int xpTotal;
    private int nivel;
    private LocalDateTime dataCadastro;

}
