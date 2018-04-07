package ru.sberbank.final_task.babbler.service.internal;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.final_task.babbler.domain.warehouse.StockItem;
import ru.sberbank.final_task.babbler.domain.warehouse.StockItemAudit;
import ru.sberbank.final_task.babbler.repository.warehouse.StockItemAuditRepository;
import ru.sberbank.final_task.babbler.repository.warehouse.StockItemRepository;
import ru.sberbank.final_task.babbler.service.WarehouseService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    @NonNull
    private StockItemRepository stockItemRepository;

    @NonNull
    private StockItemAuditRepository stockItemAuditRepository;

    @Override
    public List<StockItem> findAllStockItems() {
        return this.stockItemRepository.findAll();
    }

    @Override
    public List<StockItemAudit> findAllStockItemsAudit() {
        return this.stockItemAuditRepository.findAll();
    }

    @Override
    public List<StockItemAudit> findStockItemsAuditByItemId(Long id) {
        return this.stockItemAuditRepository.findAllByItemId(id);
    }

    @Override
    public StockItem findStockItemByName(String name) {
        return this.stockItemRepository.findByName(name);
    }

    @Override
    public Optional<StockItem> findStockItemById(Long id) {
        return this.stockItemRepository.findById(id);
    }

    @Override
    public void saveStockItem(StockItem item) {
        stockItemRepository.save(item);
    }

    @Override
    public void saveStockItemAudit(StockItemAudit audit) {
        stockItemAuditRepository.save(audit);
    }
}
