package com.arsen.exchange.service;

import com.arsen.exchange.exception.QuoteNotFoundException;
import com.arsen.exchange.model.Item;
import com.arsen.exchange.model.Quote;
import com.arsen.exchange.repository.QuoteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class QuoteServiceImp implements QuoteService {

    private ItemService itemService;
    private QuoteRepository quoteRepository;

    QuoteServiceImp(QuoteRepository quoteRepository, ItemService itemService) {
        this.quoteRepository = quoteRepository;
        this.itemService = itemService;
    }

    public List findAll() {
        return quoteRepository.findAll();
    }

    public Quote save(Quote quote) {


        List<Item> items = itemService.findItems(quote.getItems());
        quote.setItems(items);
        return quoteRepository.save(quote);
    }

    public Optional<Quote> findById(long id) {

        return quoteRepository.findById(id);
    }

    public void deleteById(long id) {

        quoteRepository.deleteById(id);

    }

    public ResponseEntity<Quote> update(long id, Quote quote) {


        return findById(id).map(record -> {
            record.setName(quote.getName());
            record.setPrice(quote.getPrice());
            record.setItems(quote.getItems());
            Quote updated = save(record);
            return ResponseEntity.ok().body(updated);
        }).orElseThrow(() -> {
            throw new QuoteNotFoundException("Quote not found by update");
        });


    }

    public void softDelete(long id) {
        quoteRepository.softDelete(id);
    }
}