package br.com.incidentemanager.helpdesk.service;

import br.com.incidentemanager.helpdesk.domain.Usuario;
import br.com.incidentemanager.helpdesk.entity.UsuarioEntity;
import br.com.incidentemanager.helpdesk.exception.AuthorizationException;
import br.com.incidentemanager.helpdesk.exception.BusinessException;
import br.com.incidentemanager.helpdesk.mapper.UsuarioMapper;
import br.com.incidentemanager.helpdesk.repository.UsuarioRepository;


import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
//@RequiredArgsConstructor
@Service
public class UsuarioService {

    //@Autowired
    //private final UsuarioRepository userRepository;

    private final UsuarioMapper mapper;
    private final UsuarioRepository usuarioRepository;


    public UsuarioService( UsuarioMapper mapper, UsuarioRepository usuarioRepository) {
        //this.userRepository = userRepository;
        this.mapper = mapper;
        this.usuarioRepository = usuarioRepository;
    }



    //private final UsuarioRepository usuarioRepository; //Não funcionou


    public  Usuario criaUsuario(Usuario novoUsuario) {

        Optional<UsuarioEntity> usuarioExistente = usuarioRepository.findByUsername(novoUsuario.getUsername());

        if (usuarioExistente.isPresent()) {
            throw new BusinessException("Este username já existe na base de dados!");
        }

        UsuarioEntity entity = mapper.toEntity(novoUsuario);
        entity.setCriadoEm(new Date());
        entity.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
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

    public Usuario findByUsername(String username) {
        UsuarioEntity entity = usuarioRepository.findByUsername(username).orElse(null);
        if (entity == null) {
            throw new AuthorizationException("User not found");
        }
        return mapper.toDomain(entity);
    }

    public List<Usuario> listAll() {
        return mapper.toDomain(usuarioRepository.findAll());
    }
}
