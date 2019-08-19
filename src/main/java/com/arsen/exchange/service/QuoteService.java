package com.arsen.exchange.service;

import com.arsen.exchange.model.Quote;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface QuoteService {


    List findAll();

    Quote save(Quote quote);

    Optional<Quote> findById(long id);

    void deleteById(long id);

    ResponseEntity<Quote> update(long id, Quote quote);

    void softDelete(long id);
}