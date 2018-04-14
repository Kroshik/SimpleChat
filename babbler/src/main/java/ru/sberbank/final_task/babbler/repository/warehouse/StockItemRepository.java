package ru.sberbank.final_task.babbler.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.final_task.babbler.domain.warehouse.StockItem;

@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Long> {

    StockItem findByName(String name);
}