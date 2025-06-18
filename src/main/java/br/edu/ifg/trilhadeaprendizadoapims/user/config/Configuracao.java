package br.edu.ifg.trilhadeaprendizadoapims.user.config;

import br.edu.ifg.trilhadeaprendizadoapims.user.dto.AdminCreateDto;
import br.edu.ifg.trilhadeaprendizadoapims.user.dto.AlunoCreateDto;
import br.edu.ifg.trilhadeaprendizadoapims.user.model.Administrador;
import br.edu.ifg.trilhadeaprendizadoapims.user.model.Aluno;
import br.edu.ifg.trilhadeaprendizadoapims.user.util.Util;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracao {

    @Bean
    public ModelMapper obterModelMapper(){

        ModelMapper modelMapper = new ModelMapper();

        Converter<String, String> hashConverter = new Converter<String, String>() {
            @Override
            public String convert(MappingContext<String, String> context) {
                String senha = context.getSource();
                if (senha == null) return null;
                return Util.gerarHashMD5(senha);
            }
        };

        modelMapper.typeMap(AlunoCreateDto.class, Aluno.class)
                .addMappings(mapper -> mapper.using(hashConverter)
                        .map(AlunoCreateDto::getSenha, Aluno::setSenhaHash));

        modelMapper.typeMap(AdminCreateDto.class, Administrador.class)
                .addMappings(mapper -> mapper.using(hashConverter)
                        .map(AdminCreateDto::getSenha, Administrador::setSenhaHash));

        return modelMapper;
    }
}