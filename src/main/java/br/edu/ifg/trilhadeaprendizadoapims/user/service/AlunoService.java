package br.edu.ifg.trilhadeaprendizadoapims.user.service;

import br.edu.ifg.trilhadeaprendizadoapims.user.dto.AlunoCreateDto;
import br.edu.ifg.trilhadeaprendizadoapims.user.dto.AlunoDto;
import br.edu.ifg.trilhadeaprendizadoapims.user.model.Aluno;
import br.edu.ifg.trilhadeaprendizadoapims.user.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AlunoService implements UsuarioAbstractService<AlunoDto, AlunoCreateDto> {

    @Autowired
    private AlunoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AlunoDto buscarPorId(Long id) {
        Aluno entidade = repository.findById(id).orElseThrow( () -> new EntityNotFoundException());
        return modelMapper.map(entidade, AlunoDto.class);
    }

    @Override
    public AlunoDto atualizar(Long id, AlunoDto dto) {
        Aluno entidadeAntiga = repository.findById(id).orElseThrow( () -> new EntityNotFoundException());
        Aluno entidade = modelMapper.map(dto, Aluno.class);
        entidade.setId(id);
        entidade.setSenhaHash(entidadeAntiga.getSenhaHash());
        repository.save(entidade);

        return modelMapper.map(entidade, AlunoDto.class);
    }

    @Override
    public Page<AlunoDto> buscarTodos(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(entidade -> modelMapper.map(entidade, AlunoDto.class));
    }

    @Override
    public AlunoDto inserir(AlunoCreateDto dto) {
        Aluno entidade = modelMapper.map(dto, Aluno.class);
        entidade.setDataCadastro(LocalDateTime.now());
        entidade.setNivel(0);
        entidade.setXpTotal(0);
        repository.save(entidade);

        return modelMapper.map(entidade, AlunoDto.class);
    }

    @Override
    public void excluir(Long id) {
        Aluno entidade = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));

        repository.deleteById(id);

    }
}
