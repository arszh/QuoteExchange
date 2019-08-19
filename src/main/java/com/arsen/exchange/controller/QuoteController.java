package com.arsen.exchange.controller;

import com.arsen.exchange.exception.QuoteNotFoundException;
import com.arsen.exchange.model.Quote;
import com.arsen.exchange.service.QuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/quote"})
@Validated
public class QuoteController {

    private QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public List findAll() {
        return quoteService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Quote> findById(@PathVariable long id) {


        return quoteService.findById(id).map(record -> ResponseEntity.ok().body(record))
                .orElseThrow(() -> {
                    throw new QuoteNotFoundException("Quote not found ");
                });
    }

    @PostMapping
    public Quote create(@Valid @RequestBody Quote quote) {
        return quoteService.save(quote);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Quote> update(@PathVariable("id") long id,
                                        @Valid @RequestBody Quote quote) {

        ResponseEntity<Quote> updated = quoteService.update(id, quote);

        return updated;


    }


    @DeleteMapping(path = {"/{id}"})
    public void delete(@PathVariable("id") long id) {
        quoteService.softDelete(id);
    }


}