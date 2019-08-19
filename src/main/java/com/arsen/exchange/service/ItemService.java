package com.arsen.exchange.service;

import com.arsen.exchange.model.Item;

import java.util.List;
import java.util.Optional;


public interface ItemService {


    List findAll();


    Item save(Item item);


    Item update(long id, Item item);


    Optional<Item> findById(long id);


    void delete(long id);


    void softDelete(long id);


    List<Item> findItems(List<Item> items);
}