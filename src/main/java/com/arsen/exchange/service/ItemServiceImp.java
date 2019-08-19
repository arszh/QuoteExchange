package com.arsen.exchange.service;

import com.arsen.exchange.exception.ItemNotFoundException;
import com.arsen.exchange.model.Item;
import com.arsen.exchange.repository.ItemRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional

@Service
public class ItemServiceImp implements ItemService {

    private ItemRepository itemRepository;

    ItemServiceImp(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public List findAll() {


        return itemRepository.findAll();
    }


    public Item save(Item item) {
        return itemRepository.save(item);
    }


    public Item update(long id, Item item) {

        return findById(id).map(record -> {
            record.setName(item.getName());
            return record;
        }).orElseThrow(() -> {
            throw new RuntimeException();
        });
    }


    public Optional<Item> findById(long id) {
        return itemRepository.findById(id);
    }


    public void delete(long id) {
        if (findById(id).isPresent()) itemRepository.deleteById(id);
    }


    public void softDelete(long id) {

        if (findById(id).isPresent()) itemRepository.softDelete(id);

    }


    public List<Item> findItems(List<Item> items) {
        List<Item> res = new ArrayList<>();
        for (Item item : items) {

            Item foundItem = findById(item.getId()).orElseThrow(() -> {
                throw new ItemNotFoundException("Item not found in findItems");
            });
            res.add(foundItem);

        }
        return res;
    }
}