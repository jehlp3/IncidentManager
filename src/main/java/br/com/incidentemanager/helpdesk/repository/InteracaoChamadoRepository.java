package br.com.incidentemanager.helpdesk.repository;

import br.com.incidentemanager.helpdesk.entity.ChamadoEntity;
import br.com.incidentemanager.helpdesk.entity.InteracaoChamadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

//4º video 1:04:00
@Repository
public interface InteracaoChamadoRepository extends JpaRepository<InteracaoChamadoEntity, UUID> {

    List<InteracaoChamadoEntity> findByIdChamado(ChamadoEntity chamado);

}
