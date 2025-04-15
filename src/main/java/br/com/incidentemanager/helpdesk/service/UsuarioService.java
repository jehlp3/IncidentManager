package br.com.incidentemanager.helpdesk.service;

import br.com.incidentemanager.helpdesk.domain.Usuario;
import br.com.incidentemanager.helpdesk.entity.UsuarioEntity;
import br.com.incidentemanager.helpdesk.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

//@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //private final UsuarioRepository usuarioRepository; //Não funcionou

    public void criarUsuario(Usuario newUser) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        UsuarioEntity entity = new UsuarioEntity();
        entity.setIdUsuario(newUser.idUsuario());
        entity.setNome(newUser.nome());
        entity.setUsername(newUser.username());
        entity.setSenha(bCryptPasswordEncoder.encode(newUser.senha())); //Encriptografada
        //entity.setSenha(newUser.senha()); //Não criptografada
        entity.setEmail(newUser.email());
        entity.setAtivo(newUser.ativo()); // Caso venha do front, necessário alterar.
        entity.setTelefone(newUser.telefone());
        entity.setEhTecnicoTi(newUser.ehTecnicoTi());
        entity.setEhTecnicoNivelDois(newUser.ehTecnicoNivelDois());
        entity.setEhAdministrador(newUser.ehAdministrador());
        entity.setCriadoEm(new Date());
        usuarioRepository.save(entity); // Agora, o usuarioRepository não é null
    }
}
