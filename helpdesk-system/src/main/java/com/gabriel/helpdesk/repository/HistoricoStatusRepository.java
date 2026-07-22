package com.gabriel.helpdesk.repository;

import com.gabriel.helpdesk.model.HistoricoStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoStatusRepository extends JpaRepository<HistoricoStatus, Long> {
}