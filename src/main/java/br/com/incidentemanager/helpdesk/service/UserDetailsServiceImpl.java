package br.com.incidentemanager.helpdesk.service;

import br.com.incidentemanager.helpdesk.domain.Usuario;
import br.com.incidentemanager.helpdesk.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;


@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioService usuarioService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Getting information for user {}", username);
        Usuario usuario = usuarioService.findByUsername(username);
        if (usuario != null) {
            logger.warn("Information for user {} found", username);
            return new CustomUserDetails(usuario);
        }
        logger.error("Could not find the user {}", username);
        throw new UsernameNotFoundException("Could not find user");
    }
}