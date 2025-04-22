package br.com.incidentemanager.helpdesk.repository;

import br.com.incidentemanager.helpdesk.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

//2º video 32:00
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
    //Repository faz com que o usuário seja salvo no BD

    //4º 8:00 exceções
    Optional<UsuarioEntity> findByUsername(String username);
    //

}
