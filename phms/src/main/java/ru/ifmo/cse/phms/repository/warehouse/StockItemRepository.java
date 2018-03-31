package ru.ifmo.cse.phms.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ifmo.cse.phms.domain.warehouse.StockItem;

@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Long> {

    StockItem findByName(String name);
}