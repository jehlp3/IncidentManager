package br.com.incidentemanager.helpdesk.repository;

import br.com.incidentemanager.helpdesk.entity.AnexoChamadoEntity;
import br.com.incidentemanager.helpdesk.entity.ChamadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


//4º video 1:04:00
@Repository
public interface AnexoChamadoRepository extends JpaRepository<AnexoChamadoEntity, UUID> {


}
