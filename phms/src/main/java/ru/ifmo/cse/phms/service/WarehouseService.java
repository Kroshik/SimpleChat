package ru.ifmo.cse.phms.service;

import ru.ifmo.cse.phms.domain.warehouse.StockItem;
import ru.ifmo.cse.phms.domain.warehouse.StockItemAudit;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {
    List<StockItem> findAllStockItems();

    List<StockItemAudit> findAllStockItemsAudit();

    List<StockItemAudit> findStockItemsAuditByItemId(Long id);

    StockItem findStockItemByName(String name);

    Optional<StockItem> findStockItemById(Long id);

    void saveStockItem(StockItem item);

    void saveStockItemAudit(StockItemAudit audit);
}
