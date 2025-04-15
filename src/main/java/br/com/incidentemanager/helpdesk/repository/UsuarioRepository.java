package br.com.incidentemanager.helpdesk.repository;

import br.com.incidentemanager.helpdesk.entity.UsuarioEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;


//2º video 32:00
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
    //Repository faz com que o usuário seja salvo no BD



}
