package ru.ifmo.cse.phms.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ifmo.cse.phms.domain.warehouse.StockItemAudit;

import java.util.List;

@Repository
public interface StockItemAuditRepository extends JpaRepository<StockItemAudit, Long> {
    List<StockItemAudit> findAllByItemId(Long itemId);
}