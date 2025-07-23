package br.edu.ifg.trilhadeaprendizadoapims.user.service;

import br.edu.ifg.trilhadeaprendizadoapims.user.dto.AdminCreateDto;
import br.edu.ifg.trilhadeaprendizadoapims.user.dto.AdministradorDto;
import br.edu.ifg.trilhadeaprendizadoapims.user.model.Administrador;
import br.edu.ifg.trilhadeaprendizadoapims.user.model.enums.Role;
import br.edu.ifg.trilhadeaprendizadoapims.user.repository.AdministradorRepository;
import br.edu.ifg.trilhadeaprendizadoapims.user.util.Util;
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
    public AdminCreateDto buscarPorEmail(String email) {
        Administrador entidade = repository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException());
        AdminCreateDto dto = modelMapper.map(entidade, AdminCreateDto.class);
        // For auth purposes, map the hashed password to the senha field
        dto.setSenha(entidade.getSenhaHash());
        return dto;
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
        
        // Hash the password before saving
        entidade.setSenhaHash(Util.gerarHashMD5(dto.getSenha()));
        
        // Set role if not already set
        if (entidade.getRole() == null) {
            entidade.setRole(Role.ADMIN);
        }
        
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
