package com.arsen.exchange.controller;

import com.arsen.exchange.model.Item;
import com.arsen.exchange.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/item"})

public class ItemController {

    private ItemService itemService;

    ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List findAll() {
        return itemService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Item> findById(@PathVariable long id) {


        return itemService.findById(id).map(item -> ResponseEntity.ok().body(item))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Item create(@RequestBody Item item) {
        return itemService.save(item);
    }

    @PutMapping(value = "/{id}")
    public Item update(@PathVariable("id") long id, @RequestBody Item item) {
        return itemService.update(id, item);
    }


    @DeleteMapping(path = {"/{id}"})
    public void delete(@PathVariable("id") long id) {
        itemService.softDelete(id);
    }


}

