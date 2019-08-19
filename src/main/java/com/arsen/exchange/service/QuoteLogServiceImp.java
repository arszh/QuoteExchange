package com.arsen.exchange.service;


import com.arsen.exchange.model.Log;
import com.arsen.exchange.model.QuoteLog;
import com.arsen.exchange.repository.LogRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

@Service

public class QuoteLogServiceImp implements LogService {


    private final LogRepository logRepository;

    QuoteLogServiceImp(LogRepository logRepository) {
        this.logRepository = logRepository;
    }


    public Log save(Log log) {
        return logRepository.save(log);
    }

    @Override
    public Log createLog(RequestMethod method, Long idObj, int status, String message) {
        return new QuoteLog(method, status, message, idObj);
    }
}