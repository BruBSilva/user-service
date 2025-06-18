package br.edu.ifg.trilhadeaprendizadoapims.user.controller;

import br.edu.ifg.trilhadeaprendizadoapims.user.dto.UsuarioCreateDto;
import br.edu.ifg.trilhadeaprendizadoapims.user.dto.UsuarioDto;
import br.edu.ifg.trilhadeaprendizadoapims.user.service.UsuarioAbstractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class UsuarioController<T extends UsuarioDto, K extends UsuarioCreateDto> {

    protected abstract UsuarioAbstractService<T,K> getService();

    @GetMapping("/{id}")
    public ResponseEntity<T> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(getService().buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        getService().excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> atualizar(@PathVariable Long id, @RequestBody T dto) {
        return ResponseEntity.ok(getService().atualizar(id, dto));
    }
}
