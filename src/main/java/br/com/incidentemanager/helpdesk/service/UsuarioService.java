package br.com.incidentemanager.helpdesk.service;

import br.com.incidentemanager.helpdesk.domain.Usuario;
import br.com.incidentemanager.helpdesk.entity.UsuarioEntity;
import br.com.incidentemanager.helpdesk.mapper.UsuarioMapper;
import br.com.incidentemanager.helpdesk.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

//@RequiredArgsConstructor
@Service
public class UsuarioService {

    //@Autowired
    private final UsuarioRepository userRepository;

    private final UsuarioMapper mapper;
    private final UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository userRepository, UsuarioMapper mapper, UsuarioRepository usuarioRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.usuarioRepository = usuarioRepository;
    }



    //private final UsuarioRepository usuarioRepository; //Não funcionou


    public  Usuario criaUsuario(Usuario novoUsuario) {

        //Optional<UsuarioEntity> existentUser = userRepository.findByUsername(newUsuario.getUsername());

//        if (existentUser.isPresent()) {
//            throw new BusinessException("This username is already in use in the system");
//        }

        UsuarioEntity entity = mapper.toEntity(novoUsuario);
        entity.setCriadoEm(new Date());
        entity.setSenha(new BCryptPasswordEncoder().encode(entity.getSenha()));
        entity = usuarioRepository.save(entity);
        return mapper.toDomain(entity);

        //Antes de utilizar o Mapper - Funcional
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//
//        UsuarioEntity entity = new UsuarioEntity();
//        entity.setIdUsuario(newUser.idUsuario());
//        entity.setNome(newUser.nome());
//        entity.setUsername(newUser.username());
//        entity.setSenha(bCryptPasswordEncoder.encode(newUser.senha())); //Encriptografada
//        //entity.setSenha(newUser.senha()); //Não criptografada
//        entity.setEmail(newUser.email());
//        entity.setAtivo(newUser.ativo()); // Caso venha do front, necessário alterar.
//        entity.setTelefone(newUser.telefone());
//        entity.setEhTecnicoTi(newUser.ehTecnicoTi());
//        entity.setEhTecnicoNivelDois(newUser.ehTecnicoNivelDois());
//        entity.setEhAdministrador(newUser.ehAdministrador());
//        entity.setCriadoEm(new Date());
//        usuarioRepository.save(entity); // Agora, o usuarioRepository não é null
    }
}
