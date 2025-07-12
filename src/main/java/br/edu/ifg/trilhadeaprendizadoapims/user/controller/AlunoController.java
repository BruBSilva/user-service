package br.edu.ifg.trilhadeaprendizadoapims.user.controller;

import br.edu.ifg.trilhadeaprendizadoapims.user.dto.AlunoCreateDto;
import br.edu.ifg.trilhadeaprendizadoapims.user.dto.AlunoDto;
import br.edu.ifg.trilhadeaprendizadoapims.user.service.AlunoService;
import br.edu.ifg.trilhadeaprendizadoapims.user.service.UsuarioAbstractService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario/aluno")
public class AlunoController extends UsuarioController<AlunoDto, AlunoCreateDto>{

    @Autowired
    private AlunoService alunoService;

    @Override
    protected UsuarioAbstractService<AlunoDto, AlunoCreateDto> getService() {
        return alunoService;
    }

    @GetMapping
    public Page<AlunoDto> buscarTodos(@PageableDefault(size = 10) Pageable paginacao) {
        return alunoService.buscarTodos(paginacao);
    }

    @PostMapping
    public ResponseEntity<AlunoDto> inserir(@RequestBody @Valid AlunoCreateDto dto, UriComponentsBuilder uriBuilder) {
        AlunoDto dtoCriado = alunoService.inserir(dto);
        URI endereco = uriBuilder.path("/aluno/{id}").buildAndExpand(dtoCriado.getId()).toUri();
        return ResponseEntity.created(endereco).body(dtoCriado);
    }
}
