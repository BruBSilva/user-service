package br.edu.ifg.trilhadeaprendizadoapims.user.service;

import br.edu.ifg.trilhadeaprendizadoapims.user.dto.AdminCreateDto;
import br.edu.ifg.trilhadeaprendizadoapims.user.dto.AdministradorDto;
import br.edu.ifg.trilhadeaprendizadoapims.user.model.Administrador;
import br.edu.ifg.trilhadeaprendizadoapims.user.repository.AdministradorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService implements UsuarioAbstractService<AdministradorDto, AdminCreateDto>{

    @Autowired
    private AdministradorRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AdministradorDto buscarPorId(Long id) {
        Administrador entidade = repository.findById(id).orElseThrow( () -> new EntityNotFoundException());
        return modelMapper.map(entidade, AdministradorDto.class);
    }

    @Override
    public AdministradorDto atualizar(Long id, AdministradorDto dto) {
        Administrador entidadeAntiga = repository.findById(id).orElseThrow( () -> new EntityNotFoundException());
        Administrador entidade = modelMapper.map(dto, Administrador.class);
        entidade.setId(id);
        entidade.setSenhaHash(entidadeAntiga.getSenhaHash());
        repository.save(entidade);

        return modelMapper.map(entidade, AdministradorDto.class);
    }

    public Page<AdministradorDto> buscarTodos(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(entidade -> modelMapper.map(entidade, AdministradorDto.class));
    }

    public AdministradorDto inserir(AdminCreateDto dto) {
        Administrador entidade = modelMapper.map(dto, Administrador.class);
        repository.save(entidade);

        return modelMapper.map(entidade, AdministradorDto.class);
    }

    public void excluir(Long id) {

        Administrador entidade = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin não encontrado"));

        long totalAdmins = repository.count();
        if (totalAdmins <= 1) {
            throw new IllegalStateException("Não é permitido remover o único administrador do sistema.");
        }

        repository.deleteById(id);
    }
}
