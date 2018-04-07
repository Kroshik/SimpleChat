package ru.sberbank.final_task.babbler.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.final_task.babbler.domain.warehouse.StockItem;
import ru.sberbank.final_task.babbler.domain.warehouse.StockItemAudit;
import ru.sberbank.final_task.babbler.service.WarehouseService;
import ru.sberbank.final_task.babbler.service.internal.WarehouseServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"/warehouse"})
public class WarehouseController {
    private WarehouseService service;

    public WarehouseController(@Autowired WarehouseServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public String showWarehouseForm(Model model) {
        return "warehouse/stock";
    }

    @RequestMapping(value = "stockItem", method = RequestMethod.GET)
    public String stockItems(Model model) {
        model.addAttribute("stockItems", service.findAllStockItems());
        return "stockItem/list";
    }

    @ModelAttribute("stockItems")
    public List<StockItem> stockItems() {
        return service.findAllStockItems();
    }

    @RequestMapping(value = "stockItemAudit", method = RequestMethod.GET)
    public String stockItemsAudit(Model model) {
        model.addAttribute("stockItemsAudit", service.findAllStockItemsAudit());
        return "stockItemAudit/list";
    }

    @ModelAttribute("stockItemsAudit")
    public List<StockItemAudit> stockItemsAudit(@RequestParam(value="audit",required = false) Long itemId) {
        if (itemId == null) {
            return service.findAllStockItemsAudit();
        } else {
            List<StockItemAudit> tmp = service.findStockItemsAuditByItemId(itemId);
            return tmp;
        }
    }

    @ModelAttribute("stockItem")
    public StockItem stockItem() {
        return new StockItem();
    }

    @PostMapping("addStockItem")
    public String addStockItem(@ModelAttribute("stockItem") StockItem item,
                               BindingResult result) {
        StockItem existing = service.findStockItemByName(item.getName());
        if (existing != null) {
            result.rejectValue("name", null, "There is already stock item with that name");
        }
        if (result.hasErrors()) {
            return "redirect:/warehouse";
        }
        item.setCount(new Long(0));
        service.saveStockItem(item);
        return "redirect:/warehouse";
    }

    @ModelAttribute("stockItemAudit")
    public StockItemAudit stockItemAudit() {
        return new StockItemAudit();
    }

    @PostMapping("addAudit/{id}")
    public String addStockItemAudit(@PathVariable("id") Long id, @ModelAttribute("stockItemAudit") StockItemAudit audit,
                               BindingResult result) {
        if (id == null) {
            result.rejectValue("itemId", null, "Item Id should be not null");
            return "redirect:/warehouse";
        }
        Optional<StockItem> existing = service.findStockItemById(id);
        if (!existing.isPresent()) {
            result.rejectValue("itemId", null, "Item not found");
        }
        StockItem item = existing.get();
        Long newCount = existing.get().getCount() + audit.getCount();
        if (newCount < 0) {
            result.rejectValue("count", null, "Not enough count");
        }
        if (result.hasErrors()) {
            return "redirect:/warehouse?audit=" + id;
        }
        item.setCount(newCount);
        audit.setId(null);
        audit.setItemId(id);
        audit.setCount(newCount);
        audit.setUserId(0L); // FIXME
        audit.setDate(new Date());
        service.saveStockItemAudit(audit);
        service.saveStockItem(item);
        return "redirect:/warehouse?audit=" + id;
    }
}